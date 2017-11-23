package kochbuch.project.kochbuch.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class RecipeSearchController
{

    @RequestMapping("/search") //ensures that HTTP requests to /search are mapped to search()
    public String search(@RequestParam(value = "searchItem", required = false) String searchItem, Model model )
    {                                   // binds the value of the query String param searchItem into the String param of the search method
        if (searchItem != null)
        {
            model.addAttribute("searchItem", searchItem); // the value of the searchItem param is added to a Model obj, making it accessible to the view template
        }
        return "search"; //returning a view/template
    }
}
