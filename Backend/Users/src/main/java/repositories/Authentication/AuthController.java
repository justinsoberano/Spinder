package repositories.Authentication;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@RestController
public class AuthController {

    // Redirect URI used for the localhost to connect to the spotify login page
    private static final URI redirectURI = SpotifyHttpManager.makeUri("http://localhost:8080/login/api");

    // Builds our app to the API, we now have a connection with their servers.
    private static final SpotifyApi spotifyAPI = new SpotifyApi.Builder()
            .setClientId("ae02bde4d6ef4bc395502d8f76e38f04")
            .setClientSecret("0d0a994ae7f842feb33dfa163b56bacd")
            .setRedirectUri(redirectURI)
            .build();


    @GetMapping("login")
    @ResponseBody // This is where we ask permission for their information.
    void spotifyLogin(HttpServletResponse response) throws IOException {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyAPI.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-top-read") // do not touch
                .show_dialog(true) // If we want to skip the spotify login use 'false'
                .build();

        final URI uri = authorizationCodeUriRequest.execute();

        // Redirects the user to 'localhost:8080/login/api'
        response.sendRedirect(uri.toString());

    }

    @GetMapping("login/api")
    // Where we get the access code
    public String getAccessCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {

        // We 'rebuild' the API with their access code. Allows us to grab information from them.
        AuthorizationCodeRequest authorizationCodeRequest = spotifyAPI.authorizationCode(userCode)
                .build();

        try {
            final AuthorizationCodeCredentials credentials = authorizationCodeRequest.execute();
            spotifyAPI.setAccessToken(credentials.getAccessToken());
            spotifyAPI.setRefreshToken(credentials.getRefreshToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // used for testing
        // response.sendRedirect("http://localhost:8080/top-artists");

        // Returns the users access key
        return spotifyAPI.getAccessToken();
    }

    /* Function used for testing to check if access key retrieval works

    @GetMapping("top-artists")
    public Artist[] getUserTopArtists() {

        final GetUsersTopArtistsRequest req = spotifyAPI.getUsersTopArtists()
                .time_range("long_term")
                .limit(1)
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
    */
}
