package entity;
import javax.persistence.*;


@Entity

@Table(name = "posts")

public class Post extends Messege
{
 @Id

 @GeneratedValue(strategy = GenerationType.AUTO)

 private long id;

    @ManyToOne(fetch = FetchType.EAGER)

    private User author;

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
    
        

}
