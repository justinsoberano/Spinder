package mappers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * ArtistMapper is mainly used by TrackMapper for artist information.
 * Can also be used for `Artist` JSON objects.
 */
public class ArtistMapper {

    /* Artist Class */
    static class Artist {

        private String id;
        private String name;

        public String getName() {
            return name;
        }
        public String getId() {
            return id;
        }

    }

    /* Creates GSON object for JSON mapping */
    static Gson gson = new Gson();

    public static void artistData(String data) {

        /* Creates a TypeToken of ArtistType for JSON Mapping */
        Type ArtistType = new TypeToken<List<Artist>>(){}.getType();

        /* Creates a list of artists */
        List<Artist> artists = gson.fromJson(data, ArtistType);

        /* For each artist, display info */
        for (Artist a : artists) {
            System.out.println(a.getName());
            System.out.println(a.getId() + "\n");
        }
    }
}
