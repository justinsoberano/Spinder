package userData.authentication;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import userData.authentication.InfoController;
import userData.users.User;
import userData.users.UserRepository;

/**
 * This class is responsible for handling the authentication of the user.
 */
@RestController
public class AuthController {


    @Autowired
    UserRepository userRepository;

    /**
     * This is the username of the user that is currently logged in.
     */
    private String username;

    /**
     * This is the redirect URI that Spotify will send the user to after they have logged in.
     */
    private static final URI redirectURI = SpotifyHttpManager.makeUri("http://localhost:8080/register/api");

    /**
     * This is the Spotify API object that will be used to make requests to the Spotify API.
     */
    private static final SpotifyApi spotifyAPI = new SpotifyApi.Builder()
            .setClientId("ae02bde4d6ef4bc395502d8f76e38f04")
            .setClientSecret("0d0a994ae7f842feb33dfa163b56bacd")
            .setRedirectUri(redirectURI)
            .build();

    /**
     * This method will redirect the user to the Spotify login page.
     * @param response: The response object that will be used to redirect the user.
     * @throws IOException: If the redirect fails.
     */
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

    /**
     * This method will get the access code from Spotify and set the access token and refresh token in the Spotify API object.
     * @param userCode The access code that Spotify sends back to the user.
     * @param response The response object that will be used to redirect the user.
     * @return spotifyAPI.getAccessToken() : The access token that will be used to make requests to the Spotify API.
     * @throws IOException If the redirect fails.
     */
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
//        response.sendRedirect("http://localhost:8080/top-artists");
        return spotifyAPI.getAccessToken();
    }

    /**
     * This method will redirect the user to the Spotify registration page.
     * @param response: The response object that will be used to redirect the user.
     * @throws IOException: If the redirect fails.
     */
    @GetMapping("register/{username}")
    @ResponseBody
    void spotifyRegister(HttpServletResponse response, @PathVariable String username) throws IOException {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyAPI.authorizationCodeUri()
                .scope("user-read-private, user-top-read")
                .show_dialog(true)
                .build();

        this.username = username;
        final URI uri = authorizationCodeUriRequest.execute();
        response.sendRedirect(uri.toString());
    }

    /**
     * This method will get the access code from Spotify and set the access token and refresh token in the Spotify API object.
     * @param userCode The access code that Spotify sends back to the user.
     * @return spotifyAPI.getAccessToken() : The access token that will be used to make requests to the Spotify API.
     * @throws IOException If the redirect fails.
     */
    @GetMapping("register/api")
    public String accessCode(@RequestParam("code") String userCode) throws IOException {
        AuthorizationCodeRequest authorizationCodeRequest = spotifyAPI.authorizationCode(userCode)
                .build();

        try {
            final AuthorizationCodeCredentials credentials = authorizationCodeRequest.execute();
            spotifyAPI.setAccessToken(credentials.getAccessToken());
            spotifyAPI.setRefreshToken(credentials.getRefreshToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // User u = userRepository.findByUserName(username);
        // u.setAccessKey(spotifyAPI.getAccessToken());

        System.out.println("[DEBUG] | " + username + " has successfully registered. \n[DEBUG] | Access Token: " + spotifyAPI.getAccessToken());
        return "You can now go back to the app.";
    }
}
