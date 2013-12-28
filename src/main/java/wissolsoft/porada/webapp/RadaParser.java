package wissolsoft.porada.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import wissolsoft.porada.webapp.model.Law;

public class RadaParser {

    public static void main(String[] args) {
        try {
            new RadaParser().fetchLaw("http://w1.c1.rada.gov.ua/pls/zweb2/webproc555");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Law> fetchLaws() {
        final List<Law> lawList = new ArrayList<Law>();

        return lawList;
    }

    public Law fetchLaw(final String url) throws IOException {
        final Law law = new Law();
        final Document doc = Jsoup.connect(url).get();
        final Elements raws = doc.select("tr");
        System.out.println(raws.size());
        for(Element raw : raws){
            for(Element cell : raw.children())
            {
                System.out.println(cell.html());
            }
        }

        return law;
    }
}
