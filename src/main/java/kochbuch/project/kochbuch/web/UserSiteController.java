package kochbuch.project.kochbuch.web;

import kochbuch.project.kochbuch.Benutzer.User;
import kochbuch.project.kochbuch.Benutzer.UserService;
import kochbuch.project.kochbuch.Kochbuch.Ingredient;
import kochbuch.project.kochbuch.Kochbuch.IngredientService;
import kochbuch.project.kochbuch.Kochbuch.Recipe;
import kochbuch.project.kochbuch.Kochbuch.RecipeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.Duration;
import java.awt.*;

@Controller
public class UserSiteController
{
    private final UserService userService;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private User user;
    private Duration rDuration;

    public UserSiteController(UserService userService, RecipeService recipeService, IngredientService ingredientService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }


    @RequestMapping(value = "/userSite")
    public String userSite(@RequestParam(value = "innr", required = false) Double innr, @RequestParam(value = "inun", required = false) String inun, @RequestParam(value = "inna", required = false) String inna, @RequestParam(value = "newRid", required = false) Long newRid,@RequestParam(value = "rname",required = false) String rname, @RequestParam(value = "duration", required = false) Integer duration, @RequestParam(value = "difficulty", required = false) Integer diff, @RequestParam(value = "pplnr", required = false) Double pplnr, @RequestParam(value = "cprocess", required = false) String cprocess, @RequestParam(value = "released", required = false) Boolean released, @RequestParam(value = "veggie", required = false) Boolean veggie, @RequestParam(value = "delete", required = false) Long delete, Model model)
    {


        if(newRid != null && rname != null && duration != null && diff != null && pplnr != null && cprocess != null)
        {

            recipeService.updateDuration(newRid,duration);
            System.out.println(veggie);
            if(veggie != null)
            {
                recipeService.updateVegetarian(newRid,veggie);
            }
            else
            {
                recipeService.updateVegetarian(newRid,false);
            }
            if(released != null)
            {
                recipeService.updateReleased(newRid,released);
            }
            else{ recipeService.updateReleased(newRid,false);}
            System.out.println(released);
            recipeService.updateName(newRid,rname);
            System.out.println(rname);

            System.out.println(rDuration);
            recipeService.updateDifficulty(newRid,diff);
            System.out.println(diff);
            recipeService.updateNumberOfPeople(newRid,pplnr);
            System.out.println(pplnr);
            recipeService.updateCookingProcess(newRid,cprocess);
            System.out.println(cprocess);
            model.addAttribute("newRiL",recipeService.findByIdRecipe(newRid).getIngredientList());
            model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
            System.out.println(recipeService.findByIdRecipe(newRid).getName());
            /*Recipe recipe = recipeService.findByIdRecipe(newRid);
            System.out.println(recipe.getName());
            recipeService.saveRecipe(recipe); */
            model.addAttribute("newRid", newRid);
        }

        user = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        //TODO as a RequestParam the id of the recipe
        //gives user name
        model.addAttribute("user", user);
        model.addAttribute ("userid",user.getid());

        if(innr != null && inun != null && inna != null)
            {
                if(newRid != null)
                {
                    System.out.println("test");

                    ingredientService.createIngredient(recipeService.findByIdRecipe(newRid),inna, innr,inun);
                    recipeService.updateIngredientList(recipeService.findNewRecipe().getId(), ingredientService.findIngredientByRecipe(recipeService.findByIdRecipe(newRid)));
                    model.addAttribute("newRiL",recipeService.findByIdRecipe(newRid).getIngredientList());
                    model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
                    model.addAttribute("newRid", newRid);
                }
                else
                {
                    recipeService.createRecipe(user);
                    ingredientService.createIngredient(recipeService.findNewRecipe(),inna, innr,inun);

                    recipeService.updateIngredientList(recipeService.findNewRecipe().getId(), ingredientService.findIngredientByRecipe(recipeService.findNewRecipe()));

                    model.addAttribute("newRiL",recipeService.findNewRecipe().getIngredientList());
                    model.addAttribute("newRid",recipeService.findNewRecipe().getId());
                }
            }

        if(newRid!= null && delete != null)
        {
            model.addAttribute("newRid",newRid);
            ingredientService.deleteIngredient(ingredientService.findIngredientById(delete));
            recipeService.updateIngredientList(recipeService.findNewRecipe().getId(), ingredientService.findIngredientByRecipe(recipeService.findByIdRecipe(newRid)));
            model.addAttribute("newRiL",recipeService.findByIdRecipe(newRid).getIngredientList());
        }

        if(newRid!= null)
        {
            model.addAttribute("newRid",newRid);
            model.addAttribute("newR",recipeService.findByIdRecipe(newRid));
            model.addAttribute("newRiL",recipeService.findByIdRecipe(newRid).getIngredientList());
            model.addAttribute("newRd",recipeService.findByIdRecipe(newRid).getDuration().toMinutes());
        }

        System.out.println("testout");

       // userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());

        return "userSite";
    }


    //TODO Input is the User ID (Given in REcipe & Comment Obj)
    //TODO Call Repository for the Recipes
    //TODO Call Repository for the Comments
    //TODO Output the Recipe and Comments in the Model
}
