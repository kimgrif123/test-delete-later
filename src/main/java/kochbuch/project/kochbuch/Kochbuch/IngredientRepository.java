package kochbuch.project.kochbuch.Kochbuch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long>
{

   /*
        TODO COMMENT: repository - IngredientRepository
        The following class is required to integrate the spring data framework
        and handles the SQL database queries for the class Ingredient.
    */

    @Query("select i from Ingredient i where i.recipe = :recipe")
    List<Ingredient> findIngredientByRecipe(@Param("recipe") Recipe recipe);

    @Query("select i from Ingredient i where i.id = :id")
    Ingredient findIngredientById(@Param("id") Long id);

}