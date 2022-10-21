package de.byedev.rpgtavern.persistence.entities;

public class BattleObject {

    private int x, y, z;

    private int width, length, height;

    private String name;

    private String color;

    private BattleObjectType type;

    public BattleObject(int x, int y, int z, String name, String color, BattleObjectType type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
        this.color = color;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public BattleObjectType getType() {
        return type;
    }
}
