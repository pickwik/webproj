package restApp.config.db.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vlad on 14.03.2017.
 */
@Entity
@Transactional(readOnly = false)
public class User implements Serializable{
    @Id
    @NotNull
    @GeneratedValue
    private int userId;
    @Column(length = 40, unique = true)
    @NotNull
    private String username;
    @Column(length = 80)
    @NotNull
    @JsonIgnore
    private String password;
    @Column
    private boolean enabled;
    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    private Set<UserRole> roles;

    public User(){}
    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public boolean addRole(UserRole role){
        Set<UserRole> thisroles = this.getRoles();
        if (thisroles==null)thisroles = new HashSet<>();
        role.setOwner(this);
        return thisroles.add(role);
    }

    public boolean removeRole(UserRole role){
        Set<UserRole> thisroles = this.getRoles();
        if (thisroles==null)thisroles = new HashSet<>();
        return thisroles.remove(role);
    }
}
