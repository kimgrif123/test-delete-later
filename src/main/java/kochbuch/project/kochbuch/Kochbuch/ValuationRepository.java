package kochbuch.project.kochbuch.Kochbuch;

import kochbuch.project.kochbuch.Benutzer.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValuationRepository extends JpaRepository<Valuation,Long>
{

    @Query("select v from Valuation v where v.recipe = :recipe")
    List<Valuation> findValuationByRecipe(@Param("recipe") Recipe recipe);

    @Query("select v from Valuation v where v.id = :id")
    Valuation findValuationById(@Param("id") Long id);

    @Query("select v from Valuation v where v.recipe = :recipe and v.author =:user")
    Valuation findValuationByRecipeAndAuthor(@Param("recipe") Recipe recipe, @Param("user") User user);
}