package mappers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.lang.reflect.Type;

public class TrackMapper {

    static Gson gson = new Gson();

    public static void trackData(String jsonData) {

        Type TrackType = new TypeToken<List<Track>>(){}.getType();
        List<Track> tracks = gson.fromJson(jsonData, TrackType);

        for(Track t : tracks) {
            System.out.println("Song: " + t.getName());
            System.out.println("Artist: " + t.getArtist());
            System.out.println("Album: " + t.getAlbumName());
            System.out.println("Image: " + t.getImage());
            System.out.println("ID: " + t.getId());
            System.out.println("Preview: " + t.getPreviewUrl() + "\n");
        }
    }

    static class Track {
        private String id;
        private String name;
        private Album album;
        private List<ArtistMapper.Artist> artists;
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

        public String getArtist() {
            return  artists != null && !artists.isEmpty() ?
                    artists.get(0).getName() : "Unknown Artist";
        }

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
}
