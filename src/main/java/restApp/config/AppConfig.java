package restApp.config;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import restApp.config.db.entities.UserDao;
import restApp.config.db.entities.UserDaoImpl;
import restApp.config.db.entities.UserRoleDao;
import restApp.config.db.entities.UserRoleDaoImpl;


/**
 * Created by vlad on 15.03.2017.
 */
@Configuration
public class AppConfig {
    @Autowired
    private SessionFactory sessionFactory;

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl(sessionFactory);
    }

    @Bean
    public UserRoleDao userRoleDao(){
        return new UserRoleDaoImpl(sessionFactory);
    }
}
