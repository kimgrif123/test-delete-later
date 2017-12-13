package kochbuch.project.kochbuch.Kochbuch;

import com.sun.org.apache.regexp.internal.RE;
import kochbuch.project.kochbuch.Benutzer.User;
import kochbuch.project.kochbuch.Benutzer.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecipeService
{

    private final RecipeRepository  recipeRepository;
    private final IngredientService ingredientService;
    private final UserService userService;
    private final ValuationService valuationService;


    public RecipeService(RecipeRepository recipeRepository, IngredientService ingredientService, UserService userService, ValuationService valuationService)
    {
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
        this.userService = userService;
        this.valuationService = valuationService;
    }


    public void createRecipe( User cook) {
        recipeRepository.save(new Recipe(cook));
    }


    @PostConstruct
    @Transactional
    public void init() {
        if (recipeRepository.count() == 0)
        {
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //Bratkartoffeln//
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            createRecipeWithName(userService.findUserByName("Kai"),"Bratkartoffeln");

            ingredientService.createIngredient(findByNameRecipe("Bratkartoffeln"),"Kartoffeln",1.00,"kg");
            ingredientService.createIngredient(findByNameRecipe("Bratkartoffeln"),"Öl",20.00,"ml");

            Recipe x = findByNameRecipe("Bratkartoffeln");
            x.setIngredientList(ingredientService.findIngredientByRecipe(findByNameRecipe("Bratkartoffeln")));
            updateRecipe(x.getId(),"Bratkartoffeln","Kartoffeln schnippeln und braten",2,15,1.00,"true","true");

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //BLT Sandwich//
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            createRecipeWithName(userService.findUserByName("Kim"),"BLT Sandwich");

            ingredientService.createIngredient(findByNameRecipe("BLT Sandwich"),"Sandwich",2.00,"Scheiben");
            ingredientService.createIngredient(findByNameRecipe("BLT Sandwich"),"Bacon",4.00,"Scheiben");
            ingredientService.createIngredient(findByNameRecipe("BLT Sandwich"),"Eisbergsalat",1.00,"Kopf");
            ingredientService.createIngredient(findByNameRecipe("BLT Sandwich"),"Tomate",1.00,"Stück");
            ingredientService.createIngredient(findByNameRecipe("BLT Sandwich"),"Mayonnaise",20.00,"g");

            Recipe y = findByNameRecipe("BLT Sandwich");
            y.setIngredientList(ingredientService.findIngredientByRecipe(findByNameRecipe("BLT Sandwich")));
            updateRecipe(y.getId(),"BLT Sandwich","Frühstücksspeck anbraten und auf Küchenpapier abtropfen lassen. Sandwichtoast im Backofen oder Toaster toasten. Brot mit Speck, Scheibentomaten, Eisbergsalat und Mayonnaise garnieren.",2,8,1.00,"true",null);
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }
    }


    public void createRecipeWithName(User cook, String name) {
        recipeRepository.save(new Recipe(cook,name));
    }

    public void saveRecipe( Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public List<Recipe> findAll()
    {
        return recipeRepository.findAll();
    }

    public Recipe findByIdRecipe(Long id)
    {
        return recipeRepository.findByIdRecipe(id);
    }

    public Recipe findNewRecipe()
    {
        return recipeRepository.findNewRecipe();
    }

    public Recipe findByNameRecipe(String name) {return recipeRepository.findByNameRecipe(name);}

    public List<Recipe> findRecipeByCook(User user)
    {
        List<Recipe> allRecipes =  (recipeRepository.findAll());
        List<Recipe> userRecipes = new ArrayList<Recipe>();

        for (Recipe recipe: allRecipes)
        {
            if(recipe.getCook().equals(user))
            {
                userRecipes.add(recipe);
            }
        }
        return userRecipes;
    }

    public List<Recipe> findAllByName(String search)
    {
        //adding the Wildcard
        String pattern = "%"+search+"%";
        return recipeRepository.findAllByName(pattern);
    }

    public List<Recipe> findAllVegRByName(String search)
    {
        //adding the Wildcard
        String pattern = "%"+search+"%";
        return recipeRepository.findAllVegRByName(pattern);
    }
    public void addIngredientToList(Long newRid, String name, Double quantity, String measure)
    {
        Ingredient i =  ingredientService.createIngredient(findByIdRecipe(newRid),name,quantity,measure);

        Recipe r = findByIdRecipe(newRid);

        r.addIngredientToList(i);

        saveRecipe(r);
    }

    public void updateRecipe(Long id, String name, String cprocess, Integer diff, Integer duration, Double nrOfPpl, String released, String veggie)
    {
        Recipe r = findByIdRecipe(id);
        r.setName(name);
        r.setCookingProcess(cprocess);
        r.setDifficulty(diff);
        r.setNumberOfPeople(nrOfPpl);
        r.setDuration(duration);

        if(released != null)
        {
            r.setReleased(true);
        }
        else
        {
            r.setReleased(false);
        }
        if(veggie != null)
        {
            r.setVegetarian(true);
        }
        else
        {
            r.setVegetarian(false);
        }
        saveRecipe(r);
    }

    public void addValuationToList(Long newRid, Integer score, String comment,User user)
    {
        Recipe r = findByIdRecipe(newRid);

        Valuation v =  valuationService.createValuation(r,score,comment,user);

        r.addValuationToList(v);

        saveRecipe(r);
    }

    public void removeValuation(Long rId, User user)
    {

        Recipe x = findByIdRecipe(rId);
        Valuation v = valuationService.findValuationByRecipeAndAuthor(x,user);

        x.removeValuation(v);
        saveRecipe(x);

        valuationService.deleteValuation(v);
    }
    public void removeIngredient(Long rId, Long delete)
    {

        Recipe x = findByIdRecipe(rId);
        Ingredient i = ingredientService.findIngredientById(delete);

        x.removeIngredient(i);

        saveRecipe(x);
        ingredientService.deleteIngredient(ingredientService.findIngredientById(delete));
    }

    public void deleteRecipe (Long id)
    {
        Recipe r = findByIdRecipe(id);
        recipeRepository.delete(r.getId());

    }

    public Boolean aBooleancheckForUserValuation(Long rId, User user)
    {
        Recipe x = findByIdRecipe(rId);
        Valuation v = valuationService.findValuationByRecipeAndAuthor(x,user);
        if(v == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
