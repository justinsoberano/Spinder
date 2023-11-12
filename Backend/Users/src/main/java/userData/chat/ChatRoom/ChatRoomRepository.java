package userData.chat.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import userData.users.User;

/**
 * This class represents a chat room between two users.
 */
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{

    /**
     * Returns the chat room between two users.
     * @param userOne the first user
     * @param userTwo the second user
     * @return the chat room between the two users
     */
    ChatRoom findByUserOneAndUserTwo(String userOne, String userTwo);

    /**
     * Returns the chat room between two users.
     * @param id the id of the chat room
     * @return the chat room between the two users
     */
    ChatRoom findById(int id);

    /**
     * Returns whether the chat room exists.
     * @param id the id of the chat room
     * @return whether the chat room exists
     */
    boolean existsById(int id);

}
