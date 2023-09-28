package mappers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.lang.reflect.Type;

public class TrackMapper {

    /* Track Class used for JSON Mapping */
    static class Track {

        private String id;
        private String name;
        private Album album;
        private List<ArtistMapper.Artist> artists;
        private String previewUrl;

        Track() {}

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
    }

    /* Album class used for getting the album name and image */
    static class Album {
        private List<Image> images;
        private String name;

        public List<Image> getImages() {
            return images;
        }

        public String getName() {
            return name;
        }
    }

    /* Image class for storing all qualities of Images from Albums */
    static class Image {
        private int height;
        private int width;
        private String url;

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public String getUrl() {
            return url;
        }
    }

    // Creates a GSON Object for JSON Mapping.
    static Gson gson = new Gson();

    // Function used to call
    public static void trackData(String jsonData) {

        /* Create a TypeToken for the class Track for mapping */
        Type TrackType = new TypeToken<List<Track>>(){}.getType();

        /* Create a list of tracks based on the JSON file */
        List<Track> tracks = gson.fromJson(jsonData, TrackType);

        /* Prints out each tracks info */
        for(Track t : tracks) {
            System.out.println("Song: " + t.getName());
            System.out.println("Artist: " + t.getArtist());
            System.out.println("Album: " + t.getAlbumName());
            System.out.println("Image: " + t.getImage());
            System.out.println("ID: " + t.getId());
            System.out.println("Preview: " + t.getPreviewUrl() + "\n");
        }
    }
}
