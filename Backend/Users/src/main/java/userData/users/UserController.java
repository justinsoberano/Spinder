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

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TrackRepository trackRepository;

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/user/{id}")
    User getUserById( @PathVariable int id){
        if(userRepository.existsById(id)){
            return userRepository.findById(id);
        } else {
            return null; // error
        }

    }

    @PostMapping(path = "/user")
    String createUser(User user){
        if (user == null) {
            return "Post Request Failed";
        }
        userRepository.save(user);
        return "success";
    }

    @PutMapping(path = "user/{id}/station/{song}")
    void createStation(@PathVariable int id, @PathVariable String song){
        Track t;
        try {
            t = searchTrack(song).get(0);
        } catch (NullPointerException E) {
            return;
        }
        getUserById(id).getStation().setSeed(t); // TODO make postmapping
    }

    @GetMapping(path = "user/{id}/station")
    String getStation(@PathVariable int id){
        return userRepository.findById(id).getStation().getSeed();
    }

    @GetMapping(path = "user/{id}/{stationId}")
    List<Track> getTracks(@PathVariable int id, @PathVariable int stationId){
        Station s = userRepository.findById(id).getStation();
        return s.generateTacks();
    }

    @PutMapping(path = "user/{id}/profile/{string}")
    void setBio(@PathVariable int id, @PathVariable String string){
        userRepository.findById(id).setProfileStr(string);
    }

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
