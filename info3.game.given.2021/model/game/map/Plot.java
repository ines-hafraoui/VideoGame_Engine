package game.map;

import game.entity.Position;

public class Plot {

    private Polygon borders; // List of positions defining the polygon borders of the plot
    private LandType landType; // LandType of the plot

    public Plot(LandType landType) {
        this.landType = landType;
    }

    public Position getPosition() {
        return borders.getCenter();
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
