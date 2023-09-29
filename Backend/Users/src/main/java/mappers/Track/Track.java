package mappers.Track;


public class Track {

    /* Track ID */
    private final String id;

    /* Track name */
    private final String name;

    /* Album name that Track is in */
    private final String album;

    /* Image URL */
    private final String image;

    /* Artist name */
    private final String artist;

    /* Preview URL */
    private final String preview;

    /* Constructor */
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
