package game.entity;

public class Position {
    
    private float x; 
    private float y;
    private float z;

    public Position(float x, float y, float z) {
        this.x = x; 
        this.y = y;
        this.z = z;
    }
    
    public Position(float x, float y) {
        this.x = x; 
        this.y = y;
    }
    
	public float distance(Position other) {
		float deltaX = this.x - other.getPositionX();
		float deltaY = this.y - other.getPositionY();
		float deltaZ = this.z - other.getPositionZ();
		return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
	}

    public float getPositionX() {
        return x; 
    }

    public float getPositionY() {
        return y; 
    }

    public float getPositionZ() {
        return z; 
    }

    public void setPositionX(float x) {
        this.x = x; 
    }

    public void setPositionY(float y) {
        this.y = y; 
    }

    public void setPositionZ(float z) {
        this.z = z; 
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return Float.compare(position.x, x) == 0 &&
               Float.compare(position.y, y) == 0 &&
               Float.compare(position.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y, z);
    }

}
