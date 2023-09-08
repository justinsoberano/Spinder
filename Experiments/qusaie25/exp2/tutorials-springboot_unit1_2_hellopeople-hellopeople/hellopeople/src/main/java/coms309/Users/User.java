package coms309.Users;


import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Qusai Elwazir
 */

public class User {

    private String userName;

    private String email;

    private String password;

    private String secretKey;

    private String publicKey;

    private HashSet<Song> likedSongs = new HashSet<Song>();

    public User(){
        
    }

    public User(String userName, String email, String password, String secretKey, String publicKey){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.secretKey = secretKey;
        this.publicKey = publicKey;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setFirstName(String userName) {
        this.userName = userName;
    }

    public String getEmail() { return this.email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setTelephone(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void addLikedSong(Song s){
        this.likedSongs.add(s);
    }



    @Override
    public String toString() {
        return userName + " "
               + email;
    }
}
