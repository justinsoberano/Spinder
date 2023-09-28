package mappers.Track;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
class Album {

    @Autowired

    @Id
    private int id;
    @OneToMany
    private List<Image> images;
    private String name;

    public List<Image> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }
}