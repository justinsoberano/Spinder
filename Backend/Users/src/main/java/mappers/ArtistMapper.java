package mappers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class ArtistMapper {

    static Gson gson = new Gson();

    public static void artistData(String data) {

        Type ArtistType = new TypeToken<List<Artist>>(){}.getType();
        List<Artist> artists = gson.fromJson(data, ArtistType);

        for (Artist a : artists) {
            System.out.println(a.getName());
            System.out.println(a.getId() + "\n");
        }
    }

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
}
