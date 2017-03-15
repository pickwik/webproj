package restApp.config.db.entities;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by vlad on 15.03.2017.
 */
@Transactional(readOnly = false)
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void save(User user) {
        getHibernateTemplate().saveOrUpdate(user);
    }

    @Override
    public void update(User user) {
        getHibernateTemplate().update(user);
    }

    @Override
    public void delete(User user) {
        getHibernateTemplate().delete(user);
    }

    @Override
    public User findByName(String username) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("username", username));
        List users = getHibernateTemplate().findByCriteria(criteria);
        if (users.isEmpty()) return null;
        else return (User) users.get(0);
    }

    @Override
    public User findById(int id) {
        return getHibernateTemplate().get(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return getHibernateTemplate().loadAll(User.class);
    }
    private HibernateTemplate getHibernateTemplate(){
        if(hibernateTemplate==null)hibernateTemplate=new HibernateTemplate(sessionFactory);
        return hibernateTemplate;
    }
}
