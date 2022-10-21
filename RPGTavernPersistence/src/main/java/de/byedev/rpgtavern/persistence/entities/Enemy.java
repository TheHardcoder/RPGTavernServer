package de.byedev.rpgtavern.persistence.entities;

public class Enemy implements Combatant {

    private String name;
    private int ini;
    private int maxLife;
    private int currentLife;
    private int at, pa;
    private int dice, dmgBonus;

    public Enemy(String name, int ini, int maxLife, int at, int pa, int dice, int dmgBonus) {
        this.name = name;
        this.ini = ini;
        this.maxLife = maxLife;
        this.currentLife = maxLife;
        this.at = at;
        this.pa = pa;
        this.dice = dice;
        this.dmgBonus = dmgBonus;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getInitiative() {
        return ini;
    }

    @Override
    public int getMaxLife() {
        return maxLife;
    }

    @Override
    public void setCurrentLife(int life) {
        currentLife = life;
    }

    @Override
    public int getCurrentLife() {
        return currentLife;
    }

    @Override
    public int getAT() {
        return at;
    }

    @Override
    public int getPA() {
        return pa;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public int getDmgBonus() {
        return dmgBonus;
    }

    public void setDmgBonus(int dmgBonus) {
        this.dmgBonus = dmgBonus;
    }
}
