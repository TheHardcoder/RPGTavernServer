package de.byedev.rpgtavern.persistence.entities;

public enum Role {
    ADMIN("admin");

    private transient String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
