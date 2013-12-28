package wissolsoft.porada.webapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wissolsoft.porada.webapp.model.Law;

@Repository
public class LawsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void insertLaw(final Law law) {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        session.save(law);
        transaction.commit();
    }

    @Transactional
    public List<Law> getAllLaws() {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        final Criteria criteria = session.createCriteria(Law.class);
        List<Law> laws = criteria.list();
        transaction.commit();
        return laws;
    }

    @Transactional
    public Law getLawById(long id) {
        final Session session = sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        final Law law = (Law)session.get(Law.class, id);
        transaction.commit();
        return law;
    }

}
