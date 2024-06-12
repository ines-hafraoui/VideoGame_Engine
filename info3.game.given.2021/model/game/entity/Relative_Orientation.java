package game.entity;

public class Relative_Orientation {
	
	/*
	 * this class is here for the Condition.
	 */
	
	public char rel_or;  
	
	public static final char HERE = 'H';
	public static final char FRONT = 'F';
	public static final char BACK = 'B';
	public static final char LEFT = 'L';
	public static final char RIGHT = 'R';
	
	public Relative_Orientation(char orientation) {
		rel_or = orientation; 
	}
	
	public char get_rel_Orientation() {
		return rel_or;
	}
	
	public void set_rel_Orientation(char orientation) {
		rel_or = orientation; 
	}
	
	

}
