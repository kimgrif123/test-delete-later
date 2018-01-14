package kochbuch.project.kochbuch.Benutzer;

import javax.persistence.*;

@Entity
public class User
{
    /*
    TODO - COMMENT: class - User
    The following class contains Getter & Setter for the defined attributes and two constructors.
    The empty constructor is required to fulfill a requirement of Hibernate, the implemented ORM-Framework.
    The implemented Spring Data annotations @Id @GeneratedValue define a ID to be automatically generated,
    when a instance of the class User is saved as an entity(due to @Entity) in the database.
    The annotation "@Column(unique = true)" defines the saved attribute username to be unique in the database.
     */

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

}