package kochbuch.project.kochbuch.Kochbuch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ingredient
{
    /*
        TODO - COMMENT: class - Ingredient
        The following class contains Getter & Setter for the defined attributes and two constructors.
        The empty constructor is required to fulfill a requirement of Hibernate, the implemented ORM-Framework.
        The implemented annotations @Id @GeneratedValue define a ID to be automatically generated,
        when a instance of the class Ingredient is saved as an entity(@Entity)in the database.
     */

    @Id
    @GeneratedValue
    private Long  id;
    @ManyToOne
    private Recipe recipe;
    private Double quantity;
    private String measure;
    private String name;

    public Ingredient(Recipe recipe, String name, Double quantity, String measure)
    {
        this.recipe = recipe;
        this.name = name;
        this.quantity = quantity;
        this.measure = measure;
    }
    public Ingredient(){}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    public Double getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Double quantity)
    {
        this.quantity = quantity;
    }

    public String getMeasure()
    {
        return measure;
    }

    public void setMeasure(String measure)
    {
        this.measure = measure;
    }

    public Long getId(){return id;}

}

