package kochbuch.project.kochbuch.web;

import kochbuch.project.kochbuch.Benutzer.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
TODO COMMENT: controller - RegistrationController
This controller handles the URL - requests for the registration.html site.
There are two scenarios to be handled:

    1. Anonymous user tries to register.
    2. Logged in user tries to register.

If the parameters "uname", "pswd" and "wpswd" are given and "pswd" equals "wpswd",
then the userService method "createUser()" is called to create and save a new User object in the db.
After the new user is registered, the URL - request is mapped to the login.html view.

If the parameters are not given, the controller utilizes Spring Security and checks if the user posses the anonymous role.
If the user has the anonymous role the URL - request is mapped to the registration.html view, so the user can register.
If the user does not have the anonymous role, it means he´s already logged in, the URL - request is mapped(redirected) to the index.html view
 */

@Controller
public class RegistrationController
{
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value="/registration")
    String registration(@RequestParam(value = "uname", required = false) String uname, @RequestParam(value = "pswd", required = false) String pswd,@RequestParam(value = "wpswd", required = false) String wpswd, Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        if(uname!=null && pswd!=null && wpswd!=null && pswd.equals(wpswd))
        {
            userService.createUser(uname,pswd,"USER");
            return "login";
        }

        else
        {
            if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) { return "registration";}
            else {  return "redirect:/";}

        }
    }
}

