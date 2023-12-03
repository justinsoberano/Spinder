package userData.trackCreation.Spotify;

import com.google.gson.Gson;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Recommendations;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;

import userData.trackCreation.Track.Track;
import userData.trackCreation.Track.mapper.TrackMapper;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@RestController
public class SpotifyController {

    static Gson gson = new Gson();

    private static final SpotifyApi spotifyAPI = new SpotifyApi.Builder()
            .setClientId("ae02bde4d6ef4bc395502d8f76e38f04")
            .setClientSecret("0d0a994ae7f842feb33dfa163b56bacd")
            .build();

    private static final ClientCredentialsRequest credentials = spotifyAPI.clientCredentials()
            .build();

    @PostConstruct
    public void clientCredentials() {
        try {
            final ClientCredentials client = credentials.execute();
            spotifyAPI.setAccessToken(client.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Oops! " + e.getMessage());
        }
    }

    public static List<Track> searchTrack(String trackName) {

        final SearchItemRequest search = spotifyAPI.searchItem(trackName, ModelObjectType.TRACK.getType())
                .market(CountryCode.US)
                .limit(5)
                .build();

        try {
            final SearchResult searchResult = search.execute();
            String jsonOutput = gson.toJson(searchResult.getTracks().getItems());
            return TrackMapper.trackData(jsonOutput);

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    // TODO: Make into a list of tracks
    public static List<Track> getRecommendations(String seeds, int numSongs/*, int popularity*/) {

        /* int max = Math.min(popularity + 20, 100);
        int min = Math.max(popularity - 20, 0); */

        final GetRecommendationsRequest request = spotifyAPI.getRecommendations()
                .limit(numSongs)
                .seed_tracks(seeds)
//                .seed_tracks(seeds.get(1))
//                .seed_tracks(seeds.get(2))
//                .seed_tracks(seeds.get(3))
//                .seed_tracks(seeds.get(4))
                // .max_popularity(max)
                // .min_popularity(min)
                .build();

        try {

            final Recommendations recommendations = request.execute();
            String jsonOutput = gson.toJson(recommendations.getTracks());
            return TrackMapper.trackData(jsonOutput);

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Oh no :( " + e.getMessage());
        }
        return null;
    }

    public static List<Track> getRecommendationsByArtist(List<String> seeds, int numSongs, int popularity, int volume, int tempo) {

        /* int max = Math.min(popularity + 20, 100);
        int min = Math.max(popularity - 20, 0); */

        final GetRecommendationsRequest request = spotifyAPI.getRecommendations()
                .limit(numSongs)
                .seed_artists(seeds.get(0))
                .seed_artists(seeds.get(1))
                .seed_artists(seeds.get(2))
                .seed_artists(seeds.get(3))
                .seed_artists(seeds.get(4))
                .max_popularity(Math.min(popularity + 20, 100))
                .min_popularity(Math.max(popularity - 20, 0))
                .max_tempo((float)Math.min(volume + 20, 100))
                .min_tempo((float)Math.max(volume - 20, 0))
                .max_loudness((float)Math.min(tempo + 20, 100))
                .min_loudness((float)Math.max(tempo - 20, 0))
                .build();

        try {

            final Recommendations recommendations = request.execute();
            String jsonOutput = gson.toJson(recommendations.getTracks());
            return TrackMapper.trackData(jsonOutput);

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Oh no :( " + e.getMessage());
        }
        return null;
    }

    public static List<Track> getRecommendationsByGenre(List<String> seeds, int numSongs/*, int popularity*/) {

        /* int max = Math.min(popularity + 20, 100);
        int min = Math.max(popularity - 20, 0); */

        final GetRecommendationsRequest request = spotifyAPI.getRecommendations()
                .limit(numSongs)
                .seed_genres(seeds.get(0))
                .seed_genres(seeds.get(1))
                .seed_genres(seeds.get(2))
                .seed_genres(seeds.get(3))
                .seed_genres(seeds.get(4))
                // .max_popularity(max)
                // .min_popularity(min)
                .build();

        try {

            final Recommendations recommendations = request.execute();
            String jsonOutput = gson.toJson(recommendations.getTracks());
            return TrackMapper.trackData(jsonOutput);

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Oh no :( " + e.getMessage());
        }
        return null;
    }

}
