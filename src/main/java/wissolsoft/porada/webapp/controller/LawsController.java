package wissolsoft.porada.webapp.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import wissolsoft.porada.webapp.model.Law;
import wissolsoft.porada.webapp.model.Vote;
import wissolsoft.porada.webapp.service.LawsManager;

@Controller
public class LawsController {

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

    @RequestMapping(value="/details", method=RequestMethod.GET)
    public String singleLaw(ModelMap model, @RequestParam(value="law") long id) {
        final Law law = lawsManager.getLawById(id);
        model.addAttribute("law", law);
        return "details";
    }

    /**
     * Processor for voting
     */
    @RequestMapping(value="/details", method=RequestMethod.POST)
    public String vote(ModelMap model, @RequestParam(value="law") long id, @RequestParam(value="vote") int value) {
        final Law law = lawsManager.getLawById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        User user = userManager.getUserByUsername(name);

        Vote vote = new Vote();
        vote.setLaw(law);
        vote.setUser(user);
        vote.setValue(Vote.Value.values()[value]);

        lawsManager.insertVote(vote);

        model.addAttribute("law", law);
        return "details";
    }

}
