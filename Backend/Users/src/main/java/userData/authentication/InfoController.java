package userData.authentication;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import userData.users.User;
import java.io.IOException;

public class InfoController {

    private SpotifyApi uuidHandler = null;
    private SpotifyApi nameHandler = null;
    private SpotifyApi pictureHandler = null;
    private SpotifyApi playlistHandler = null;

    private static GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = null;


    @GetMapping("info/uuid")
    void getCurrentUuid(User u) {

        uuidHandler = new SpotifyApi.Builder()
                .setAccessToken(u.getAccessKey())
                .build();
        getCurrentUsersProfileRequest = uuidHandler.getCurrentUsersProfile()
                .build();

        try {
            final se.michaelthelin.spotify.model_objects.specification.User user = getCurrentUsersProfileRequest.execute();
            final String uuid = user.getId();
            // u.setUuid(uuid);

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        uuidHandler = null;
        getCurrentUsersProfileRequest = null;
    }

    @GetMapping("info/name")
    void getCurrentName(User u) {

        nameHandler = new SpotifyApi.Builder()
                .setAccessToken(u.getAccessKey())
                .build();
        getCurrentUsersProfileRequest = nameHandler.getCurrentUsersProfile()
                .build();

        try {
            final se.michaelthelin.spotify.model_objects.specification.User user = getCurrentUsersProfileRequest.execute();
            final String name = user.getDisplayName();

            // u.setName(name);

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        nameHandler = null;
        getCurrentUsersProfileRequest = null;
    }

    @GetMapping("info/picture")
    void getCurrentProfilePicture(User u) {

        pictureHandler = new SpotifyApi.Builder()
                .setAccessToken(u.getAccessKey())
                .build();
        getCurrentUsersProfileRequest = pictureHandler.getCurrentUsersProfile()
                .build();

        try {
            final se.michaelthelin.spotify.model_objects.specification.User user = getCurrentUsersProfileRequest.execute();
            final String profilePicture = user.getImages()[0].getUrl();

            u.setProfilePicture(profilePicture);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        pictureHandler = null;
        getCurrentUsersProfileRequest = null;
    }

    @GetMapping("info/favs")
    void getSpinderFavsPlaylist(User u) {

        playlistHandler = new SpotifyApi.Builder()
                .setAccessToken(u.getAccessKey())
                .build();
        getCurrentUsersProfileRequest = playlistHandler.getCurrentUsersProfile()
                .build();

        try {
            final se.michaelthelin.spotify.model_objects.specification.User user = getCurrentUsersProfileRequest.execute();
            final String uuid = user.getId();

            // u.setSpinderFavsPlaylist(uuid);

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        playlistHandler = null;
        getCurrentUsersProfileRequest = null;
    }


}
