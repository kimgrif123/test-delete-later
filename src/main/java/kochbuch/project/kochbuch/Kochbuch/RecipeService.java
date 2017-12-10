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
            updateIngredientList(findByNameRecipe("Bratkartoffeln").getId(),ingredientService.findIngredientByRecipe(findByNameRecipe("Bratkartoffeln")));
            updateCookingProcess(findByNameRecipe("Bratkartoffeln").getId(),"Kartoffeln schnippeln und braten");
            updateNumberOfPeople(findByNameRecipe("Bratkartoffeln").getId(),1.00);
            updateDifficulty(findByNameRecipe("Bratkartoffeln").getId(),1);
            updateDuration(findByNameRecipe("Bratkartoffeln").getId(),15);
            updateVegetarian(findByNameRecipe("Bratkartoffeln").getId(),Boolean.TRUE);
            updateReleased(findByNameRecipe("Bratkartoffeln").getId(),Boolean.TRUE);
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

    public void updateIngredientList(Long id, List<Ingredient> ingredientList) { findByIdRecipe(id).setIngredientList(ingredientList); }
    public void updateName(Long id, String name){findByIdRecipe(id).setName(name);}
    public void updateCookingProcess (Long id, String cprocess){findByIdRecipe(id).setCookingProcess(cprocess);}
    public void updateDifficulty(Long id, Integer diff){findByIdRecipe(id).setDifficulty(diff);}
    public void updateDuration (Long id, Integer duration){findByIdRecipe(id).setDuration(duration);}
    public void updateNumberOfPeople(Long id, Double nrOfPpl){findByIdRecipe(id).setNumberOfPeople(nrOfPpl);}
    public void updateReleased (Long id, Boolean released){if(released == true){findByIdRecipe(id).setReleased(true);}else{findByIdRecipe(id).setReleased(false);}}
    public void updateVegetarian (Long id, Boolean veggie){if(veggie == true){findByIdRecipe(id).setVegetarian(true);}else{findByIdRecipe(id).setVegetarian(false);}}

    public void removeIngredient(Long rId, Long iId)
    {
        findByIdRecipe(rId).removeIngredient(ingredientService.findIngredientById(iId));
    }

}
