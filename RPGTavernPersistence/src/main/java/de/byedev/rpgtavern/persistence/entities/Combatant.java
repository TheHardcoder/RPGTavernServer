package de.byedev.rpgtavern.persistence.entities;

public interface Combatant {

    String getName();

    int getInitiative();

    int getMaxLife();

    void setCurrentLife(int life);

    int getCurrentLife();

    int getAT();

    int getPA();
}
