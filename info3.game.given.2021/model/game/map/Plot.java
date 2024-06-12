package game.map;


import game.entity.Position;

public class Plot {

    private Position position; // Position of the plot
    private Polygon borders; // List of positions defining the polygon borders of the plot
    private LandType landType; // LandType of the plot

    public Plot(Position position, LandType landType) {
        this.position = position;
        this.landType = landType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    
    public Polygon getBorders() {
        return borders;
    }

    public void setBorders(Polygon borders) {
        this.borders = borders;
    }
    
    public LandType getLandType() {
        return landType;
    }

    public void setLandType(LandType landType) {
        this.landType = landType;
    }
}
