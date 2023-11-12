package userData.trackCreation.Track;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import userData.stations.Station;

/**
 * Track class that holds all the information about a song.
 */
@Entity
public class Track {

    /**
     * ID of the Track
     */
    @Id
    private String id;

    /**
     * Name of the Track
     */
    private String name;

    /**
     * Name of the Album that the Track is associated with
     */
    private String album;

    /**
     * Cover art of the Track
     */
    private String image;

    /**
     * Artist of the Track
     */
    private String artist;

    /**
     * Preview URL of the Track
     */
    private String preview;

    /**
     * Station that the Track is associated with
     */
    @OneToOne
    @JsonIgnore
    private Station station;

    /**
     * Default constructor
     */
    public Track(){}

    /**
     * Constructor for Track
     * @param id ID of the Track
     * @param name Name of the Track
     * @param album Name of the Album that the Track is associated with
     * @param artist Artist of the Track
     * @param image Cover art of the Track
     * @param preview Preview URL of the Track
     */
    public Track(String id, String name, String album,
                 String artist, String image, String preview) {

        this.id = id;
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.image = image;
        this.preview = preview != null ? preview : "null";
    }

    /**
     * Returns song ID.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the Track
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the Album that the
     * Track is associated with
     * @return album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Gets the cover art of the track
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * Gets the artist of the Track
     * @return artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Gets the preview URL of the song, normally 30 seconds.
     * @return preview
     */
    public String getPreview() {
        return preview;
    }
}
