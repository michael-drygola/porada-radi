package wissolsoft.porada.webapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wissolsoft.porada.webapp.model.Law;

@Repository
public class LawsDao {

    @Autowired(required = true)
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
    public void insertLaw(final Law law) {
        final Session session = getSession();
        //final Transaction transaction = session.beginTransaction();
        session.save(law);
        session.flush();
        //transaction.commit();
    }

    @Transactional
    public List<Law> getAllLaws() {
        final Session session = getSession();
        //final Transaction transaction = session.beginTransaction();
        final Criteria criteria = session.createCriteria(Law.class);
        List<Law> laws = criteria.list();
        //transaction.commit();
        return laws;
    }

    @Transactional
    public Law getLawById(long id) {
        final Session session = getSession();
        //final Transaction transaction = session.beginTransaction();
        final Law law = (Law)session.get(Law.class, id);
        //transaction.commit();
        return law;
    }

}
