package kochbuch.project.kochbuch.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserSiteController
{
    @RequestMapping("userSite")
    public String userSite()
    {
        return "userSite";
    }


    //TODO Input is the User ID (Given in REcipe & Comment Obj)
    //TODO Call Repository for the Recipes
    //TODO Call Repository for the Comments
    //TODO Output the Recipe and Comments in the Model
}
