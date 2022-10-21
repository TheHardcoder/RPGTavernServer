package de.byedev.rpgtavern.persistence.entities;

import java.util.ArrayList;
import java.util.List;

public class Advantage {

    private String name;
    private String text = "";
    private List<String> additionalText = new ArrayList<>();

    public Advantage(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public int getTextAsInt() {
        try {
            return Integer.parseInt(text);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }

    public List<String> getAdditionalText() {
        if (additionalText == null)
            additionalText = new ArrayList<>();
        return additionalText;
    }
}
