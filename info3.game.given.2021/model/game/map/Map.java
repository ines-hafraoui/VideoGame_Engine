package game.map;

import game.entity.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
	
	private int seed;
	
    private List<Biome> biomes;

    // Constructor to initialize the biomes list
    public Map() {
        this.biomes = new ArrayList<>();
    }

    // Generate a random seed
    public int generateSeed() {
        Random random = new Random();
        seed =  random.nextInt(); // Generates a random integer seed
        return seed;
    }

    
    public void setSeed(int seed) {
    	this.seed = seed;
    	
    }
    public int getSeed() {
    	return this.seed;
    }
    
    // Generate map using a seed
    public void generateMap(int seed) {
        // TODO: Implement the logic to generate the map using the given seed
    }

    public Plot getPlot(Position position) {
        Plot highestPlot = null;
        float maxZ = Float.NEGATIVE_INFINITY;

        for (Biome biome : biomes) {
            for (Plot plot : biome.getPlots()) {
            	
                if (plot.getBorders().containsPosition(position) && plot.getPosition().getPositionZ() >= maxZ) {
                	highestPlot = plot;
                	maxZ = plot.getPosition().getPositionZ();
                }
            }
        }
        return highestPlot;
    }
    
    public LandType getLandType(Position position) {
    	return getPlot(position).getLandType();
    }

    
    // Get the viscosity of the land type at a given position
    public float getViscosity(Position position) {
        
        return getLandType(position).getViscosity();
    }


}
