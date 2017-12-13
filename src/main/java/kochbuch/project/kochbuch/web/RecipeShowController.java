package kochbuch.project.kochbuch.web;

import kochbuch.project.kochbuch.Benutzer.User;
import kochbuch.project.kochbuch.Benutzer.UserService;
import kochbuch.project.kochbuch.Kochbuch.RecipeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecipeShowController
{
    private final UserService userService;
    private final RecipeService recipeService;
    private User user;

    public RecipeShowController(UserService userService, RecipeService recipeService)
    {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @RequestMapping("/show")
    public String show(@RequestParam(value = "rId", required = false) Long rId,@RequestParam(value = "oId", required = false) Long oId, @RequestParam(value = "score", required = false) Integer score, @RequestParam(value = "comment", required = false) String comment, @RequestParam(value = "calc", required = false) Double calc,@RequestParam(value = "delete", required = false) String delete,Model model) {
        user = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
    if (user != null)
    {
        if (rId != null && score != null && comment != null && user != null)
        {
            recipeService.addValuationToList(rId, score, comment, user);
            model.addAttribute("r", recipeService.findByIdRecipe(rId));
        }

        if (recipeService.aBooleancheckForUserValuation(rId, user)) {
            model.addAttribute("vStatus", "vCommented");
        }
        if (recipeService.aBooleancheckForUserValuation(rId, user) == false)
        {
            model.addAttribute("vStatus", "vOpen");
        }
        model.addAttribute("r", recipeService.findByIdRecipe(rId));


        if (delete != null )
        {
            recipeService.removeValuation(rId, user);
            model.addAttribute("vStatus", "vOpen");
            model.addAttribute("r", recipeService.findByIdRecipe(rId));
        }

    }
    else
    {
        model.addAttribute("vStatus", "vClose");
        model.addAttribute("r", recipeService.findByIdRecipe(rId));
    }

    if (calc != null && rId != null)
    {
        if (rId == 0 && oId != null)
        {

            model.addAttribute("r", recipeService.findByIdRecipe(oId));
        } else
        {
            model.addAttribute("r", recipeService.findByIdRecipe(rId).recipeCalculator(calc));
            model.addAttribute("oId", rId);
        }
    }
    return "show";
    }
}
