package restApp.config.db.entities;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vlad on 15.03.2017.
 */
@Transactional(readOnly = false)
public interface UserDao {
    void save(User user);
    void update(User user);
    void delete(User user);
    User findByName(String username);
    User findById(int id);
    List<User> getAllUsers();
}
