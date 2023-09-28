package mappers.Track;

import mappers.Artist.Artist;

import javax.persistence.*;
import java.util.List;

@Entity
public class Track {

    @Id
    private String id;
    private String name;

    @ManyToOne
    private Album album;

    @OneToMany
    private List<Artist> artists;
    private String previewUrl;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlbumName() {
        return album.getName();
    }

    /*
     * If `artists` is not null or `artists` is not empty return the main
     * artist, else return unknown.
     */
    public String getArtist() {
        return  artists != null && !artists.isEmpty() ?
                artists.get(0).getName() : "Unknown Artist";
    }

    /*
     * For each image in album.getImages(), return the highest quality image
     */
    public String getImage() {
        for(Image i : album.getImages()) {
            if(i.getWidth() == 640 && i.getHeight() == 640) {
                return i.getUrl();
            }
        }
        return "null";
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    /* Image class for storing all qualities of Images from Albums */


}
