package de.byedev.rpgtavern.persistence.entities;

import java.util.*;
import java.util.stream.Stream;

public class RPGRoot {

    private Map<String,Game> games;
    private List<User> users;
    private String message;
    private List<Forum> forums;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RPGRoot() {
        games = new HashMap<>();
        users = new ArrayList<>();
        forums = new ArrayList<>();
    }

    public Map<String, Game> getGames() {
        return games;
    }

    public void setGames(Map<String, Game> games) {
        this.games = games;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Stream<String> getGameNames() {
        return games.keySet().stream();
    }

    public void addGame(String name, Game game) {
        this.games.put(name, game);
    }

    public Game getGameByName(String name)
    {
        return games.get(name);
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addUsers(Collection<User> users) {
        this.users.addAll(users);
    }

    public Collection<Forum> getForums() {
        return forums;
    }

    @Override
    public String toString() {
        return "DSATableRoot{" +
                "games=" + Arrays.toString(games.values().toArray()) +
                ", message=" + message +
                ", users=" + Arrays.toString(users.toArray()) +
                '}';
    }
}
