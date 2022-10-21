package de.byedev.rpgtavern.persistence.entities;

public class Emoji {
    private String value;
    private String keywords;
    private String group;
    private String subGroup;

    public Emoji(String value, String keywords, String group, String subGroup) {
        this.value = value;
        this.keywords = keywords;
        this.group = group;
        this.subGroup = subGroup;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Emoji emoji = (Emoji) o;

        if (!value.equals(emoji.value)) return false;
        if (!group.equals(emoji.group)) return false;
        return subGroup.equals(emoji.subGroup);
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + group.hashCode();
        result = 31 * result + subGroup.hashCode();
        return result;
    }
}
