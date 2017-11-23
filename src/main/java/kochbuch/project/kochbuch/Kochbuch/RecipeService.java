package kochbuch.project.kochbuch.Kochbuch;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RecipeService
{
    /*
    private final RecipeRepository  recipeRepository;


    public RecipeService(RecipeRepository recipeRepository)
    {
        this.recipeRepository = recipeRepository;
    }
    */
    //Benötigte Methode für die Anzeige

    //TODO Search method                Input: String   Output: List of Recipe Obj

    //TODO Show method                  Input:  Obj     Output: ...
    //TODO Number of Person config      Input: Double, Obj Id   Output: Obj

  //Benötigte Methoden für Recipe

    //TODO Create a Recipe              Input: ...      Output: Obj

    //TODO Create a Ingredient          Input: ...      Output: Obj

    //TODO Save a Recipe                Input: Obj.     Output: Boolean

    //TODO Update a existing Recipe     Input: Obj       Output: Boolean

    //TODO Delete a existing Recipe     Input: Obj Id    Output: Boolean


    //Benötigte Methoden für Valuation

    //TODO Create a Valuation           Input:...       Output: Obj
    //TODO Update a Valuation           Input: Obj      Output: Boolean
    //TODO Delete a Valuation           Input: Obj Id   Output: Boolean


}
