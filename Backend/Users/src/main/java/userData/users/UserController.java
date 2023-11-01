package userData.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import userData.stations.Station;
import userData.trackCreation.Track.TrackRepository;
import userData.stations.StationRepository;
//import userData.trackCreation.Spotify.SpotifyController;
import userData.trackCreation.Track.Track;

import static userData.trackCreation.Spotify.SpotifyController.searchTrack;
//import static userData.trackCreation.Spotify.SpotifyController.searchTrack;

/**
 * Controller used for all user action requests
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TrackRepository trackRepository;

    /**
     * @return list of all users in database
     */
    @GetMapping(path = "/user")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     *
     * @param id, the id of the user
     * @return json data of user of id
     */
    @GetMapping(path = "/user/{id}")
    User getUserById( @PathVariable int id){
        if(userRepository.existsById(id)){
            return userRepository.findById(id);
        } else {
            return null; // error
        }

    }

    /**
     * Post mapping for creating new users.
     * @param user, request body(json) used for user creation
     * @return String status
     */
    @PostMapping(path = "/user")
    String createUser(User user){
        if (user == null) {
            return "Post Request Failed";
        }
        userRepository.save(user);
        return "success";
    }

    /**
     * Request used to create a new station.
     *
     * @param id id of user
     * @param id2 id of new station of user
     */
    @PostMapping(path = "/user/{id}/station/{id2}")
    void createStation(@PathVariable int id, @PathVariable int id2){
        if(stationRepository.existsById(id2)) {
            return;
        }
        Station s = new Station();
        s.setId(id2);
        stationRepository.save(s);
        userRepository.findById(id).setStation(stationRepository.findById(id2));
    }

    /**
     * Seed setting request for rec generation
     *
     * @param id of user refered to.
     * @param song string to be used in seeding the station
     */
    @PutMapping(path = "user/{id}/station/{song}")
    void setStationSeed(@PathVariable int id, @PathVariable String song){
        Track t;
        User u = userRepository.findById(id);
        try {
            t = searchTrack(song).get(0);
        } catch (NullPointerException E) {
            return;
        }
        u.getStation().addSeed(t);
        userRepository.save(u);
    }

    @PutMapping(path = "user/{id}/station")
    void removeStationSeed(@PathVariable int id){
        User u = userRepository.findById(id);
        u.getStation().remove();
    }

    /**
     * Request for getting users station
     *
     * @param id of user refered to.
     * @return station of user
     */
    @GetMapping(path = "user/{id}/station")
    Station getStation(@PathVariable int id){
        return userRepository.findById(id).getStation();
    }

    /**
     * Request for getting list of generated tracks from a station
     *
     * @param id user whose station it is
     * @param stationId station of user
     * @return generated songs from station with seed
     */
    @GetMapping(path = "user/{id}/{stationId}")
    List<Track> getTracks(@PathVariable int id, @PathVariable int stationId){
        return userRepository.findById(id).getStation().generateTacks();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "user/{id}/profile")
    String getBio(@PathVariable int id){
        return userRepository.findById(id).getProfileStr();
    }

    /**
     *
     * @param id
     * @param string
     */
    @PutMapping(path = "user/{id}/profile/{string}")
    void setBio(@PathVariable int id, @PathVariable String string){
        User u = userRepository.findById(id);
        u.setProfileStr(string);
        userRepository.save(u);
    }

    @PutMapping(path = "user/{id}/picture/{string}")
    void setPfp(@PathVariable int id, @PathVariable String string){
        User u = userRepository.findById(id);
        u.setProfilePicture(string);
        userRepository.save(u);
    }

    @GetMapping(path = "user/{id}/picture")
    String getPfp(@PathVariable int id){
        return userRepository.findById(id).getProfilePicture();
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/user/{id}")
    String deleteUser(@PathVariable int id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "success";
        } else {
            return "failure";
        }
    }

}
