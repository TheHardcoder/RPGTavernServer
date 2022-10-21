package de.byedev.rpgtavern.webapi.dto;

import java.security.SecureRandom;

public class DieDTO {

    private static SecureRandom rand = new SecureRandom();

    private int size;
    private int result = -1;

    public DieDTO(int size) {
        this.size = size;
    }

    public DieDTO() {}

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void roll() {
        result = rand.nextInt(size)+1;
    }
}
