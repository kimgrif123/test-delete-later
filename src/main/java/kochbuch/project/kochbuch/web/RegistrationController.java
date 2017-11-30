package kochbuch.project.kochbuch.web;

import kochbuch.project.kochbuch.Benutzer.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if(uname!=null && pswd!=null && wpswd!=null && pswd.equals(wpswd))
        {
            //1. check if input is correct (if not handling the wrong input)
            //2. by correct input call the service method "create user" which again calls the constructer of the User class.
            //3. The service method saves the new User object in the DB.
            //4. The systemuser gets a feedback regarding the successfull registration and can jump to the index
            //(Index checks upon the new user id and enables the access to his usersite)

            userService.createUser(uname,pswd,"USER");

            return "registration";
        }

        else
        {
            return "registration";
        }
    }
}
