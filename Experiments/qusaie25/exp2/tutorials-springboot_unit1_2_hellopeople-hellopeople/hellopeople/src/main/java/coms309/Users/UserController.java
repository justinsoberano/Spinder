package coms309.Users;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.HashMap;

/**
 * @author Qusai Elwazir
 */

@RestController
public class UserController {
    HashMap<String, User> userList = new  HashMap<>();

    @GetMapping("/users")
    public @ResponseBody HashMap<String,User> getAllUsers() {
        return userList;
    }

    @PostMapping("/user")
    public @ResponseBody String createUser(@RequestBody User user) {
        System.out.println(user);
        userList.put(user.getUserName(), user);
        return "New User "+ user.getUserName() + " created with email: " + user.getEmail();
    }

    @GetMapping("/user/{userName}")
    public @ResponseBody User getUser(@PathVariable String userName) {
        User user = userList.get(userName);
        return user;
    }

    @GetMapping("/user/{userName}/devKey")
    public @ResponseBody String getUserKeys(@PathVariable String userName){
        User user = userList.get(userName);
        return user.getPublicKey() + user.getSecretKey();
    }

    @PutMapping("/user/{userName}")
    public @ResponseBody User updateUser(@PathVariable String userName, @RequestBody User u) {
        userList.replace(userName, u);
        return userList.get(userName);
    }

    @PutMapping("user/{userName}/{song}")
    public @ResponseBody String addSong(@PathVariable String userName, @PathVariable String song){
        User u = userList.get(userName);
        u.addLikedSong(new Song(song));
        return song + " has been added to " + userName + "'s liked songs";
    }

    @DeleteMapping("/user/{userName}")
    public @ResponseBody HashMap<String, User> deleteUser(@PathVariable String userName) {
        userList.remove(userName);
        return userList;
    }
}

