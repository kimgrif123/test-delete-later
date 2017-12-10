package kochbuch.project.kochbuch.Kochbuch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long>
{

    @Query("select i from Ingredient i where i.recipe = :recipe")
    List<Ingredient> findIngredientByRecipe(@Param("recipe") Recipe recipe);

    @Query("select i from Ingredient i where i.id = :id")
    Ingredient findIngredientById(@Param("id") Long id);

}