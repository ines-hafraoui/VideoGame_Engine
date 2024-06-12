package game.map;

import game.entity.Position;

public class LandType {

    private Position position;  // Position containing x, y, and z
    private Dimension dimension; // Dimension containing height and width
    private String name;  // Name of the LandType

    // Constructor to initialize all variables
    public LandType(Position position, Dimension dimension, String name) {
        this.position = position;
        this.dimension = dimension;
        this.name = name;
    }

    // Getter and setter for position
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    // Getter and setter for dimension
    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Method to get a description of the LandType
    public String getDescription() {
        return "LandType: " + name + ", Position: (" + position.getPositionX() + ", " + position.getPositionY() + ", " + position.getPositionZ() + "), " +
               "Dimension: (" + dimension.getWidth() + "x" + dimension.getHeight() + ")";
    }
}
