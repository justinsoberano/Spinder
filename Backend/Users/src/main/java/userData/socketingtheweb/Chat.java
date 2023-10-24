package userData.socketingtheweb;


import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@ServerEndpoint("/{username}/chat/{friend}")
public class Chat {

    private static Map <Session, String> sessionUsernameMap = new Hashtable<>();
    private static Map <String, Session> usernameSessionMap = new Hashtable<>();

    private String username;
    private String friend_username;

    private final Logger logger = LoggerFactory.getLogger(Chat.class);

    /**
     *
     */
    public void onOpen(Session session, @PathVariable("username") String username, @PathVariable("friend") String friend_username) throws IOException {

        this.username = username;
        this.friend_username = friend_username;

        // server side log
        logger.info("[onOpen] " + username);

        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);
        sendMessage(username, "You are chatting with " + friend_username);

    }

    public void onClose(Session session) throws IOException {
        String username = sessionUsernameMap.get(session);
        logger.info("[CLOSED] " + username);

        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);


    }

    public void onMessage(Session session, String message) throws IOException {
        String username = sessionUsernameMap.get(session);
        logger.info("[onMessage]" + username + ": " + message);

        sendMessage(username, message);
        sendMessage(friend_username, message);
    }

    public void sendMessage(String username, String message) {
        try {
            usernameSessionMap.get(friend_username).getBasicRemote().sendText(message);
         } catch (IOException e) {
            logger.info("[Message Exception] " + e.getMessage());
        }
    }
}
