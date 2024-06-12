package game.map;

public class Dimension {
    
    private float height;
    private float width; 
    
    public Dimension(float h, float w) {
        this.height = h; 
        this.width = w;
    }
    
    public float getWidth() {
        return width; 
    }
    
    public float getHeight() {
        return height; 
    }
    
    public void setWidth(float w) {
        this.width = w; 
    }
    
    public void setHeight(float h) {
        this.height = h; 
    }
}
