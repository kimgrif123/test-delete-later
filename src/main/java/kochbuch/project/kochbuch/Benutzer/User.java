package kochbuch.project.kochbuch.Benutzer;

import kochbuch.project.kochbuch.Kochbuch.Recipe;
import kochbuch.project.kochbuch.Kochbuch.Valuation;

import javax.persistence.*;

@Entity
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;
    @Column(unique = true)
    private String username;
    private String password;


    public User (){}

    public User(String role, String username, String password)
    {
        this.role = role;
        this.username = username;
        this.password = password;

    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public Long getid(){return id;}

    public void setid(Long id){this.id = id;}

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void deleteRecipe(Recipe r)
    {
        if(r.getCook().equals(this)|| this.getRole() == "ADMIN")
        {
            r.setCook(null);
        }
        else
        {
            System.out.println("Du bist weder Admin, noch Verfasser! nix löschen.");
        }
    }
    public void deleteValuation(Recipe x, Valuation v)
    {
        if(v.getAuthor().equals(this) || this.getRole()=="ADMIN")
        {
            x.getValuationList().remove(v);
            x.calcAvgScore();
        }
        else
        {
            System.out.println("nope.");
        }
    }
}