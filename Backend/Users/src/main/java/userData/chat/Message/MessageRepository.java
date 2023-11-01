package userData.chat.Message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<ChatRoom, Long>{

    ChatRoom findById(int id);

}
