package kochbuch.project.kochbuch.Kochbuch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long>
{
    /*
        TODO COMMENT: repository - RecipeRepository
        The following class is required to integrate the spring data framework
        and handles the SQL database queries for the class Recipe.
    */

    @Query("select r from Recipe r where r.id = :id")
    Recipe findByIdRecipe(@Param("id") Long id);

    @Query("select r from Recipe r where r.difficulty is null")
    Recipe findNewRecipe();

    @Query("select r from Recipe r where r.name = :name")
    Recipe findByNameRecipe(@Param("name") String name);

    @Query("select r from Recipe r where r.name like :search and r.released = true")
    List<Recipe> findAllByName(@Param("search") String search);

    @Query("select r from Recipe r where r.name like :search and r.vegetarian = true and r.released = true")
    List<Recipe> findAllVegRByName(@Param("search") String search);
}