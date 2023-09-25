package repositories.Users;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    private int id;
    private String userName;
    private String profileStr;
    private String accessKey;

    @OneToMany
    private List<User> followers; //ask
    @OneToMany
    private List<User> following;


    public User(User L) {
    }

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

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

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
