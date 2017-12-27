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
public class UserSiteController
{
    private final UserService userService;
    private final RecipeService recipeService;
    private User user;

    public UserSiteController(UserService userService, RecipeService recipeService)
    {
        this.userService = userService;
        this.recipeService = recipeService;
    }


    @RequestMapping(value = "/userSite")
    public String userSite(@RequestParam(value = "newRecipe", required = false) String newRecipe, @RequestParam(value = "innr", required = false) Double innr, @RequestParam(value = "inun", required = false) String inun, @RequestParam(value = "inna", required = false) String inna, @RequestParam(value = "newRid", required = false) Long newRid,@RequestParam(value = "rname",required = false) String rname, @RequestParam(value = "duration", required = false) Integer duration, @RequestParam(value = "difficulty", required = false) Integer diff, @RequestParam(value = "pplnr", required = false) Integer pplnr, @RequestParam(value = "cprocess", required = false) String cprocess, @RequestParam(value = "released", required = false) String released, @RequestParam(value = "veggie", required = false) String veggie, @RequestParam(value = "delete", required = false) Long delete,@RequestParam(value = "deleteRecipe", required = false) String deleteRecipe,@RequestParam(value = "picURL", required = false) String picURL, Model model)
    {

        user = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());

        if(newRecipe != null)
        {
            recipeService.createRecipe(user);
            model.addAttribute("newR",recipeService.findNewRecipe());
            model.addAttribute("userRecipes",recipeService.findRecipeByCook(user));
            return "userSite";
        }
        if(newRid != null && rname != null && duration != null && diff != null && pplnr != null && cprocess != null)
        {
            recipeService.updateRecipe(newRid,rname,cprocess,diff,duration,pplnr,released,veggie,picURL);
            model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
        }

        model.addAttribute("user", user);
        model.addAttribute ("userid",user.getid());

        if(newRid != null && innr != null && inun != null && inna != null)
        {
                recipeService.addIngredientToList(newRid,inna,innr,inun);
                model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
        }

        if(newRid!= null && delete != null)
        {
            recipeService.removeIngredient(newRid,delete);
            model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
        }
        if(newRid != null && deleteRecipe != null)
        {
            recipeService.deleteRecipe(newRid);
            model.addAttribute("userRecipes",recipeService.findRecipeByCook(user));
            return "userSite";
        }
        if(newRid!= null)
        {
            model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
        }

        model.addAttribute("userRecipes",recipeService.findRecipeByCook(user));

        return "userSite";
    }
}