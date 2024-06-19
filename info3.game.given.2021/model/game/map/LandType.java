package game.map;


public class LandType {

    private Dimension dimension; // Dimension containing height and width
    private String name;  // Name of the LandType
    private float viscosity; //value that represents how slowed down walking on this soil
    
    
    public LandType(String name, float viscosity) {
        this.name = name;
        this.viscosity = viscosity;
    }

    public float getViscosity() {
        return viscosity;
    }

    public void setViscosity(float viscosity) {
        this.viscosity = viscosity;
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
