package wissolsoft.porada.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wissolsoft.porada.webapp.dao.LawsDao;
import wissolsoft.porada.webapp.dao.VotesDao;
import wissolsoft.porada.webapp.model.Law;
import wissolsoft.porada.webapp.model.UserVote;

@Service
public class LawsManager {

    @Autowired
    private LawsDao lawsDao;

    @Autowired
    private VotesDao votesDao;

    @Transactional
    public void insertVote(final UserVote vote) {
        votesDao.insertVote(vote);
    }

    @Transactional
    public void insertLaw(final Law law) {
        lawsDao.insertLaw(law);
    }

    @Transactional
    public Law getLawById(long id) {
        return lawsDao.getLawById(id);
    }

    @Transactional
    public List<Law> getLaws() {
        return lawsDao.getAllLaws();
    }

}
