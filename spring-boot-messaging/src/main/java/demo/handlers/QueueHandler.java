package messaging.handlers;

import java.util.HashMap;
import java.util.Map;

import messaging.dto.MessageDTO;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

/**
 * Receives messages from ActiveMQ and relays them to appropriate users.
 */
@Component(value = "queueHandler")
public class QueueHandler {

    @Autowired
    private SimpMessageSendingOperations msgTemplate;

    private static Map<String, Object> defaultHeaders;

    static {
        defaultHeaders = new HashMap<String, Object>();
        // add the Content-Type: application/json header by default
        defaultHeaders.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
    }

    public void handle(Exchange exchange) {
        Message camelMessage = exchange.getIn();
        MessageDTO message = camelMessage.getBody(MessageDTO.class);
        // send the message specifically to the destination user by using STOMP's user-directed messaging
        msgTemplate.convertAndSendToUser(message.to, "/topic/messages", message, defaultHeaders);
    }
}
