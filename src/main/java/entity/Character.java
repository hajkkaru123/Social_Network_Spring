package entity;
import javax.persistence.MappedSuperclass;


@MappedSuperclass

public class Character
{
    private String email;

    private String firstname;

    private String lastname;



    public Character() {
    }
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }




}
