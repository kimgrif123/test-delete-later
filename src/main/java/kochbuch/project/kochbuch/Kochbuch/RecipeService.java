package kochbuch.project.kochbuch.Kochbuch;

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


    public RecipeService(RecipeRepository recipeRepository, IngredientService ingredientService, UserService userService)
    {
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
        this.userService = userService;
    }


    public void createRecipe( User cook) {
        recipeRepository.save(new Recipe(cook));
    }


    @PostConstruct
    @Transactional
    public void init() {
        if (recipeRepository.count() == 0)
        {
            createRecipeWithName(userService.findUserByName("Kai"),"Bratkartoffeln");

            ingredientService.createIngredient(findByNameRecipe("Bratkartoffeln"),"Kartoffeln",1.00,"kg");
            ingredientService.createIngredient(findByNameRecipe("Bratkartoffeln"),"Öl",20.00,"ml");

            Recipe x = findByNameRecipe("Bratkartoffeln");
            x.setIngredientList(ingredientService.findIngredientByRecipe(findByNameRecipe("Bratkartoffeln")));
            updateRecipe(x.getId(),"Bratkartoffeln","Kartoffeln schnippeln und braten",2,15,1.00,"true","true");
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
}
