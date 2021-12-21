package entity;

import javax.persistence.*;

@Entity

@Table(name = "users")
public class User extends Character
{
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)

private long id;

    private String username;

    private String password;

    private String img;

    private String salt;

public User ()
{

}

  public String getUsername()
    {
        return username;
    }

public void setUsername(String username)
{
this.username = username;
}

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}






