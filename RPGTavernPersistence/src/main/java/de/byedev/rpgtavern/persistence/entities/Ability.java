package de.byedev.rpgtavern.persistence.entities;

public abstract class Ability implements Rollable {

    private PropertyNames propOne;
    private PropertyNames propTwo;
    private PropertyNames propThree;

    private String name;
    private String check;
    private int value;
    private String handicap = "";

    public Ability(String name, String check, int value) {
        this.name = name;
        this.check = check;
        String[] checks = parseCheckString(check);
        propOne = PropertyNames.getByAbrv(checks[0]).orElse(PropertyNames.NONE);
        propTwo = PropertyNames.getByAbrv(checks[1]).orElse(PropertyNames.NONE);
        propThree = PropertyNames.getByAbrv(checks[2]).orElse(PropertyNames.NONE);
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getCheck() {
        return check;
    }

    @Override
    public int getAbilityValue() {
        return getValue();
    }

    public int getValue() {
        return value;
    }

    @Override
    public PropertyNames getProp(int n) {
        switch (n)
        {
            case 0:
                return propOne;
            case 1:
                return propTwo;
            case 2:
                return propThree;
            default:
                return null;
        }
    }

    @Override
    public Dice[] getDice() {
        Dice[] dice = new Dice[]{new Dice(20),new Dice(20),new Dice(20)};
        return dice;
    }

    public static String[] parseCheckString(String check)
    {
        return check.trim().substring(1,check.trim().length()-1).split("/");

    }

    @Override
    public String toString() {
        return name+check + " " + value;
    }
}
