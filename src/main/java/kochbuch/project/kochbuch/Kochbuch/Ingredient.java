package kochbuch.project.kochbuch.Kochbuch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ingredient
{
    @Id
    @GeneratedValue
    private long  id;
    @ManyToOne
    private Recipe recipe;
    private double quantity;
    private String measure;
    private String name;

    public Ingredient(Recipe recipe, String name, double quantity, String measure)
    {
        this.recipe = recipe;
        this.name = name;
        this.quantity = quantity;
        this.measure = measure;
    }

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


    public double getQuantity()
    {
        return quantity;
    }

    public void setQuantity(double quantity)
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

    public long getId(){return id;}

}

