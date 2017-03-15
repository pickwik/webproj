package restApp.config.db.entities;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vlad on 15.03.2017.
 */
@Transactional(readOnly = false)
public class UserRoleDaoImpl implements UserRoleDao {
    private final SessionFactory sessionFactory;

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public UserRoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void save(UserRole userRole) {
        getHibernateTemplate().saveOrUpdate(userRole);
    }

    @Override
    public void update(UserRole userRole) {
        getHibernateTemplate().update(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        getHibernateTemplate().delete(userRole);
    }

    @Override
    public List<UserRole> findByUser(User user) {
        DetachedCriteria criteria = DetachedCriteria.forClass(UserRole.class);
        criteria.add(Restrictions.eq("user_id", user.getUserId()));
        List users = getHibernateTemplate().findByCriteria(criteria);
        return users;
    }

    @Override
    public UserRole findByUserAndName(User user, String roleName) {
        DetachedCriteria criteria = DetachedCriteria.forClass(UserRole.class);
        criteria.add(Restrictions.eq("user_id", user.getUserId()));
        criteria.add(Restrictions.eq("user_role", roleName));
        List users = getHibernateTemplate().findByCriteria(criteria);
        if (users.isEmpty()) return null;
        else return (UserRole) users.get(0);
    }

    @Override
    public UserRole findById(int id) {
        return getHibernateTemplate().get(UserRole.class,id);
    }

    @Override
    public List<UserRole> getAllRoles() {
        return null;
    }
    private HibernateTemplate getHibernateTemplate(){
        if(hibernateTemplate==null)hibernateTemplate=new HibernateTemplate(sessionFactory);
        return hibernateTemplate;
    }
}
