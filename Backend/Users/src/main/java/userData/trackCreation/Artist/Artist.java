package userData.trackCreation.Artist;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Artist class for mapper
 */
@Entity
public class Artist {

    /* Artist ID */
    @Id
    private String id;

    /* Artist name */
    private String name;

    /**
     * Gets the name of the artist
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name of the ID
     * @return id;
     */
    public String getId() {
        return id;
    }
}
