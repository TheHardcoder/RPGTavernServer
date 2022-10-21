package de.byedev.rpgtavern.persistence.entities;

import java.util.Date;

public class Message {

    private Date date;
    private User user;
    private Combatant hero;
    private String message;
    private String masterMessage;
    private boolean generated = false;
    private boolean hidden = false;

    public Message(Date date, User user, Combatant hero, String message, boolean hidden) {
        this.date = date;
        this.user = user;
        this.hero = hero;
        this.message = message;
        this.hidden = hidden;
    }

    public Message(Date date, User user, Combatant hero, String message, boolean generated, boolean hidden) {
        this(date,user,hero,message, hidden);
        this.generated = generated;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Combatant getCombatant() {
        return hero;
    }

    public void setCombatant(Combatant hero) {
        this.hero = hero;
    }

    public String getMessage() {
        if (message.startsWith(hero.getName()) && message.length() > hero.getName().length()+1)
            return message.substring(hero.getName().length()+1,message.length());
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isGenerated() {
        return generated;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getMasterMessage() {
        return masterMessage;
    }

    public void setMasterMessage(String masterMessage) {
        this.masterMessage = masterMessage;
    }
}
