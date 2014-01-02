package wissolsoft.porada.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import wissolsoft.porada.webapp.model.DeputatVote;
import wissolsoft.porada.webapp.model.Law;
import wissolsoft.porada.webapp.model.UserVote;
import wissolsoft.porada.webapp.service.LawsManager;

@Controller
public class LawsController {

    public static final int NUMBER_OF_DEPUTATES = 460;

    private static final Log LOGGER = LogFactory.getLog(LawsController.class);

    @Autowired
    private LawsManager lawsManager;

    @Autowired
    UserManager userManager;

    @RequestMapping("/laws*")
    public String execute(ModelMap model) {

        final List<Law> lawsFromDB = lawsManager.getLaws();

        model.addAttribute("laws", lawsFromDB);
        return "lawlist";
    }

    @RequestMapping(value="/details/{lawId}", method=RequestMethod.GET)
    public String lawDetails(ModelMap model, @PathVariable("lawId") long id) {
        final Law law = lawsManager.getLawById(id);


        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            final String name = auth.getName(); //get logged in username
            final User user = userManager.getUserByUsername(name);

            //TODO remove copypaste
            for(UserVote v : law.getVotes()) {
                if (v.getUser().getId() == user.getId())
                {
                    model.addAttribute("uservoted", true);
                    break;
                }
            }
        }

        long votesFor = 0;
        long votesAgainst = 0;
        long votesAbstain = 0;
        for(UserVote v : law.getVotes()) {
            switch (v.getValue()) {
                case ABSTAIN:
                    votesAbstain++;
                    break;
                case FOR:
                    votesFor++;
                    break;
                case AGAINST:
                    votesAgainst++;
                    break;
                default:
                    break;
            }
        }

        model.addAttribute("for", votesFor);
        model.addAttribute("against", votesAgainst);
        model.addAttribute("abstain", votesAbstain);

        model.addAttribute("law", law);
        return "details";
    }

    /**
     * Processor for voting
     */
    @RequestMapping(value="/details/{lawId}", method=RequestMethod.POST)
    public String vote(ModelMap model, @PathVariable("lawId") long id, @RequestParam(value="vote") int value) {
        final Law law = lawsManager.getLawById(id);

        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String name = auth.getName(); //get logged in username
        final User user = userManager.getUserByUsername(name);

        final UserVote vote = new UserVote();
        vote.setLaw(law);
        vote.setUser(user);
        vote.setValue(UserVote.Value.values()[value]);

        lawsManager.insertVote(vote);

        long votesFor = 0;
        long votesAgainst = 0;
        long votesAbstain = 0;
        for(UserVote v : law.getVotes()) {
            switch (v.getValue()) {
                case ABSTAIN:
                    votesAbstain++;
                    break;
                case FOR:
                    votesFor++;
                    break;
                case AGAINST:
                    votesAgainst++;
                    break;
                default:
                    break;
            }
        }

        model.addAttribute("uservoted", true);
        model.addAttribute("for", votesFor);
        model.addAttribute("against", votesAgainst);
        model.addAttribute("abstain", votesAbstain);

        model.addAttribute("law", law);
        return "details";
    }

    public Map<DeputatVote.Value, Integer> calcStats(Set<DeputatVote> votes) {
        final Map<DeputatVote.Value, Integer> stats = new HashMap<DeputatVote.Value, Integer>(5, 1);
        for(DeputatVote.Value value : DeputatVote.Value.values()) {
            stats.put(value, 0);
        }
        for(DeputatVote vote : votes) {
            stats.put(vote.getValue(), stats.get(vote.getValue()) + 1);
        }
        int totalVotes = 0;
        for(DeputatVote.Value value : DeputatVote.Value.values()) {
            totalVotes += stats.get(value);
        }
        if(totalVotes != NUMBER_OF_DEPUTATES) {
            LOGGER.warn("Total number of votes is " + totalVotes + ", but should be " + NUMBER_OF_DEPUTATES);
        }
        return stats;
    }

}
