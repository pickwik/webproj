package restApp.config.db.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by vlad on 14.03.2017.
 */
@Entity
@Transactional(readOnly = false)
public class UserRole  implements Serializable {
    @Id
    @NotNull
    @GeneratedValue
    private int userRoleId;
    @Column(length = 20)
    private String userRole;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User owner;
    public UserRole(){}

    public UserRole(String roleName){
        this.userRole = roleName;
    }

    public UserRole(String userRole, User owner) {
        this.userRole = userRole;
        this.owner = owner;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
