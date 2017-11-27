package kochbuch.project.kochbuch.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController
{

    @RequestMapping(value="/registration")
    String registration(@RequestParam(value = "fname", required = false) String fname,@RequestParam(value = "lname", required = false) String lname,@RequestParam(value = "pswd", required = false) String pswd,@RequestParam(value = "wpswd", required = false) String wpswd, Model model)
    {
        if(fname!=null && lname!=null && pswd!=null && wpswd!=null && pswd.equals(wpswd))
        {
            //1. check if input is correct (if not handling the wrong input)
            //2. by correct input call the service method "create user" which again calls the constructer of the User class.
            //3. The service method saves the new User object in the DB.
            //4. The systemuser gets a feedback regarding the successfull registration and can jump to the index
            //(Index checks upon the new user id and enables the access to his usersite)
            System.out.println("test");
            model.addAttribute("fname",fname);
            model.addAttribute("lname",lname);
            model.addAttribute("pswd",pswd);
            model.addAttribute("wpswd",wpswd);


            return "registration";
        }

        else
        {
            return "registration";
        }
    }
}
