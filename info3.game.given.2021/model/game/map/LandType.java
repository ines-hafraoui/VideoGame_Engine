package game.map;

import game.entity.Position;

public class LandType {

    private Dimension dimension; // Dimension containing height and width
    private String name;  // Name of the LandType
    private float viscosity; //value that represents how slowed down walking on this soil
    
    
    public LandType(Dimension dimension, String name, float viscosity) {
        this.dimension = dimension;
        this.name = name;
        this.viscosity = viscosity;
    }

    public float getViscosity() {
        return viscosity;
    }

    public void setViscosity(float viscosity) {
        this.viscosity = viscosity;
    }
    
    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Method to get a description of the LandType
    public String getDescription() {
        return "Dimension: (" + dimension.getWidth() + "x" + dimension.getHeight() + ")";
    }
}
