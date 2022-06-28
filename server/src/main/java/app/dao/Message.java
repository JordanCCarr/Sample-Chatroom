package app.dao;

import java.sql.Timestamp;

public class Message {
    private String messageContent;
    private String username;
    private Timestamp timestamp;

    public Message() {

    }

    public Message(final String message_content, final String username, final Timestamp timestamp) {
        this.messageContent = message_content;
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getUsername() {
        return username;
    }
    
    public Timestamp getTimestamp() {
        return timestamp;
    }
}
