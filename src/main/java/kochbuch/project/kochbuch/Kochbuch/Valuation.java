package kochbuch.project.kochbuch.Kochbuch;

import kochbuch.project.kochbuch.Benutzer.User;

import javax.persistence.*;

@Entity
public class Valuation
{
    /*
        TODO - COMMENT: class - Valuation
        The following class contains Getter & Setter for the defined attributes, two constructors and a comparator.
        The empty constructor is required to fulfill a requirement of Hibernate, the implemented ORM-Framework.
        The implemented annotations @Id @GeneratedValue define a ID to be automatically generated,
        when a instance of the class Valuation is saved as an entity(@Entity)in the database.
     */

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Recipe recipe;
    private Integer score;
    private String comment;
    @OneToOne
    private User author;

    public Valuation(Recipe recipe, Integer score, String comment, User author)
    {
        this.recipe = recipe;
        this.score = score;
        this.comment = comment;
        this.author = author;
    }
    public Valuation() {}

    public Integer getScore()
    {
        return score;
    }

    public void setScore(Integer score)
    {
        this.score = score;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public long getId(){return id;}

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {

        this.comment = comment;
    }

    public boolean checkIfUserIsAuthor(User x)
    {
        if(this.getAuthor().equals(x))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public User getAuthor()
    {
        return author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }
}
