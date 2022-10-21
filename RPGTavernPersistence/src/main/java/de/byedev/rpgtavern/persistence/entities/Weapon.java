package de.byedev.rpgtavern.persistence.entities;

public class Weapon {

    private String name;

    private int atMod;
    private int paMod;

    private int dice;
    private int dmgBonus;

    private int iniMod;

    private String talent;

    public Weapon(String name, int atMod, int paMod, int dice, int dmgBonus, int iniMod, String talent) {
        this.name = name;
        this.atMod = atMod;
        this.paMod = paMod;
        this.dice = dice;
        this.dmgBonus = dmgBonus;
        this.iniMod = iniMod;
        this.talent = talent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtMod() {
        return atMod;
    }

    public void setAtMod(int atMod) {
        this.atMod = atMod;
    }

    public int getPaMod() {
        return paMod;
    }

    public void setPaMod(int paMod) {
        this.paMod = paMod;
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

    public int getIniMod() {
        return iniMod;
    }

    public void setIniMod(int iniMod) {
        this.iniMod = iniMod;
    }

    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    @Override
    public String toString() {
        return name +"("+dice+"d6+"+dmgBonus+")";
    }
}
