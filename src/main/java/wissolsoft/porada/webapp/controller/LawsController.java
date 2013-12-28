package wissolsoft.porada.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import wissolsoft.porada.webapp.model.Law;

@Controller
public class LawsController {

    @RequestMapping("/laws*")
    public String execute(ModelMap model) {
    	final List<Law> laws = new ArrayList<Law>();
    	final Law testLaw1 = new Law();
    	testLaw1.setCaption("Проект Закону про усунення негативних наслідків та недопущення переслідування та покарання осіб з приводу подій, які мали місце під час проведення мирних зібрань");
    	testLaw1.setDate(new Date());
    	testLaw1.setId(3787);
    	testLaw1.setLink("http://w1.c1.rada.gov.ua/pls/zweb2/webproc4_1?pf3511=49375");
    	laws.add(testLaw1);
    	final Law testLaw2 = new Law();
    	testLaw2.setCaption("Проект Закону про внесення змін до Закону України 'Про спеціальну (вільну) економічну зону 'Порто-франко' на території Одеського морського торговельного порту' (щодо регулювання інвестиційної діяльності на території СЕЗ 'Порто-франко')");
    	testLaw2.setDate(new Date());
    	testLaw2.setId(3788);
    	testLaw2.setLink("http://w1.c1.rada.gov.ua/pls/zweb2/webproc4_1?pf3511=49371");
    	laws.add(testLaw2);

        model.addAttribute("laws", laws);
        return "lawlist";
    }

}