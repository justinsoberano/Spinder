package userData.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import userData.trackCreation.Track.TrackRepository;
import userData.stations.StationRepository;

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

    @PostMapping(path = "user/station/{song}")
    void createStation(@PathVariable String song){

    }

    @PutMapping(path = "user/profile/{string}")
    void setBio(@PathVariable String string){

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
