package wissolsoft.porada.webapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wissolsoft.porada.webapp.model.Deputy;

@Repository
public class DeputyDao {

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
    public void insertDeputy(final Deputy deputy) {
        final Session session = getSession();
        session.save(deputy);
        session.flush();
    }

    @Transactional
    public List<Deputy> getAllDeputies() {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(Deputy.class);
        final List<Deputy> deputies = criteria.list();
        return deputies;
    }

    @Transactional
    public Deputy getByShortName(String shortName) {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(Deputy.class);
        criteria.add(Restrictions.eq("shortname", shortName));
        final Deputy deputy = (Deputy)criteria.uniqueResult();
        return deputy;
    }

}
