package userData.trackCreation.TopFields;


import com.fasterxml.jackson.annotation.JsonIgnore;
import userData.users.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TopArtist {

    @Id
    int id;
    private String image;
    private String name;

    @OneToOne
    @JsonIgnore
    private User user;

    public TopArtist(){

    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
