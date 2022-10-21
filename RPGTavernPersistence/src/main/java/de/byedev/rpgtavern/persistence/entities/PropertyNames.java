package de.byedev.rpgtavern.persistence.entities;

import java.util.Arrays;
import java.util.Optional;

public enum PropertyNames {
    COURAGE("Mut", "MU"), WISDOM("Klugheit","KL"), INTUITION("Intuition","IN"),
    CHARISMA("Charisma","CH"), DEXTERITY("Fingerfertigkeit","FF"),
    AGILITY("Gewandtheit","GE"), CONSTITUTION("Konstitution","KO"),
    STRENGTH("Körperkraft","KK"), MAGIC_RESISTENZ("Magieresistenz","MR"),
    LIFE("Lebensenergie","LeP"), ENDURANCE("Ausdauer","AuP"),
    MAGIC_ENERGY("Astralenergie","ASP"), KARMA("Karmaenergie","KE"),
    SOCIAL_STANDING("Sozialstatus","SO"), BASE_ATTACK("AT","AT"),
    BASE_DEFENCE("PA","PA"),BASE_RANGED_AT("FK","FK"),
    INITIATIVE("ini","INI"), NONE("none","--"),
    DODGE("Ausweichen", "Aw");

    private transient String name;
    private transient String abrv;

    public String getAbrv() {
        return abrv;
    }

    @Override
    public String toString() {
        return name;
    }

    PropertyNames(String name, String abrv) {
        this.name = name;
        this.abrv = abrv;
    }

    public String getName() {
        return name;
    }

    public static Optional<PropertyNames> getByName(String name)
    {
        return Arrays.stream(PropertyNames.values()).filter(names -> names.getName().toLowerCase().equals(name.toLowerCase())).findAny();
    }

    public static Optional<PropertyNames> getByAbrv(String abrv)
    {
        return Arrays.stream(PropertyNames.values()).filter(names -> names.getAbrv().toLowerCase().equals(abrv.toLowerCase())).findAny();
    }
}
