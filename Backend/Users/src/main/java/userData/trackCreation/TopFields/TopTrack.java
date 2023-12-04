package userData.trackCreation.TopFields;

import com.fasterxml.jackson.annotation.JsonIgnore;
import userData.users.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TopTrack {

    @GeneratedValue
    @Id
    int id;
    private String name;

    private String image;

    @OneToOne
    @JsonIgnore
    private User user;

    public TopTrack(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
