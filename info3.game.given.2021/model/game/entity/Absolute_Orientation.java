package game.entity;

public class Absolute_Orientation {
	
	public char abs_or;  
	
	public static final char NORTH = 'N';
	public static final char SOUTH = 'S';
	public static final char EAST = 'E';
	public static final char WEST = 'W';
	
	
	
	public Absolute_Orientation(char orientation) {
		abs_or = orientation; 
	}
	
	public char get_abs_Orientation() {
		return abs_or;
	}
	
	public void set_abs_Orientation(char orientation) {
		abs_or = orientation; 
	}

}
