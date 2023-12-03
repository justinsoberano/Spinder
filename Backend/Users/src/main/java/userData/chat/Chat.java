package userData.chat;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import userData.chat.ChatRoom.ChatRoomRepository;
import userData.chat.ChatRoom.ChatRoom;
import userData.users.UserRepository;
import userData.users.User;

@ServerEndpoint("/{userName}/chat/{friend}")
@Component
public class Chat {

    @Autowired
    public void setChatRoomRepository(ChatRoomRepository repo) { chatRepo = repo;}
    private static ChatRoomRepository chatRepo;

    @Autowired
    public void setUserRepository(UserRepository u) { userRepo = u; }
    private static UserRepository userRepo;

    private static Map <Session, String> chatSession = new Hashtable<>();

    private static Map <String, Session> searchChat = new Hashtable<>();

    private ChatRoom chat;
    private String userName;
    private String friendUsername;

    // Logger for terminal output and debugging
    private final Logger logger = LoggerFactory.getLogger(Chat.class);

    /**
     * Connects the user to the websocket.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String userName, @PathParam("friend") String friendUsername) throws IOException {

        this.userName = userName;
        this.friendUsername = friendUsername;

        /* Finds the id of each user and adds them together for the ChatRoom Id */
         User u1 = userRepo.findByUserName(userName);
         User u2 = userRepo.findByUserName(friendUsername);
         if(u1 == null || u2 == null){
             return;
         }

         chatSession.put(session, userName);
         searchChat.put(userName, session);

        if(chatRepo.findByUserOneAndUserTwo(userName, friendUsername) == null) {
            logger.info("[CHAT CREATED]");
            chat = new ChatRoom(userName, friendUsername);
            chatRepo.save(chat);
        } else {
            logger.info("[CHAT OPENED]");
            chat = chatRepo.findByUserOneAndUserTwo(userName, friendUsername);
            loadHistory(session, chat);
        }
    }


    /**
     * Removes the session from the HashMap when the user closes out of the session.
     * @param session
     * @throws IOException
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        String userName = chatSession.get(session);
        logger.info("[DISCONNECTED] " + userName);

        chatSession.remove(session);
        searchChat.remove(userName);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {

        // get the userName from session-userName mapping
        String userName = chatSession.get(session);

        // do error handling here
        logger.info("[onError]" + userName + ": " + throwable.getMessage());
    }


    /**
     * Connects the current session and sends the message.
     * @param session
     * @param message
     * @throws IOException
     */

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        // Grabs the username of the current session.
        String sender = chatSession.get(session);

        // Log the message to the terminal.
        logger.info("[MESSAGE] " + sender + ": " + message);

        // Send the message to the sender, so they see their own message.
        sendMessage(session, sender, message);

        // If the friend is different and connected, send them the message.
        if(!sender.equals(friendUsername)) {
            Session friendSession = searchChat.get(friendUsername);
            if(friendSession != null) {
                sendMessage(friendSession, sender, message);
            }
        }

        // Store the message in the chat history.
        chat.setContent(sender, message);
        chatRepo.save(chat);
    }

    /**
     * Handles the message sending, called from 'onMessage'.
     * @param message
     */
    public void sendMessage(Session session, String user, String message) throws IOException {
        String formattedMessage = user + ": " + message;
        session.getBasicRemote().sendText(formattedMessage);
    }

    /**
     * Allows previous messages to be sent to the users.
     */
    void loadHistory(Session session, ChatRoom chat) throws IOException {
        // Initialize the messages collection if it is lazy-loaded
        if (chat.getMessages() != null && Hibernate.isInitialized(chat.getMessages())) {
            for(int i = 0; i < chat.getMessages().size(); i++) {
                session.getBasicRemote().sendText(chat.getMessages().get(i).getUser() + ": " + chat.getMessages().get(i).getContent());
            }
        }
    }

    void getRoom(String u1, String u2){
        chatRepo.findByUserOneAndUserTwo(u1, u2);
    }
}
