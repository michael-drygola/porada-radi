package wissolsoft.porada.webapp;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import wissolsoft.porada.webapp.model.Deputy;
import wissolsoft.porada.webapp.model.DeputyVote;
import wissolsoft.porada.webapp.model.Law;

public final class RadaParser {

    //TODO make it configurable
    public static final String BASE_URL = "http://w1.c1.rada.gov.ua";

    private static final Log LOGGER = LogFactory.getLog(RadaParser.class);

    private static final DateFormat DATE_PARSER = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        //TODO main is for debug only, remove it
        try {
            //final RadaParser parser = new RadaParser();
            //parser.parseDeputates(Jsoup.connect(BASE_URL + "/pls/site2/fetch_mps?skl_id=8").get()); //"All deputates"
            //parser.parseDeputates(Jsoup.connect(BASE_URL + "/pls/site2/fetch_mps?skl_id=8&pid_id=-3").get()); //Those who out
            parseDeputates(Jsoup.parse(new File("src/test/resources/rada-mock/deputy_list.html"), "cp1251"));
            //parser.fetchLaws(BASE_URL + "/pls/zweb2/webproc555");
            //parser.fetchLaw(BASE_URL + "/pls/zweb2/webproc4_1?pf3511=49375"); //+ "#ui-tabs-2");
            //parser.fetchLawVoteResults(BASE_URL + "/pls/radan_gs09/ns_zakon_gol_dep_wohf?zn=3787");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<Deputy> parseDeputates(final Document doc) throws IOException {
        final Set<Deputy> deputates = new HashSet<Deputy>();
        final Elements atags = doc.select("p.title a[href]");
        for(Element a : atags){
            final String link = a.attr("href");
            final String name = a.text();
            final Deputy deputat = new Deputy();
            deputat.setName(name);
            deputat.setShortName(abbreverateName(name));
            deputat.setLink(link);
            deputat.setId(Long.parseLong(link.substring(link.lastIndexOf('/') + 1)));
            deputates.add(deputat);
            LOGGER.debug("Parsed " + deputat);
        }
        return deputates;
    }

    public static List<Law> fetchLaws(final String url) throws IOException {
        final List<Law> lawList = new ArrayList<Law>();
        final Document doc = Jsoup.connect(url).get();
        final Elements atags = doc.select("table tr td a[href]");
        LOGGER.debug("Going to fetch " + atags.size() + " laws");
        for(Element a : atags){
            String link = BASE_URL + "/pls/zweb2/" + a.attr("href");
            try {
                lawList.add(fetchLaw(link));
            } catch (Exception e) {
                LOGGER.error("Failed to fetch a law by URL=" + link, e);
            }
        }

        return lawList;
    }

    public static Law fetchLaw(final String url) throws IOException, ParseException {
        final Law law = new Law();
        law.setLink(url);
        final Document doc = Jsoup.connect(url).get();
        law.setCaption(doc.select("h3[align=center]").text());
        String idAndRegDate = doc.select("div.zp-info dl dd").get(0).text(); //Номер, дата реєстрації: "3787 від 19.12.2013"
        String[] splitted = idAndRegDate.split(" ");
        law.setId(Long.parseLong(splitted[0]));
        law.setDate(DATE_PARSER.parse(splitted[2]));
        law.setStatus(parseStatus(doc.select("table.striped tbody tr th").get(1).text()));
        law.setDeputatVotes(fetchLawVoteResults(BASE_URL + "/pls/radan_gs09/ns_zakon_gol_dep_wohf?zn=" + law.getId()));
        LOGGER.debug("parsed a " + law.toString());
        return law;
    }

    public static Set<DeputyVote> fetchLawVoteResults(String url) throws IOException {
        final Set<DeputyVote> votes = new HashSet<DeputyVote>();
        final Document doc = Jsoup.connect(url).get();
        for(Element a : doc.select("div.fr_nazva a"))
        {
            String link = BASE_URL + a.attr("href");
            final Document golosdoc = Jsoup.connect(link).get();
            final Elements footnotes = golosdoc.select("li#01 table tbody tr td[align=left]");
            int footnoteIndex = 0;
            for(Element golos : golosdoc.select("li#00 ul li ul.frd li")) { //All deputates are listed twice; li#00 selects one of the lists
                final DeputyVote vote = new DeputyVote();
                final Deputy deputat = new Deputy();
                deputat.setName(golos.select("div.dep").text());
                vote.setDeputy(deputat);
                String voteText = golos.select("div.golos").text();
                if(voteText.contains("*")) {
                    voteText = voteText.replace("*", "");
                    try {
                        String footnote = footnotes.get(footnoteIndex).text();
                        footnoteIndex++;
                        vote.setNote(footnote);
                        vote.setValue(parseFootnote(footnote));
                        vote.setRemote(true);
                    } catch(IndexOutOfBoundsException e) {
                        LOGGER.warn("Wrong number of footnotes in " + link);
                        vote.setValue(parseVote(voteText));
                        vote.setRemote(false);
                    }
                } else {
                    vote.setValue(parseVote(voteText));
                }
                votes.add(vote);
                LOGGER.debug("Parsed " + vote);
            }
        }
        return votes;
    }

    private static Law.Status parseStatus(final String stringStatus) {
        switch (stringStatus) {
        case "Одержано проект":
            return Law.Status.RECEIVED;
        case "Закон підписано":
            return Law.Status.SIGNED;
        default:
            LOGGER.warn("Unknown law status: " + stringStatus);
            return Law.Status.UNKNOWN;
        }
    }

    private static DeputyVote.Value parseVote(final String stringVote) {
        switch (stringVote) {
        case "За":
            return DeputyVote.Value.FOR;
        case "Проти":
            return DeputyVote.Value.AGAINST;
        case "Утримався":
        case "Утрималась":
            return DeputyVote.Value.ABSTAIN;
        case "Не голосував":
        case "Не голосувала":
            return DeputyVote.Value.DIDNT_VOTE;
        case "Відсутня":
        case "Відсутній":
            return DeputyVote.Value.ABSENT;
        default:
            LOGGER.warn("Unknown vote: " + stringVote + " considering as DIDNT_VOTE");
            return DeputyVote.Value.DIDNT_VOTE;
        }
    }

    /**
     * Some deputates are voting remotely. Their results are marked with "*" and there is a footnote like
     * "* До Голови Верховної Ради України надійшла заява народного депутата України Депутата Г.Г.
     * з проханням вважати результатом його голосування - "За""
     * This method parses such footnotes for vote results.
     */
    private static DeputyVote.Value parseFootnote(final String footnote) {
        String remoteVoteResult = footnote.split("\"")[1];
        return parseVote(remoteVoteResult);
    }

    /**
     * @param fullName For example, Булочкин Абдула Єфремович
     * @return sgort name in form Булочкин А. Є.
     */
    private static String abbreverateName(final String fullName) {
        String[] fio = fullName.split(" ");
        return fio[0] + " " + fio[1].charAt(0) + ". " + fio[2].charAt(0) + ".";
    }

    private RadaParser() {
        //it's an utility class
    }
}
