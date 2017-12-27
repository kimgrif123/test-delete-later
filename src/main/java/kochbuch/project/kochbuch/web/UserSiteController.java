package kochbuch.project.kochbuch.web;

import kochbuch.project.kochbuch.Benutzer.User;
import kochbuch.project.kochbuch.Benutzer.UserService;
import kochbuch.project.kochbuch.Kochbuch.RecipeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*
TODO COMMENT: controller - UserSiteController
This controller handles the URL - requests for the userSite.html site.
 */
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
        /*
            The User object is initiated by using the Spring Security method getName()
            as an Input to an SQL query which returns, if existent, the matching User who´s saved in the db.
        */
        user = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());

        /*
            1. creating a new recipe

            If the parameter "newRecipe" is given, the recipeService method "createRecipe()" is called to initiate and save a new Recipe object in the db.
            The new Recipe object is added to the model with the recipeService method "findNewRecipe()", so the application user can work with it.

            In every scenario handled by the controller, before mapping the URL request,
            the recipeService method "findRecipeByCook" is called upon to add the actual recipes created by the user to the model.
         */
        if(newRecipe != null)
        {
            recipeService.createRecipe(user);
            model.addAttribute("newR",recipeService.findNewRecipe());
            model.addAttribute("userRecipes",recipeService.findRecipeByCook(user));
            return "userSite";
        }
        /*
            2. updating a existing recipe

            If the parameters "newRid", "rname", "duration", "diff", "pplnr" and "cprocess" are given,
            the recipeService method "updateRecipe" is called to make the changes on the Recipe object persistent in the db.
            The updated Recipe object is then added to the model, by finding it with its Id, via the recipeService method "findByIdRecipe()".
         */
        if(newRid != null && rname != null && duration != null && diff != null && pplnr != null && cprocess != null)
        {
            recipeService.updateRecipe(newRid,rname,cprocess,diff,duration,pplnr,released,veggie,picURL);
            model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
        }

        //model.addAttribute("user", user);
        //model.addAttribute ("userid",user.getid());

        /*
            3. adding a new Ingredient to an existing recipe

            If the parameter "newRid", "innr", "inun" and "inna" are given, the recipeService method "addIngredientToList()"
            is called to create a new instance of Ingredient, save it in the db and add the Ingredient to the recipe with the Id value of "newRid"
         */
        if(newRid != null && innr != null && inun != null && inna != null)
        {
                recipeService.addIngredientToList(newRid,inna,innr,inun);
                model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
        }
        /*
            4. removing a Ingredient from an existing recipe

            If the parameter "delete" is given, the recipeService method "removeIngredient()" is called,
            to first remove the Ingredient from the associated recipe and then delete the Ingredient.
            The parameter "delete" is the Id of the Ingredient, while the parameter "newRid" being the Id of the associated recipe.
         */
        if(newRid!= null && delete != null)
        {
            recipeService.removeIngredient(newRid,delete);
            model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
        }
        /*
            5. removing a existing recipe.

            If the parameter "deleteRecipe" is given, the recipeService method "deleteRecipe()" is called,
            to remove the Recipe from the db where the Id equals the parameter value of "newRid".
         */
        if(newRid != null && deleteRecipe != null)
        {
            recipeService.deleteRecipe(newRid);
            model.addAttribute("userRecipes",recipeService.findRecipeByCook(user));
            return "userSite";
        }
        /*
            If the parameter "newRid" is given, the recipe where the Id equals the parameter value of "newRid"
            is added to the model.
         */
        if(newRid!= null)
        {
            model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
        }

        model.addAttribute("userRecipes",recipeService.findRecipeByCook(user));

        return "userSite";
    }
}