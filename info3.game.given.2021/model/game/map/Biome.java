package game.map;

import java.util.List;

import game.entity.Position;

public class Biome {

    private Polygon borders; // List of positions defining the polygon borders of the biome
    private List<Plot> plots; // List of plots composing the biome
    private LandType landType; //default landtype to show on the background by default.
    private String name;  // Name of the Biome

    public Biome(Polygon borders, LandType landType) {
        this.borders = borders;
        this.landType = landType;
    }
    
    public Biome(Polygon borders, LandType landType, List<Plot> plots) {
        this.borders = borders;
        this.plots = plots;
        this.landType = landType;
    }

    public Position getPosition() {
        return borders.getCenter();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Polygon getBorders() {
        return borders;
    }

    public void setBorders(Polygon borders) {
        this.borders = borders;
    }

    public List<Plot> getPlots() {
        return plots;
    }

    public void setPlots(List<Plot> plots) {
        this.plots = plots;
    }
    
    public void generateBiome(int seed) {
    	
    }
    
    public LandType getLandType() {
        return landType;
    }

    public void setLandType(LandType landType) {
        this.landType = landType;
    }
    

    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append("Biome Borders: ");
        for (Position pos : borders.getVertices()) {
            description.append("(").append(pos.getPositionX()).append(", ").append(pos.getPositionY()).append(", ").append(pos.getPositionZ()).append(") ");
        }
        description.append("\nPlots: ");
        for (Plot plot : plots) {
            description.append(plot.getLandType().getName()).append(" ");
        }
        return description.toString();
    }
}
