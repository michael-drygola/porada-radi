package wissolsoft.porada.webapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wissolsoft.porada.webapp.model.Vote;

@Repository
public class VotesDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void insertVote(final Vote vote) {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        session.save(vote);
        transaction.commit();
    }



}
