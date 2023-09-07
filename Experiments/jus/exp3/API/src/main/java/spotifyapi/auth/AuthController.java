package spotifyapi.auth;


import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
/* The following class can only be executed using localhost:8080/api/...  */

public class AuthController {

    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/api/auth");
    private String code = "";
    private final static String clientID = "adca318cd4114135b95b6fd65f385b38";
    private final static String clientSecret = "a8fcbaa0cb434e189312c73b2a23cd30";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientID)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();

    /* localhost:8080/api/login
    *  Connects the SpotifyAPI to this SpringBoot App
    */
    @GetMapping("/login")
    @ResponseBody
    public String login() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-top-read")
                .build();

        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

    /* Once login finishes executing it calls this function, this updates the
     * spotifyAPI to use your special access token.
     */
    @GetMapping(value = "auth")
    public String getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        code = userCode;
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();

        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        response.sendRedirect("http://localhost:8080/api/top-tracks");
        return spotifyApi.getAccessToken();
    }

    /* localhost:8080/api/top-tracks
     * Once login finishes authenticating, it will then run this method which
     * returns the users top tracks.
     */
    @GetMapping("top-tracks")
    public Track[] getUserTopTracks() {
        final GetUsersTopTracksRequest getUsersTopTracksRequest = spotifyApi.getUsersTopTracks()
                .time_range("long_term") // "medium_term", "short_term"
                .limit(5)
                .build();
        try {
            final Paging<Track> artistPaging = getUsersTopTracksRequest.execute();
            return artistPaging.getItems();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new Track[0];
    }
}
