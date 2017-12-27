package kochbuch.project.kochbuch.Kochbuch;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IngredientService
{
    /*
    TODO COMMENT: class - IngredientService
    The following class abstracts the interaction with instances the class Ingredient
    by offering services bound to a Ingredient object.
     */

    private final IngredientRepository  ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository)
    {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient createIngredient(Recipe recipe, String name, Double quantity, String measure){Ingredient i = new Ingredient(recipe, name, quantity, measure); ingredientRepository.save(i); return i;}

    public List <Ingredient> findIngredientByRecipe(Recipe recipe){return ingredientRepository.findIngredientByRecipe(recipe);}

    public Ingredient findIngredientById(Long id){return ingredientRepository.findIngredientById(id);}

    public void deleteIngredient(Ingredient ingredient){ingredientRepository.delete(ingredient);}
}
