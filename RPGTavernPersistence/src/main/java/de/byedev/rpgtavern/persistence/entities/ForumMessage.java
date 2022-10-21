package de.byedev.rpgtavern.persistence.entities;

public class ForumMessage {

    private User user;
    private String message;
    private long created;
    private long lastModified;

    public ForumMessage(User user, String message) {
        this.user = user;
        this.message = message;
        created = System.currentTimeMillis();
        lastModified = created;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        lastModified = System.currentTimeMillis();
    }

    public long getCreated() {
        return created;
    }

    public long getLastModified() {
        return lastModified;
    }
}
