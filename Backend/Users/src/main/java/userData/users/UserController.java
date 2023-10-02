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

    @GetMapping(path = "/user")
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

    @PutMapping(path = "user/{id}/station/{song}")
    void setStationSeed(@PathVariable int id, @PathVariable String song){
        Track t;
        User u = userRepository.findById(id);
        try {
            t = searchTrack(song).get(0);
        } catch (NullPointerException E) {
            return;
        }
        u.getStation().setSeed(t);
        userRepository.save(u);
    }

    @GetMapping(path = "user/{id}/station")
    Station getStation(@PathVariable int id){
        return userRepository.findById(id).getStation();
    }

    @GetMapping(path = "user/{id}/{stationId}")
    List<Track> getTracks(@PathVariable int id, @PathVariable int stationId){
        return userRepository.findById(id).getStation().generateTacks();
    }

    @PutMapping(path = "user/{id}/profile/{string}")
    void setBio(@PathVariable int id, @PathVariable String string){
        User u = userRepository.findById(id);
        u.setProfileStr(string);
        userRepository.save(u);

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
