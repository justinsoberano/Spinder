package mappers.Track;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.lang.reflect.Type;

public class TrackMapper {

    // Creates a GSON Object for JSON Mapping.
    static Gson gson = new Gson();

    // Function used to call
    public static List<Track> trackData(String jsonData) {

        /* Create a TypeToken for the class Track for mapping */
        Type TrackType = new TypeToken<List<Track>>(){}.getType();

        /* Return a list of tracks based on the JSON file */
        return gson.fromJson(jsonData, TrackType);
    }
}
