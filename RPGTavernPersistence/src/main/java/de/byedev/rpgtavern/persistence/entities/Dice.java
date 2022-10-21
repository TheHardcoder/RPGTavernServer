package de.byedev.rpgtavern.persistence.entities;

import java.security.SecureRandom;
import java.util.Random;

public class Dice {
    private static final Random random = new SecureRandom();

    private int size;

    private int lastRoll;

    public Dice(int size) {
        this.size = size;
    }

    public int roll()
    {
        lastRoll = random.nextInt(size)+1;
        return lastRoll;
    }

    public int getLastRoll() {
        return lastRoll;
    }
}
