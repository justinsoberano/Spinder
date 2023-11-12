package userData.users;

import javax.persistence.*;

import userData.stations.Station;
import java.util.List;

/**
 * User class, used to store user data
 */
@Entity
public class User {

    /**
     * User ID
     */
    @Id
    private int id;

    /**
     * Username
     */
    private String userName;

    /**
     * User password
     */
    private String password;

    /**
     * User bio
     */
    private String profileStr;

    /**
     * User profile picture
     */
    private String profilePicture;

    /**
     * User access key for Spotify API
     */
    private String accessKey;

    /**
     * User stations
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id")
    private Station station;

    /**
     * User friends
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<User> friends;

    /**
     * Default constructor
     */
    public User() {}


    /**
     * Constructor for User
     * @param id user id
     * @param userName user name
     * @param profileStr user bio
     * @param profilePicture user profile picture
     * @param accessKey user access key for Spotify API
     */
    public User(int id, String userName, String profileStr, String profilePicture, String accessKey){
        this.id = id;
        this.userName = userName;
        this.profileStr = profileStr;
        this.profilePicture = profilePicture;
        this.accessKey = accessKey;
    }

    /**
     * Constructor for User
     * @return user id
     */
    public int getId(){
        return id;
    }

    /**
     * Sets user id
     * @param id user id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Gets username
     * @return user name
     */
    public String getUserName(){
        return userName;
    }

    /**
     * Sets username
     * @param userName user name
     */
    public void setUserName(String userName){
        this. userName = userName;
    }

    /**
     * Gets user password
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user password
     * @param password user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets user bio
     * @return user bio
     */
    public String getProfileStr(){
        return profileStr;
    }

    /**
     * Sets user bio
     * @param profileStr user bio
     */
    public void setProfileStr(String profileStr) {
        this.profileStr = profileStr;
    }

    /**
     * Sets user profile picture
     * @param profilePicture user profile picture
     */
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    /**
     * Gets user profile picture
     * @return user profile picture url
     */
    public String getProfilePicture(){ return this.profilePicture; }

    /**
     * Gets user access key for Spotify API
     * @return user access key for Spotify API
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     * Sets user access key for Spotify API
     * @param accessKey user access key for Spotify API
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    /**
     * Gets user station
     * @return user station
     */
    public Station getStation(){ return this.station; }

    /**
     * Sets user station
     * @param s user station
     */
    public void setStation(Station s){ this.station = s; }

    /**
     * Adds friend to user
     * @param f friend to add
     */
    public void addFriend(User f){
        this.friends.add(f);
    }

    /**
     * Removes friend from user
     * @param f friend to remove
     */
    public void removeFreind(User f){
        this.friends.remove(f);
    }

    /**
     * Gets user friends
     * @return user friends
     */
    public List<User> getFriends(){
        return this.friends;
    }


}
