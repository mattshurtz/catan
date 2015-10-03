package shared.definitions;

import java.awt.Color;

public enum CatanColor {

    RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BROWN;

    private Color color;

    private String strName;

    static {
        RED.color = new Color(227, 66, 52);
        RED.strName = "red";
        ORANGE.color = new Color(255, 165, 0);
        ORANGE.strName = "orange";
        YELLOW.color = new Color(253, 224, 105);
        YELLOW.strName = "yellow";
        BLUE.color = new Color(111, 183, 246);
        BLUE.strName = "blue";
        GREEN.color = new Color(109, 192, 102);
        GREEN.strName = "green";
        PURPLE.color = new Color(157, 140, 212);
        PURPLE.strName = "purple";
        PUCE.color = new Color(204, 136, 153);
        PUCE.strName = "puce";
        WHITE.color = new Color(223, 223, 223);
        WHITE.strName = "white";
        BROWN.color = new Color(161, 143, 112);
        BROWN.strName = "brown";
    }

    public Color getJavaColor() {
        return color;
    }

    // Returns a string version of this color
    public String toString() {
        return strName;
    }
}
