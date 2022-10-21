package de.byedev.rpgtavern.persistence.entities;

import java.util.ArrayList;
import java.util.List;

public enum Forum {
    RPG("Rollenspiel"),
    BUGS_AND_FEATURES("Bugs und Features"),
    OFF_TOPIC("Dies und Das");

    private List<ForumEntry> entries = new ArrayList<>();
    private transient String title;

    private Forum(String title) {
        this.title = title;
    }

    public List<ForumEntry> getEntries() {
        return entries;
    }

    public String getTitle() {
        return title;
    }
}
