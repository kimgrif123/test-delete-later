package kochbuch.project.kochbuch.Kochbuch;

import kochbuch.project.kochbuch.Benutzer.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ValuationService
{
    private final ValuationRepository  valuationRepository;


    public ValuationService(ValuationRepository valuationRepository)
    {
        this.valuationRepository = valuationRepository;
    }

    public Valuation createValuation(Recipe recipe, Integer score, String comment, User author){Valuation v = new Valuation(recipe, score, comment, author); valuationRepository.save(v); return v;}

    public List<Valuation> findValuationByRecipe(Recipe recipe){return valuationRepository.findValuationByRecipe(recipe);}

    public Valuation findValuationById(Long id){return valuationRepository.findValuationById(id);}

    public Valuation findValuationByRecipeAndAuthor(Recipe recipe, User user){return valuationRepository.findValuationByRecipeAndAuthor(recipe,user);}

    public void deleteValuation(Valuation valuation){valuationRepository.delete(valuation);}

}
