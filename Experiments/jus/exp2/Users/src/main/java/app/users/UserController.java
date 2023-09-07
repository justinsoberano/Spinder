package app.users;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {

    HashMap<String, User> users = new HashMap<>();

    /* curl -X GET localhost:8080/users */
    @GetMapping("/users")
    public @ResponseBody HashMap<String, User> getAllUsers() {
        return users;
    }

    /* {"username": "...", "firstName": "...", "lastName": "...", "password": "..."} */
    /* curl -X POST localhost:8080/create -H 'Content-type:application/json' -d '{"username": "...", "firstName": "...", "lastName": "...", "password": "..."}' */
    @PostMapping("/create")
    public @ResponseBody String createUser(@RequestBody User user) {
        if (users.containsKey(user.getUsername())) {
            return "@" + user.getUsername() + " already exists.";
        } else {
            users.put(user.getUsername(), user);
            return "New user added: @" + user.getUsername();
        }
    }

    /* curl -X GET localhost:8080/users/{username} */
    @GetMapping("/users/{username}")
    public @ResponseBody User getUser(@PathVariable String username) {
        User u = users.get(username);
        return u;
    }

    /* curl -X PUT localhost:8080/users/{username}/add/{friend} */
    @PutMapping("/users/{username}/add/{friend}")
    public @ResponseBody String addFriends(@PathVariable String username, @PathVariable String friend) {

        User u = users.get(username);

        if (u.getFriends().contains(friend)) {
            return "@" + username + " is already friends with " + "@" + friend;
        } else {
            if (users.containsKey(friend)) {
                u.addFriend(friend);
                users.get(friend).addFriend(username);
                return "@" + username + " added " + "@" + friend;
            } else {
                return "Person does not exist.";
            }
        }
    }

    /* curl -X PUT localhost:8080/users/update/{username} -H 'Content-type:application/json' -d '{"username": "...", "firstName": "...", "lastName": "...", "password": "..."}' */
    @PutMapping("/users/update/{username}")
    public @ResponseBody User updateUser(@PathVariable String username, @RequestBody User u) {
        users.replace(username, u);
        return users.get(username);
    }

    /* curl -X DELETE localhost:8080/users/delete/{username} */
    @DeleteMapping("/users/delete/{username}")
    public @ResponseBody HashMap<String, User> deleteUser(@PathVariable String username) {
        users.remove(username);
        return users;
    }

    /* curl -X POST localhost:8080/create/dummy */
    @PostMapping("/create/dummy")
    public @ResponseBody String createDummyUsers() {
        User a = new User("jus", "Justin", "Soberano", "12345");
        User b = new User("jdoe", "John", "Doe", "12345");
        User c = new User("minecraftluvr", "Steve", " ", "12345");

        users.put(a.getUsername(), a);
        users.put(b.getUsername(), b);
        users.put(c.getUsername(), c);

        return "Dummy users created.";
    }
}
