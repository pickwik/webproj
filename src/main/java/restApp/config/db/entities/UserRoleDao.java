package restApp.config.db.entities;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vlad on 15.03.2017.
 */
@Transactional(readOnly = false)
public interface UserRoleDao {
    void save(UserRole userRole);
    void update(UserRole userRole);
    void delete(UserRole userRole);
    List<UserRole> findByUser(User user);
    UserRole findByUserAndName(User user,String roleName);
    UserRole findById(int id);
    List<UserRole> getAllRoles();
}
