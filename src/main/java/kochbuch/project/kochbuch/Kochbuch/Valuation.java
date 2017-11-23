package kochbuch.project.kochbuch.Kochbuch;

import kochbuch.project.kochbuch.Benutzer.User;

public class Valuation
{
    private Integer score;
    private String comment;
    private User author;

    public Valuation(Integer score, String comment, User author)
    {
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
