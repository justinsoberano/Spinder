package userData.authentication;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import userData.users.User;
import java.io.IOException;

public class InfoController {

    private static SpotifyApi playlistHandler = null;
    private static GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = null;

    void getCurrentUuid(User u) {
        SpotifyApi uuidHandler = new SpotifyApi.Builder()
                .setAccessToken(u.getAccessKey())
                .build();
        getCurrentUsersProfileRequest = uuidHandler.getCurrentUsersProfile()
                .build();
        try {
            final se.
                    michaelthelin.
                    spotify.
                    model_objects.
                    specification.
                    User user = getCurrentUsersProfileRequest.execute();

            final String uuid = user.getId();
            u.setUuid(uuid);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        getCurrentUsersProfileRequest = null;
    }

    void getCurrentName(User u) {

        SpotifyApi nameHandler = new SpotifyApi.Builder()
                .setAccessToken(u.getAccessKey())
                .build();
        getCurrentUsersProfileRequest = nameHandler.getCurrentUsersProfile()
                .build();

        try {
            final se.michaelthelin.spotify.model_objects.specification.User user = getCurrentUsersProfileRequest.execute();
            final String name = user.getDisplayName();

            u.setName(name);

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        getCurrentUsersProfileRequest = null;
    }

    void getCurrentProfilePicture(User u) {

        SpotifyApi pictureHandler = new SpotifyApi.Builder()
                .setAccessToken(u.getAccessKey())
                .build();
        getCurrentUsersProfileRequest = pictureHandler.getCurrentUsersProfile()
                .build();

        try {
            final se.
                    michaelthelin.
                    spotify.model_objects.
                    specification.User user = getCurrentUsersProfileRequest.execute();

            final String profilePicture = user.getImages()[0].getUrl();

            u.setProfilePicture(profilePicture);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        getCurrentUsersProfileRequest = null;
    }

    void createSpinderFavorites(User u) {
        String uuid = u.getUuid();

        playlistHandler = new SpotifyApi.Builder()
                .setAccessToken(u.getAccessKey())
                .build();

        CreatePlaylistRequest createPlaylist = playlistHandler.createPlaylist(uuid, "Spinder Favs")
                .collaborative(false)
                .public_(false)
                .description("Generated with love on Spinder <3")
                .build();

        try {
            final Playlist playlist = createPlaylist.execute();
            final String playlistId = playlist.getId();
            // u.setPlaylistID(playlistId);

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        playlistHandler = null;
        getCurrentUsersProfileRequest = null;
    }

    @PostMapping("add/{username}/{track}")
    public static void addTrackToFavorites(@PathVariable String username, @PathVariable String track) {

        playlistHandler = new SpotifyApi.Builder()
                .setAccessToken(username)
                .build();

        String[] trackUri = new String[]{"spotify:track:" + track};
        String playlistId = null; // u.getPlaylistID();

        AddItemsToPlaylistRequest addTrack = playlistHandler
                .addItemsToPlaylist(playlistId, trackUri)
                .build();
        try {
            final SnapshotResult executeAddTrack = addTrack.execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
