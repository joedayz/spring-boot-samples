package messaging.controllers;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    /**
     * Listens the /app/messaging endpoint and when a message is received, gets the user information encapsulated within it, and informs all clients
     * listening at the /topic/users endpoint that the user has joined the topic.
     * 
     * @param message the encapsulated STOMP message
     */
    @MessageMapping("/messaging")
    public void messaging(Message<Object> message) {
        // get the user associated with the message
        Principal user = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
        // notify all users that a user has joined the topic
        messageTemplate.convertAndSend("/topic/users", user.getName());
    }
}
