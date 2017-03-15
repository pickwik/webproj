package restApp.config.security.jwt;


import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import restApp.config.security.AuthenticationProviderConfig;

import java.util.Collection;


public class AuthenticatedUser implements Authentication {

    private UserDetails user;
    private boolean authenticated = true;
    private UserDetailsService userDetailsService;

    AuthenticatedUser(String name){
        if (userDetailsService==null)userDetailsService = new AuthenticationProviderConfig().userDetailsService();
        user = userDetailsService.loadUserByUsername(name);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return this.user.getUsername();
    }
}