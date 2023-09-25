package repositories.Users;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import onetoone.Laptops.Laptop;

@Entity
public class User {

    @Id
    private int id;
    private String userName;
    private String profileStr;
    private String accessKey;
    private List<User> followers; //ask
    private List<User> following;


    public User(User L) {
        this = L;
        Followers = new ArrayList<User>();
        Following = new ArrayList<User>();
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
