package userData.chat.ChatRoom;

import org.springframework.beans.factory.annotation.Autowired;
import userData.users.UserRepository;

import javax.persistence.*;
import java.util.List;

@Entity
public class ChatRoom {

    @Autowired
    UserRepository userRepository;

    @Id
    private int roomId;

    private String userTwo;
    private String userOne;

    private List<String> messages;

    public ChatRoom() {};

    public ChatRoom(String userOne, String userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;



//        roomId = userOne + userTwo;




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

    public List<String> getContent() {
        return messages;
    }

    public void setContent(String content) {
        messages.add(content);
    }


}

