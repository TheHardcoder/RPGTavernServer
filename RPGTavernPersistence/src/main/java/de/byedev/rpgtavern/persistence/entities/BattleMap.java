package de.byedev.rpgtavern.persistence.entities;

import java.util.ArrayList;
import java.util.List;

public class BattleMap {

    public static final BattleMap NO_MAP = new BattleMap("No Map", User.ANONYMOUS);
    public static final BattleMap TOKEN_MAP = new BattleMap("Token Map", User.ANONYMOUS);

    private String name;

    private User owner;

    private List<BattleObject> objects = new ArrayList<>();

    public BattleMap(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public List<BattleObject> getObjects() {
        return objects;
    }
}
