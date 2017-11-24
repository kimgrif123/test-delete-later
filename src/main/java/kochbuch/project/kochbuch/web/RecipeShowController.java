package kochbuch.project.kochbuch.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeShowController
{
    @RequestMapping("/show")
    public String show()
    {
        return "show";
    }
}
