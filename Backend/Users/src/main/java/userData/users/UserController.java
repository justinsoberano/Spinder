package userData.users;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import userData.stations.Station;
import userData.trackCreation.Track.TrackRepository;
import userData.stations.StationRepository;
//import userData.trackCreation.Spotify.SpotifyController;
import userData.trackCreation.Track.Track;

import javax.servlet.http.HttpServletResponse;
import userData.authentication.AuthController;
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
     * @param , request body(json) used for user creation
     * @return String status
     */
    @PostMapping(path = "/user/{name}/{password}")
    String createUser(@PathVariable String name, @PathVariable String password) throws IOException {
        if (userRepository.findByUserName(name) != null) {
            return "Post Request Failed";
        }
        Random r = new Random();
        User u = new User();
        u.setId(r.nextInt());
        Station s = new Station(u.getId()); // same id as owner
        s.setTempo(50);
        s.setPopularity(50);
        s.setVolume(50);
        u.setUserName(name);
        u.setPassword(password);
        u.setStation(s);
        stationRepository.save(s);
        userRepository.save(u);
        return "success";
    }

    @GetMapping(path = "/login/{username}")
    String getUserId(@PathVariable String username){
        return String.valueOf(userRepository.findByUserName(username).getId());
    }

    /**
     * Seed setting request for rec generation
     *
     * @param song string to be used in seeding the station
     */
    @PutMapping(path = "user/{username}/station/{song}")
    void setStationSeed(@PathVariable String username, @PathVariable String song){
        Track t;
        User u = userRepository.findByUserName(username);
        try {
            t = searchTrack(song).get(0);
        } catch (NullPointerException E) {
            return;
        }
        u.getStation().setSeed(t);
        userRepository.save(u);
    }

//    @PutMapping(path = "user/{username}/station")
//    void removeStationSeed(@PathVariable String username){
//        User u = userRepository.findByUserName(username);
//        u.getStation().remove();
//    }

    @GetMapping(path = "user/{username}/station")
    List<Track> getTracks(@PathVariable String username){
        return userRepository.findByUserName(username).getStation().generateTacks();
    }

    @PutMapping(path = "user/{username}/tempo/{tempo}")
    void setTempo(@PathVariable String username, @PathVariable int tempo){
        User u = userRepository.findByUserName(username);
        Station s = u.getStation();
        s.setTempo(tempo);
    }

    @PutMapping(path = "user/{username}/tempo/{pop}")
    void setPopularity(@PathVariable String username, @PathVariable int pop){
        User u = userRepository.findByUserName(username);
        Station s = u.getStation();
        s.setPopularity(pop);
    }

    @PutMapping(path = "user/{username}/tempo/{vol}")
    void setVolume(@PathVariable String username, @PathVariable int vol){
        User u = userRepository.findByUserName(username);
        Station s = u.getStation();
        s.setTempo(vol);
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

    @DeleteMapping("user/all")
    void deleteAll(){
        userRepository.deleteAll();
    }

}
