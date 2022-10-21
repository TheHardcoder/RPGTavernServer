package de.byedev.rpgtavern.persistence.entities;

public class MapMarker {

    private String label;
    private Character symbol;
    private String color;
    private int x, y;
    private String ownerMail;

    public MapMarker(String label, Character symbol, String color, int x, int y, String ownerMail) {
        this.label = label;
        this.symbol = symbol;
        this.color = color;
        this.x = x;
        this.y = y;
        this.ownerMail = ownerMail;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getOwnerMail() {
        return ownerMail;
    }

    public void setOwnerMail(String ownerMail) {
        this.ownerMail = ownerMail;
    }
}
