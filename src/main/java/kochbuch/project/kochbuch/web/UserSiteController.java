package kochbuch.project.kochbuch.web;

import kochbuch.project.kochbuch.Benutzer.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class UserSiteController
{
    private final UserService userService;

    public UserSiteController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("userSite")
    public String userSite(Model model)
    {
        //gives user name
        model.addAttribute("user", userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute ("userid",(userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName()).getid()));
       // userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());

        return "userSite";
    }

    //TODO Input is the User ID (Given in REcipe & Comment Obj)
    //TODO Call Repository for the Recipes
    //TODO Call Repository for the Comments
    //TODO Output the Recipe and Comments in the Model
}
