package wissolsoft.porada.webapp.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import wissolsoft.porada.webapp.model.Law;
import wissolsoft.porada.webapp.service.LawsManager;

@Controller
public class LawsController {

    @Autowired
    private LawsManager lawsManager;

    @RequestMapping("/laws*")
    public String execute(ModelMap model) {

        final List<Law> lawsFromDB = lawsManager.getLaws();

        model.addAttribute("laws", lawsFromDB);
        return "lawlist";
    }

    @PostConstruct
    public void insertTestData() {
        final Law testLaw1 = new Law();
        testLaw1.setCaption("Проект Закону про усунення негативних наслідків та недопущення переслідування та покарання осіб з приводу подій, які мали місце під час проведення мирних зібрань");
        testLaw1.setDate(new Date());
        testLaw1.setId(3787);
        testLaw1.setLink("http://w1.c1.rada.gov.ua/pls/zweb2/webproc4_1?pf3511=49375");
        final Law testLaw2 = new Law();
        testLaw2.setCaption("Проект Закону про внесення змін до Закону України 'Про спеціальну (вільну) економічну зону 'Порто-франко' на території Одеського морського торговельного порту' (щодо регулювання інвестиційної діяльності на території СЕЗ 'Порто-франко')");
        testLaw2.setDate(new Date());
        testLaw2.setId(3788);
        testLaw2.setLink("http://w1.c1.rada.gov.ua/pls/zweb2/webproc4_1?pf3511=49371");

        lawsManager.insertLaw(testLaw1);
        lawsManager.insertLaw(testLaw2);
    }

}
