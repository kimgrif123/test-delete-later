package kochbuch.project.kochbuch.Kochbuch;

import kochbuch.project.kochbuch.Benutzer.User;

import javax.persistence.*;

@Entity
public class Valuation
{
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
