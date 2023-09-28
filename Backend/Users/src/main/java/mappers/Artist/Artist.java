package mappers.Artist;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Artist {

    @Id
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
