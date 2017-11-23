package kochbuch.project.kochbuch.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController
{
    @RequestMapping("/registration")
    String registration()
    {
        return "registration";
    }
}
