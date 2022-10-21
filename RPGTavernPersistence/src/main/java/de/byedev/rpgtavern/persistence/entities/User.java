package de.byedev.rpgtavern.persistence.entities;


import de.byedev.rpgtavern.persistence.util.PasswordAuthentication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

    public static final transient User ANONYMOUS = new User("anonymous","anonymous",new char[]{});

    private String username;
    private String email;
    private String password;
    private String lastGame;
    private long lastOnline;
    private transient boolean isOnline = false;

    private List<String> quickRolls = new ArrayList<>();

    private List<Hero> heroes = new ArrayList<>();

    private List<Role> roles = new ArrayList<>();

    private List<BattleMap> battlemaps = new ArrayList<>();

    private List<Video> videos = new ArrayList<>();

    public User(String username, String email, char[] password) {
        this.username = username;
        this.email = email;
        this.password  = PasswordAuthentication.hash(password);
        quickRolls.add("#1d6");
        quickRolls.add("#1d20");
        quickRolls.add("#3d20");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = PasswordAuthentication.hash(password);
    }

    public long getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(long lastOnline) {
        this.lastOnline = lastOnline;
    }

    public boolean login(char[] password)
    {
        if (password == null)
            return false;
        return PasswordAuthentication.authenticate(password, this.password);
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void addHero(Hero hero) {
        this.heroes.add(hero);
    }

    public boolean isAnonymous() { return password == null; }

    public String getLastGame() {
        return lastGame;
    }

    public void setLastGame(String lastGame) {
        this.lastGame = lastGame;
    }

    public List<String> getQuickRolls() {
        if (quickRolls == null){
            quickRolls = new ArrayList<>();
            quickRolls.add("#1d6");
            quickRolls.add("#1d20");
            quickRolls.add("#3d20");
        }
        return quickRolls;
    }

    public List<Role> getRoles() {
        if (roles == null)
            roles = new ArrayList<>();
        return roles;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public List<BattleMap> getBattlemaps() {
        if (battlemaps == null)
            battlemaps = new ArrayList<>();
        return battlemaps;
    }

    public List<Video> getVideos() {
        if (videos == null)
            videos = new ArrayList<>();
        return videos;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", heroes=" + Arrays.toString(heroes.toArray()) +
                '}';
    }
}
