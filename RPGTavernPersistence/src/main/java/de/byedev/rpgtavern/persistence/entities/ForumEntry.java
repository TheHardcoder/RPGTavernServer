package de.byedev.rpgtavern.persistence.entities;

import java.util.ArrayList;
import java.util.List;

public class ForumEntry {

    private String title;
    private List<ForumMessage> messages = new ArrayList<>();
    private User creator;
    private Status status = Status.OPEN;
    private long created;

    public ForumEntry(String title, User creator) {
        this.title = title;
        this.creator = creator;
        created = System.currentTimeMillis();
    }

    public String getTitle() {
        return title;
    }

    public List<ForumMessage> getMessages() {
        return messages;
    }

    public User getCreator() {
        return creator;
    }

    public long getCreated() {
        return created;
    }

    public static enum Status {
        OPEN, CLOSED;
    }
}
