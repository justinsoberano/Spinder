package repositories.Authentication;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@RestController
public class AuthController {

    // Redirect URI used for the localhost to connect to the spotify login page
    private static final URI redirectURI = SpotifyHttpManager.makeUri("http://localhost:8080/login/api");

    // Builds our app to the API, we now have a connection with their servers.

    // TODO: Make this into a function instead.
    private static final SpotifyApi spotifyAPI = new SpotifyApi.Builder()
            .setClientId("ae02bde4d6ef4bc395502d8f76e38f04")
            .setClientSecret("0d0a994ae7f842feb33dfa163b56bacd")
            .setRedirectUri(redirectURI)
            .build();


    @GetMapping("login")
    @ResponseBody
    void spotifyLogin(HttpServletResponse response) throws IOException {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyAPI.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-top-read")
                .show_dialog(false)
                .build();

        final URI uri = authorizationCodeUriRequest.execute();
        response.sendRedirect(uri.toString());

    }

    @GetMapping("login/api")
    public String getAccessCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        AuthorizationCodeRequest authorizationCodeRequest = spotifyAPI.authorizationCode(userCode)
                .build();

        try {
            final AuthorizationCodeCredentials credentials = authorizationCodeRequest.execute();
            spotifyAPI.setAccessToken(credentials.getAccessToken());
            spotifyAPI.setRefreshToken(credentials.getRefreshToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        response.sendRedirect("http://localhost:8080/top-artists");
        return spotifyAPI.getAccessToken();
    }

    @GetMapping("register")
    @ResponseBody
    void spotifyRegister(HttpServletResponse response) throws IOException {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyAPI.authorizationCodeUri()
                .scope("user-read-private, user-top-read")
                .show_dialog(true)
                .build();

        final URI uri = authorizationCodeUriRequest.execute();
        response.sendRedirect(uri.toString());
    }

    @GetMapping("register/api")
    public String accessCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        AuthorizationCodeRequest authorizationCodeRequest = spotifyAPI.authorizationCode(userCode)
                .build();

        try {
            final AuthorizationCodeCredentials credentials = authorizationCodeRequest.execute();
            spotifyAPI.setAccessToken(credentials.getAccessToken());
            spotifyAPI.setRefreshToken(credentials.getRefreshToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return spotifyAPI.getAccessToken();
    }

    @GetMapping("top-artists")
    public Artist[] getUserTopArtists() {

        final GetUsersTopArtistsRequest req = spotifyAPI.getUsersTopArtists()
                .time_range("long_term")
                .limit(5)
                .build();

        try {
            final Paging<Artist> artistPaging = req.execute();
            return artistPaging.getItems();
            // Returns a JSON file of top artists

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Oops, something went wrong.\n" + e.getMessage());
        }
        return new Artist[0];
    }

    @GetMapping("top-tracks")
    public Track[] getUserTopTracks() {

        final GetUsersTopTracksRequest req = spotifyAPI.getUsersTopTracks()
                .time_range("long_term")
                .limit(5)
                .build();

        try {
            final Paging<Track> trackPaging = req.execute();
            return trackPaging.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Oops, something went wrong. \n" + e.getMessage());
        }
        return new Track[0];
    }
}
