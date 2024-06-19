package game.entity;

public class HitBox {
	
	private float x; 
    private float y;

    public HitBox(float x, float y) {
        this.x = x; 
        this.y = y;
    }
    

    public float getWidth() {
        return x; 
    }

    public float getHeight() {
        return y; 
    }

    public void setWidth(float x) {
        this.x = x; 
    }

    public void setHeight(float y) {
        this.y = y; 
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HitBox hb = (HitBox) obj;
        return Float.compare(hb.x, x) == 0 &&
               Float.compare(hb.y, y) == 0;
    }


}
