package game.entity;

public class Position {
    
    private float x; 
    private float y;
    private float z;

    // Constructor to initialize x, y, and z
    public Position(float x, float y, float z) {
        this.x = x; 
        this.y = y;
        this.z = z;
    }

    // Getter for x
    public float getPositionX() {
        return x; 
    }

    // Getter for y
    public float getPositionY() {
        return y; 
    }

    // Getter for z
    public float getPositionZ() {
        return z; 
    }

    // Setter for x
    public void setPositionX(float x) {
        this.x = x; 
    }

    // Setter for y
    public void setPositionY(float y) {
        this.y = y; 
    }

    // Setter for z
    public void setPositionZ(float z) {
        this.z = z; 
    }
}
