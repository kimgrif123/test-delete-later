package kochbuch.project.kochbuch.Kochbuch;

import kochbuch.project.kochbuch.Benutzer.User;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe
{
    /*
        TODO - COMMENT: class - Recipe CHECK!
        The following class contains Getter & Setter for the defined attributes,
        two constructors, calculation methods and a comparator.
        The empty constructor is required to fulfill a requirement of Hibernate, the implemented ORM-Framework.

        The implemented annotations @Id @GeneratedValue define a ID to be automatically generated,
        when a instance of the class Recipe is saved as an entity(due to @Entity) in the database.

        The annotations @OneToMany, @OneToOne define the entity relationships while orphanRemoval
        enables a cascading deletion of foreign key bound entities.
     */

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany(orphanRemoval = true, mappedBy = "recipe")
    private List<Ingredient>  ingredientList;
    private String cookingProcess;
    private Integer numberOfPeople;

    private Integer difficulty;
    private Integer duration;
    private Boolean released;
    private Boolean vegetarian;
    @OneToMany(orphanRemoval = true , mappedBy = "recipe")
    private List<Valuation> valuationList;
    private Double avgScore;
    @OneToOne
    private User cook;

    private String picURL;
    public Recipe(User cook)
    {
        this.valuationList = new ArrayList<Valuation>();
        this.ingredientList = new ArrayList<Ingredient>();
        this.name = new String();
        this.cookingProcess = new String();
        this.numberOfPeople = null;
        this.duration = null;
        this.released = false;
        this.vegetarian = false;
        this.setCook(cook);
    }

    public Recipe (User cook, String name)
    {
        this.valuationList = new ArrayList<Valuation>();
        this.ingredientList = new ArrayList<Ingredient>();
        this.cookingProcess = new String();
        this.duration = null;
        this.released = false;
        this.vegetarian = false;
        this.setCook(cook);
        this.name = name;
    }

    public Recipe(){}

    public User getCook()
    {
        return cook;
    }

    public void setCook(User cook)
    {
        this.cook = cook;
    }
    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }
    public long getId(){return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public List<Ingredient> getIngredientList()
    {
        if (ingredientList == null) {
            ingredientList = new ArrayList<>();
        }
        return ingredientList;
    }

    public void addIngredientToList(Ingredient c)
    {
        this.ingredientList.add(c);
    }

    public void removeIngredient (Ingredient c)
    {
        this.ingredientList.remove(c);
    }

    public void addValuationToList(Valuation v)
    {
        this.valuationList.add(v);
    }

    public String getCookingProcess()
    {
        return this.cookingProcess;
    }

    public void setCookingProcess(String cookingProcess)
    {
        this.cookingProcess = cookingProcess;
    }

    public Integer getNumberOfPeople()
    {
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople)
    {
        this.numberOfPeople = numberOfPeople;
    }

    public Integer getDifficulty()
    {
        return this.difficulty;
    }

    public void setDifficulty(Integer difficulty)
    {
        this.difficulty = difficulty;
    }

    public Integer getDuration()
    {
        return this.duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    public Boolean getReleased()
    {
        return released;
    }

    public void setReleased(Boolean released)
    {
        this.released = released;
    }

    public List<Valuation> getValuationList()
    {
        return this.valuationList;
    }

    public void setValuationList(List<Valuation> v)
    {
        this.valuationList = v;
    }

    public void removeValuation(Valuation v)
    {
        this.valuationList.remove(v);
        this.calcAvgScore();
    }

    public Integer getSumOfValuationScores()
    {
        List<Valuation> valList = this.getValuationList();

        Integer sum = 0;
        for(Valuation v: valList)
        {
            Integer score = v.getScore();
            sum += score;
        }
        return sum;
    }

    public void calcAvgScore()
    {
        Integer y = this.getValuationList().size();
        Double x = this.getSumOfValuationScores().doubleValue();

        Double z = y.doubleValue();
        Double avgScore = x/z;

        this.setAvgScore(avgScore);
    }


    public Double getAvgScore()
    {
        this.calcAvgScore();
        return this.avgScore;
    }

    public void setAvgScore(Double avgScore)
    {
        this.avgScore = avgScore;
    }

    public void setIngredientList (List<Ingredient> x)
    {
        this.ingredientList = x;
    }

    public Recipe recipeCalculator(Integer m)
    {

        Recipe indi = this.clone();

        Integer z = indi.getDuration();
        Integer multiply = m;

       List<Ingredient> indiList = indi.getIngredientList();
       Integer newDuration = z*multiply;
        for(Ingredient in: indiList)
        {
            in.setQuantity(in.getQuantity()*multiply);
        }
        indi.setDuration(newDuration);
        indi.setNumberOfPeople(multiply);
        indi.setIngredientList(indiList);

        return indi;
    }

    public boolean checkIfUserIsCook(User x)
    {
        if(this.getCook().equals(x))
        {
            return true;
        }
        else
        {
            return  false;
        }
    }
    @Override
    public Recipe clone()
    {
        Recipe x = new Recipe(null);
        x.cook = this.getCook();
        x.name = this.getName();
        x.avgScore = this.getAvgScore();
        x.cookingProcess = this.getCookingProcess();
        x.difficulty = this.getDifficulty();
        x.duration = this.getDuration();
        x.vegetarian = this.getVegetarian();
        x.numberOfPeople = this.getNumberOfPeople();
        x.released = this.getReleased();
        x.valuationList = this.getValuationList();
        x.ingredientList = this.getIngredientList();
        x.picURL = this.getPicURL();

        return x;
    }

}