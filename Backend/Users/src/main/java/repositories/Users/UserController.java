package repositories.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import repositories.Users.User;
import repositories.Users.UserRepository;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

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
