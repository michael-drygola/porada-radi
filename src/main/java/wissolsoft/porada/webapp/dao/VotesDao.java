package wissolsoft.porada.webapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wissolsoft.porada.webapp.model.UserVote;

@Repository
public class VotesDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        Session sess;
        try {
            sess = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            sess = sessionFactory.openSession();
        }
        return sess;
    }

    @Transactional
    public void insertVote(final UserVote vote) {
        final Session session = getSession();
        session.save(vote);
        session.flush();
    }



}
