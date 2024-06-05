package game.entity;

public class Orientation {
	
	public char orientation;  
	public static final char NORTH = 'N';
	public static final char SOUTH = 'S';
	public static final char EAST = 'E';
	public static final char WEST = 'W';
	
	public Orientation(char orientation) {
		this.orientation = orientation; 
	}
	
	public char getOrientation() {
		return orientation;
	}
	
	public void setOrientation(char dir) {
		this.orientation = orientation; 
	}

}
