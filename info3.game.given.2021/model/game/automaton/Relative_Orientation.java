package game.automaton;

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
	
	public static boolean is_relative_orientation(Object o) {
		
		switch (o.toString().charAt(0)) {
		case HERE:
			return true;
		case FRONT:
			return true;
		case BACK:
			return true;
		case LEFT:
			return true;
		case RIGHT:
			return true;
		default:
			return false;
		}
	}
	
	

}
