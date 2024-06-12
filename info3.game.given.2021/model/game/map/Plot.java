package game.map;

import java.util.List;

import game.entity.Position;

public class Plot {

    private Position position; // Position of the plot
    private Polygon borders; // List of positions defining the polygon borders of the plot
    private LandType landType; // LandType of the plot

    // Constructor to initialize the plot with its position and landType
    public Plot(Position position, LandType landType) {
        this.position = position;
        this.landType = landType;
    }

    // Getter and setter for position
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    
    // Getter and setter for borders
    public Polygon getBorders() {
        return borders;
    }

    public void setBorders(Polygon borders) {
        this.borders = borders;
    }
    
    // Getter and setter for landType
    public LandType getLandType() {
        return landType;
    }

    public void setLandType(LandType landType) {
        this.landType = landType;
    }
}
