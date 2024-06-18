package game.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.entity.Position;
import game.map.LandTypes.Lava;

public class Biome {

    private Polygon borders; // List of positions defining the polygon borders of the biome
    private List<Plot> plots = new ArrayList<Plot>(); // List of plots composing the biome
    
    private List<LandType> landTypeList = new ArrayList<LandType>();
    
    
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
    
    
    public List<LandType> getLandTypeList() {
        return landTypeList;
    }

    public void addLandType(LandType lt) {
        this.landTypeList.add(lt);
    }
    
    public void generateBiome(int seed) {
        Random random = new Random(seed);

        // Define possible biomes names
        List<String> possibleBiomeNames = new ArrayList<>();
        possibleBiomeNames.add("Volcano");
        // Add other biome names here
        
        // Randomly assign a biome type
        int biomeTypeIndex = random.nextInt(possibleBiomeNames.size());
        String biomeType = possibleBiomeNames.get(biomeTypeIndex);

        switch (biomeType) {
            case "Volcano":
                this.name = "Volcano";
                this.landType = new Lava();
                addLandType(new Lava());
                break;
            // Add other cases for different biomes
        }

        // Generate plots within the biome
        int numPlots = random.nextInt(10) + 1; // Generate between 1 and 10 plots
        for (int i = 0; i < numPlots; i++) {
            Plot plot = new Plot(landType);

            int plotLandTypeIndex = random.nextInt(landTypeList.size());
            plot.setLandType(landTypeList.get(plotLandTypeIndex));
            
            
            Polygon b = new Polygon();
            for (Position v : borders.generatePointsInsidePolygon(plotLandTypeIndex)) {
            	b.addVertex(v);
            }
            plot.setBorders(b);
            plots.add(plot);
        }
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
