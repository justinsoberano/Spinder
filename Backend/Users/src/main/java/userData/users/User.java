package userData.users;

import javax.persistence.*;

import userData.stations.Station;
import java.util.List;

@Entity
public class User {

    @Id
    private int id;
    private String userName;
    private String profileStr; // text associated with a profile (bio, ect.)
    private String profilePicture; // url for pfp
    private String accessKey; // key for access to spotify data for this user
    @OneToMany
    private List<Station> stations;
    @OneToMany
    private List<User> followers; //ask could be friend id's
    @OneToMany
    private List<User> following;

    public User() {
    }

    //
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this. userName = userName;
    }

    public String getProfileStr(){
        return profileStr;
    }

    public void setProfileStr(String profileStr) {
        this.profileStr = profileStr;
    }

    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    public String getProfilePicture(){ return this.profilePicture; }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void addStation(Station S) { this.stations.add(S); }

    public void removeStation(Station S) { this.stations.remove(S); }

    public void addFollower(User F){
        this.followers.add(F);
    }

    public void removeFollower(User F){
        this.followers.remove(F);
    }

    public void addFollowing(User F){
        this.following.add(F);
    }

    public void removeFollwing(User F){
        this.following.remove(F);
    }


}
