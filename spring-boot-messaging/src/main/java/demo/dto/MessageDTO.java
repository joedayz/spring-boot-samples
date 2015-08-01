package messaging.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple DTO class to encapsulate messages along with their destinations and timestamps.
 */
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Date date;
    public String content;
    public String to;
    public String from;

    public MessageDTO() {
        this.date = Calendar.getInstance().getTime();
    }
}