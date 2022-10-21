package de.byedev.rpgtavern.persistence.entities;

import java.util.Arrays;

public enum BattleObjectType {
    TREE("tree"),
    TREE_PINE("tree_pine"),
    PLAYER("player"),
    BLOCK("block");

    private transient String name;

    BattleObjectType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BattleObjectType getTypeByName(String name) {
        return Arrays.stream(BattleObjectType.values()).filter(bot -> bot.getName().equals(name)).findAny().orElse(BLOCK);
    }
}
