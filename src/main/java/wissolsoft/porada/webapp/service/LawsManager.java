package wissolsoft.porada.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wissolsoft.porada.webapp.dao.LawsDao;
import wissolsoft.porada.webapp.model.Law;

@Service
public class LawsManager {

    @Autowired
    private LawsDao lawsDao;

    @Transactional
    public void insertLaw(final Law law) {
        lawsDao.insertLaw(law);
    }

    @Transactional
    public List<Law> getLaws() {
        return lawsDao.getAllLaws();
    }

}
