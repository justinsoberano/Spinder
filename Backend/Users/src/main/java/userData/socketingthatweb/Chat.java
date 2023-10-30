package userData.socketingthatweb;


import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint("/{username}/chat/{friend}")
@Component
public class Chat {

    /**
     * Creates a session searchable by session
     */
    private static Map <Session, String> chatSession = new Hashtable<>();

    /**
     * Creates a session searchable by username.
     */
    private static Map <String, Session> searchChat = new Hashtable<>();

    // Username
    private String username;

    // Friend's Username
    private String friend_username;

    // Logger for terminal output and debugging
    private final Logger logger = LoggerFactory.getLogger(Chat.class);

    /**
     * Connects the user to the websocket.
     * @param session
     * @param username
     * @param friend_username
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username, @PathParam("friend") String friend_username) throws IOException {

        // Sets username
        this.username = username;

        // Sets friends username
        this.friend_username = friend_username;

        // Outputs to the terminal
        logger.info("[CONNECTED]" + username);

        // Handle the case of a duplicate username
        if (searchChat.containsKey(username)) {
            session.getBasicRemote().sendText("Username already exists");
            session.close();
        } else {

            // Puts the session into the hashmap as key and value as username.
            chatSession.put(session, username);

            // Puts the username into the hashmap as key and value as HashMap for lookup.
            searchChat.put(username, session);

            // Checks if the friend already has a chat in the HashMap. Sends Status.
            if(!searchChat.containsKey(friend_username)) {
                sendStatus(session, "Waiting for connection.");
            } else {
                sendStatus(session, "Connected");
            }
        }
    }


    /**
     * Removes the session from the HashMap when the user closes out of the session.
     * @param session
     * @throws IOException
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        String username = chatSession.get(session);
        logger.info("[DISCONNECTED] " + username);


        chatSession.remove(session);
        searchChat.remove(username);
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
        String username = chatSession.get(session);

        // Sends log to terminal.
        logger.info("[MESSAGE]" + username + ": " + message);

        // First checks if the friend exists
        if(searchChat.get(friend_username) == null) {
            sendStatus(session, "User is not connected");
        } else {

            // Sends message
            sendMessage(username, message);
        }
    }

    /**
     * Handles the message sending, called from 'onMessage'.
     * @param username
     * @param message
     */
    public void sendMessage(String username, String message) {

        // Appends the username + message into a single string.
        String m = username + ": " + message;

        // Try-Catch block required by 'sendText()'.
        try {

            // Searches for chats with the user and friend and sends the message to both of them.
            searchChat.get(username).getBasicRemote().sendText(m);
            searchChat.get(friend_username).getBasicRemote().sendText(m);

        } catch(IOException e) {
            logger.info("[EXCEPTION] " + e.getMessage());
        }
    }

    /**
     * Sends status updates to both the user and friend if available or only the current user.
     * @param session
     * @param message
     */
    public void sendStatus(Session session, String message) {

        // Appends "[SYSTEM]" before the message.
        String m = "[SYSTEM]: " + message;

        // Checks if the friend's chat session exists.
        if(searchChat.containsKey(friend_username)) {
            try {

                // Send status update to both.
                searchChat.get(username).getBasicRemote().sendText(m);
                searchChat.get(friend_username).getBasicRemote().sendText(m);
            } catch(IOException e) {
                logger.info("[EXCEPTION] " + e.getMessage());
            }
        } else {
            try {

                // Only send status update to the current session.
                session.getBasicRemote().sendText(m);
            } catch(IOException e) {
                logger.info("[EXCEPTION] " + e.getMessage());
            }
        }
    }
}
