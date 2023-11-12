package userData.chat.ChatRoom;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * This class represents a chat room between two users.
 */
@Entity
public class ChatRoom {

    @Id
    /**
     * The id of the chat room.
     */
    private int id;

    /**
     * The two users in the chat room.
     */
    private String userTwo;

    /**
     * The two users in the chat room.
     */
    private String userOne;

    /**
     * The messages in the chat room.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();

    /**
     * Creates a new chat room.
     */
    public ChatRoom() {}

    /**
     * Creates a new chat room.
     * @param userOne: the first user
     * @param userTwo: the second user
     */
    public ChatRoom( String userOne, String userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    /**
     * Returns the id of the chat room.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the chat room.
     * @param id: the id of the chat room
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the first user in the chat room.
     * @return userOne
     */
    public String getUserOne() {
        return userOne;
    }

    /**
     * Sets the first user in the chat room.
     * @param username the first user in the chat room
     */
    public void setUserOne(String username) {
        this.userOne = username;
    }

    /**
     * Sets the second user in the chat room.
     * @param username the second user in the chat room
     */
    public void setUserTwo(String username) {
        this.userTwo = username;
    }

    /**
     * Returns the second user in the chat room.
     * @return userTwo
     */
    public String getUserTwo() {
        return userTwo;
    }

    /**
     * Returns the messages in the chat room.
     * @return messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Sets the messages in the chat room.
     * @param user the user who sent the message
     * @param content the content of the message
     */
    public void setContent(String user, String content) {
        messages.add(new Message(user, content));
    }

    /**
     * This class represents a message in a chat room.
     */
    @Embeddable
    public class Message {

        /**
         * The user who sent the message.
         */
        private String user;

        /**
         * The content of the message.
         */
        private String content;

        /**
         * Creates a new message.
         * @param user the user who sent the message
         * @param content the content of the message
         */
        public Message(String user, String content) {
            this.user = user;
            this.content = content;
        }

        /**
         * Creates a new message.
         */
        public Message() {}

        /**
         * Returns the user who sent the message.
         * @return user
         */
        public String getUser() { return user; }

        /**
         * Returns the content of the message.
         * @return content
         */
        public String getContent() { return content; }
    }
}

