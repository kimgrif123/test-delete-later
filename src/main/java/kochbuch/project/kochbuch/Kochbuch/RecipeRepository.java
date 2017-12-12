package kochbuch.project.kochbuch.Kochbuch;

import kochbuch.project.kochbuch.Benutzer.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long>
{
    @Query("select r from Recipe r where r.id = :id")
    Recipe findByIdRecipe(@Param("id") Long id);

    @Query("select r from Recipe r where r.difficulty is null")
    Recipe findNewRecipe();

    @Query("select r from Recipe r where r.name = :name")
    Recipe findByNameRecipe(@Param("name") String name);

}