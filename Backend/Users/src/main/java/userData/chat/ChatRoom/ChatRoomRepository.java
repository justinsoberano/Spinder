package userData.chat.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{

    ChatRoom findById(int id);
    boolean existsById(int id);

}
