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
TODO COMMENT: controller - RecipeShowController
This controller handles the URL - requests for the show.html site.
To handle the requests, the "user" variable and
the following parameters are used: "rId","oId","score","calc","comment" and "delete"

********
Comment:
********

- Authorization

The User object is initiated by using the Spring Security method getName()
as an Input to an SQL query which returns, if existent, the matching User who´s saved in the db.
If the User is anonymous, the variable value is null, this way a differentiation between users and anonymous users is possible.
The authorization can be done in a more simple manner but due to the fact, that the user is needed further, this code was implemented.

- Scenarios

The controller has to handle three scenarios:

    1. The auth. user has just sent a evaluation.
    2. The auth. user has already wrote a evaluation for the recipe.
    3. The auth. user has not wrote a evaluation for the recipe yet.

The 1. scenario is checked by validating the user input (also through JS),then creating and adding the new valuation
by calling the recipeService method "addValuationToList". The updated recipe is then provided to show.html through the model.

The 2. scenario is checked by calling the recipeService method "aBooleancheckForUserValuation" with a boolean return value.
If true is returned, this information is added to the model by the attribute "vStatus" with the value "vCommented".

The 3. scenario utilizes the same method as in the second scenario and adds,
if "false" is returned the attribute "vStatus" as well but with the value "vOpen".

The attribute "vStatus" and its differing value enable a dynamic show.html/view, fitting to the situation of the User.

************
Calculation:
************

The parameters "rId", "oId" and "calc" are used to control the calculation function.
The "calc" parameter holds, if given, the multiplier to calculate the recipe for a different number of people.
The calculation is executed directly in the instantiation of the original recipe saved in the db,
making it a temporary recipe with "no relation" to the originally saved recipe object.
For the user to be able to interact with the persistent version in a later URL request, the original recipe Id is added to the model ("oId").
 */

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
    public String show(@RequestParam(value = "rId", required = false) Long rId,@RequestParam(value = "oId", required = false) Long oId, @RequestParam(value = "score", required = false) Integer score, @RequestParam(value = "comment", required = false) String comment, @RequestParam(value = "calc", required = false) Integer calc,@RequestParam(value = "delete", required = false) String delete,Model model) {
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