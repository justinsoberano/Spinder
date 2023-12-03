package userData.trackCreation.TopFields;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopTrack {

    @Id
    int id;
    private String name;

    private String image;

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
