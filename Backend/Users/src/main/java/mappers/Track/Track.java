package mappers.Track;

import mappers.Artist.Artist;

import java.util.List;

public class Track {

    private String id;
    private String name;
    private Album album;
    private List<Artist> artist;
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
        return  artist != null && !artist.isEmpty() ?
                artist.get(0).getName() : "Unknown Artist";
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

}
