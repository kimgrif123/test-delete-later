package kochbuch.project.kochbuch.Kochbuch;

import kochbuch.project.kochbuch.Benutzer.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IngredientService
{

    private final IngredientRepository  ingredientRepository;


    public IngredientService(IngredientRepository ingredientRepository)
    {
        this.ingredientRepository = ingredientRepository;
    }

    public void createIngredient(Recipe recipe, String name, Double quantity, String measure){ingredientRepository.save(new Ingredient(recipe, name, quantity, measure));}

    public List <Ingredient> findIngredientByRecipe(Recipe recipe){return ingredientRepository.findIngredientByRecipe(recipe);}

    public Ingredient findIngredientById(Long id){return ingredientRepository.findIngredientById(id);}


    public void deleteIngredient(Ingredient ingredient){ingredientRepository.delete(ingredient);}
}
