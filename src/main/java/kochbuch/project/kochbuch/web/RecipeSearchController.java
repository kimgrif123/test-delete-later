package kochbuch.project.kochbuch.web;


import kochbuch.project.kochbuch.Kochbuch.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
/*
TODO COMMENT: controller - RecipeSearchController
This controller handels URL - requests to the search.html site.
Depending on if the "search" and the "vegSearch" parameter is given the controller adds the filtered recipe search results to the model.
The filtered list of recipes are the return value of the recipeService methods "findAllVegByName" and "findAllByName" methods.
 */
@Controller

public class RecipeSearchController
{
    private final RecipeService recipeService;

    public RecipeSearchController(RecipeService recipeService)
    {
        this.recipeService = recipeService;
    }

    @RequestMapping("/search")
    public String search(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "vegSearch", required = false) String vegSearch, Model model )
    {
        if(vegSearch != null && search == null)
        {
            model.addAttribute("searchResults",recipeService.findAllVegRByName(vegSearch));
        }

        if(search != null && vegSearch == null)
        {
            model.addAttribute("searchResults",recipeService.findAllByName(search));
        }
        return "search";
    }
}
