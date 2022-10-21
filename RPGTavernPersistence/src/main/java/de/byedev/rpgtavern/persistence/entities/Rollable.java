package de.byedev.rpgtavern.persistence.entities;

public interface Rollable {

    Dice[] getDice();

    PropertyNames getProp(int n);

    String getName();

    int getAbilityValue();
}
