package restApp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 * Created by vlad on 14.03.2017.
 */
@Configuration
@ComponentScan
public class AuthenticationProviderConfig {
    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/store_db?autoReconnect=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("testpass");
        return dataSource;
    }
    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource());
        jdbcDao.setUsersByUsernameQuery("select username,password,enabled from user where username=?");
        jdbcDao.setAuthoritiesByUsernameQuery("select user.username,user_role.user_role from user_role left join user on(user_role.user_id=user.user_id) where user.username=?");
        return jdbcDao;
    }

}
