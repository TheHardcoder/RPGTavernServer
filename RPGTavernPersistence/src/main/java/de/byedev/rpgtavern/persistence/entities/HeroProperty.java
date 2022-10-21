package de.byedev.rpgtavern.persistence.entities;

public class HeroProperty implements Rollable {

    public static final transient HeroProperty NONE = new HeroProperty(PropertyNames.NONE,0);

    private PropertyNames name;
    private int value;

    public HeroProperty(PropertyNames name, int value) {
        this.name = name;
        this.value = value;
    }

    public PropertyNames getNames() {
        return name;
    }

    public String getName() {
        return name.getName();
    }

    public void setName(PropertyNames name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int getAbilityValue() {return 0;}

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Dice[] getDice() {
        if (name.equals(PropertyNames.INITIATIVE))
            return new Dice[]{new Dice(6)};
        return new Dice[]{new Dice(20)};
    }

    @Override
    public PropertyNames getProp(int n) {
        return name;
    }

    @Override
    public String toString() {
        return name.getName() + " " + value;
    }
}
