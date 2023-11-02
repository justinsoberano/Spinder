package userData.chat.ChatRoom;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Entity
public class ChatRoom {

    @Id
    private int id;
    private String userTwo;
    private String userOne;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String[]> messages = new ArrayList<String[]>();

    public ChatRoom() {}

    public ChatRoom(int id, String userOne, String userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserOne() {
        return userOne;
    }

    public void setUserOne(String username) {
        this.userOne = username;
    }

    public void setUserTwo(String username) {
        this.userTwo = username;
    }

    public String getUserTwo() {
        return userTwo;
    }

    public List<String[]> getMessages() {
        return messages;
    }

    public void setContent(String user, String content) {
        messages.add(new String[]{user, content});
    }
}

