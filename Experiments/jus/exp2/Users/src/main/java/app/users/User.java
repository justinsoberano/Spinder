package app.users;

import org.springframework.core.style.ToStringCreator;

import java.util.HashSet;

public class User {

    public String username;
    public String firstName;
    public String lastName;
    public String password;
    public HashSet<String> friends = new HashSet<>();

    public User(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addFriend(String username) {
        this.friends.add(username);
    }

    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public HashSet<String> getFriends() {
        return this.friends;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("username", this.getUsername())
                .append("name", this.getName())
                .append("password", this.getPassword())
                .toString();
    }
}
