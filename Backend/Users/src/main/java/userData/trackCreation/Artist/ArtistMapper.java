package userData.trackCreation.Artist;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * ArtistMapper is mainly used by TrackMapper for artist information.
 * Can also be used for `Artist` JSON objects.
 */
public class ArtistMapper {

    /* Creates GSON object for JSON mapping */
    static Gson gson = new Gson();

    public static List<Artist> artistData(String data) {

        /* Creates a TypeToken of ArtistType for JSON Mapping */
        Type ArtistType = new TypeToken<List<Artist>>(){}.getType();
        /* Return all artists */
        return gson.fromJson(data, ArtistType);
    }
}
