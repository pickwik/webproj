package restApp.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import restApp.Util;
import restApp.config.db.entities.*;
import restApp.config.security.AuthenticationProviderConfig;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 15.03.2017.
 */
@RestController()
public class UserManagementController {
    private final static UserDetailsService userDetailsService= new AuthenticationProviderConfig().userDetailsService();
    private final UserDao userDao;
    private final UserRoleDao userRoleDao;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public UserManagementController(UserDao userDao, UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
        this.userDao = userDao;
    }

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/user/addRole",method = RequestMethod.POST)
    public String addUserRole(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String json = Util.extractRequestBody(request);
        JsonNode inputParams = mapper.readTree(json);
        String username = inputParams.get("username").asText();
        String newRole = "ROLE_"+inputParams.get("role").asText();
        User user = userDao.findByName(username);
        UserRole role = new UserRole(newRole,user);
        userRoleDao.save(role);
        return "done";
    }

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/user/removeRole",method = RequestMethod.POST)
    public String removeUserRole(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String json = Util.extractRequestBody(request);
        JsonNode inputParams = mapper.readTree(json);
        String username = inputParams.get("username").asText();
        String roleToRemove = "ROLE_"+inputParams.get("role").asText();
        User user = userDao.findByName(username);
        UserRole role = userRoleDao.findByUserAndName(user,roleToRemove);
        if (role!=null)userRoleDao.delete(role);
        return "done";
    }

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/user/getAllUsers",method = RequestMethod.POST)
    public String getAllUsers(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ArrayNode users = mapper.createArrayNode();
        for (User user : userDao.getAllUsers()){
            users.add(mapper.valueToTree(user));
        }
        return  mapper.writeValueAsString(users);
    }


}
