package game.automaton;

public class Relative_Orientation {
	
	/*
	 * this class is here for the Condition.
	 */
	
	public String rel_or;  
	
	public static final String HERE = "H";
	public static final String FRONT = "F";
	public static final String BACK = "B";
	public static final String LEFT = "L";
	public static final String RIGHT = "R";
	
	public Relative_Orientation(String orientation) {
		rel_or = orientation; 
	}
	
	public String get_rel_Orientation() {
		return rel_or;
	}
	
	public void set_rel_Orientation(String orientation) {
		rel_or = orientation; 
	}
	
	public static boolean is_relative_orientation(Object o) {
		
		switch (o.toString()) {
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
