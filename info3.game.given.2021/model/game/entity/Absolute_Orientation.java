package game.entity;

public class Absolute_Orientation {
	
	public String abs_or;  
	
	public static final String NORTH = "N";
	public static final String SOUTH = "S";
	public static final String EAST = "E";
	public static final String WEST = "W";
	public static final String NORTH_E = "NE";
	public static final String NORTH_W = "NW";
	public static final String SOUTH_E = "SE";
	public static final String SOUTH_W = "SW";
	
	
	
	public Absolute_Orientation(String orientation) {
		abs_or = orientation; 
	}
	
	public String get_abs_Orientation() {
		return abs_or;
	}
	
	public void set_abs_Orientation(String orientation) {
		abs_or = orientation; 
	}

}
