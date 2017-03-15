package restApp.controllers;




import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;


@RestController
public class ApiContoller {

    @RequestMapping("/public")
    public String greeting() {
        return "works";
    }
    @RolesAllowed("ROLE_USER")
    @RequestMapping("/secured")
    public String secured(){
        return "secured works";
    }
    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping("/admin")
    public String admin(){
        return "admin works";
    }
}