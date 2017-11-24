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
