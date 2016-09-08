package model;

/**
 * Created by min-ho.noh on 2014-11-12.
 */
public class ColorObject {
    private String color;
    private String value;

    public ColorObject(String color, String value) {
        this.color = color;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setColor(String color) {

        this.color = color;
    }

    public String getValue() {

        return value;
    }

    public String getColor() {

        return color;
    }
}
