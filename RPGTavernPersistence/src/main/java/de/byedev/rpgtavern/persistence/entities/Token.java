package de.byedev.rpgtavern.persistence.entities;

public class Token {

    private String color;
    private String text;
    private boolean round;

    public Token(){

    }

    public Token(String color, String text, boolean round) {
        this.color = color;
        this.text = text;
        this.round = round;
    }

    public String getColor() {
        return color;
    }

    public String getText() {
        return text;
    }

    public boolean isRound() {
        return round;
    }
}
