package kochbuch.project.kochbuch.Kochbuch;

import kochbuch.project.kochbuch.Benutzer.User;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.ArrayList;

public class Recipe
{
    private String name;
    private ArrayList<Ingredient> ingredientList;
    private String cookingProcess;
    private double numberOfPeople;

    private Integer difficulty;
    private Duration duration;
    private Boolean released;
    private Boolean vegetarian;

    private ArrayList<Valuation> valuationList;
    private Double avgScore;
    private User cook;

    public Recipe(User cook, String name)
    {
        this.valuationList = new ArrayList<Valuation>();
        this.ingredientList = new ArrayList<Ingredient>();
        this.cookingProcess = new String();
        this.duration = Duration.ofMinutes(0);
        this.released = false;
        this.vegetarian = false;
        this.setCook(cook);
        this.setName(name);
    }

    public User getCook()
    {
        return cook;
    }

    public void setCook(User cook)
    {
        this.cook = cook;
    }

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

    public ArrayList getIngredientList()
    {
        return this.ingredientList;
    }

    public void addIngredientToList(Ingredient c)
    {
        this.ingredientList.add(c);
    }

    public void removeIngredient (Ingredient c)
    {
        this.ingredientList.remove(c);
    }

    public void printIngredientList() throws IllegalAccessException
    {
        for (Object x : this.getIngredientList())
        {

            Object someObject = x;
            for (Field field : someObject.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(someObject);
                if (value != null) {
                    System.out.print(value+" ");
                }
            }

            System.out.println(" ");
        }

    }

    public void printCook() throws IllegalAccessException
    {
        String fn = this.getCook().getFirstname();
        String ln = this.getCook().getLastname();
        System.out.println("Koch: "+fn+" "+ln);
    }
    public String getCookingProcess()
    {
        return this.cookingProcess;
    }

    public void setCookingProcess(String cookingProcess)
    {
        this.cookingProcess = cookingProcess;
    }

    public double getNumberOfPeople()
    {
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(double numberOfPeople)
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

    public Duration getDuration()
    {
        return this.duration;
    }

    public void setDuration(Integer x)
    {
        this.duration = this.duration.ofMinutes(x);
    }

    public Boolean getReleased()
    {
        return released;
    }

    public void setReleased(Boolean released)
    {
        this.released = released;
    }

    public ArrayList<Valuation> getValuationList()
    {
        return this.valuationList;
    }

    public void setValuationList(Valuation v)
    {
        this.valuationList.remove(v);
        this.valuationList.add(v);
        this.calcAvgScore();
    }

    public void removeValuation(Valuation v)
    {
        this.valuationList.remove(v);
        this.calcAvgScore();
    }

    public Integer getSumOfValuationScores()
    {
        ArrayList<Valuation> valList = this.getValuationList();

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

    public void setIngredientList (ArrayList<Ingredient> x)
    {
        this.ingredientList = x;
    }

    public Recipe recipeCalculator(double m)
    {

        Recipe indi = this.clone();
        Integer y = indi.getIngredientList().size();
        double multiply = m;

        ArrayList<Ingredient> indiList = indi.getIngredientList();

        for(Ingredient in: indiList)
        {
            in.setQuantity(in.getQuantity()*multiply);
        }

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
        Recipe x = new Recipe(null,"");

        x.cook = this.getCook();
        x.avgScore = this.getAvgScore();
        x.cookingProcess = this.getCookingProcess();
        x.difficulty = this.getDifficulty();
        x.duration = this.getDuration();
        x.vegetarian = this.getVegetarian();
        x.numberOfPeople = this.getNumberOfPeople();
        x.released = this.getReleased();
        x.valuationList = this.getValuationList();
        x.ingredientList = this.getIngredientList();

        return x;
    }

}