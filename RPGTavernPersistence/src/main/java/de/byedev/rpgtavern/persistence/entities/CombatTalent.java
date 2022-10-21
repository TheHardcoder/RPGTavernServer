package de.byedev.rpgtavern.persistence.entities;

public class CombatTalent {
    public static final String BASE_TALENT_NAME = "base";

    public static final CombatTalent MONSTER = new CombatTalent(0,0,"Monster");

    private int attack;
    private int defence;

    private String name;

    public CombatTalent(int attack, int defence, String name) {
        this.attack = attack;
        this.defence = defence;
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
