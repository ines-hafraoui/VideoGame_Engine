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

	/*
	 * When looking back to this fucntion don't forget that 
	 * the Y axis on the map is inverted. going up on the screen is going down onthe referential.
	 * 
	 * I prefered to keep the math as usual and simply say that north is south and south is north.
	 */
	public int get_abs_Angle() {
		switch (abs_or) {
		case (NORTH):
			return 270;

		case (SOUTH):
			return 90;

		case (EAST):
			return 0;

		case (WEST):
			return 180;

		case (NNORTH_E):
			return 315;

		case (NORTH_W):
			return 225;

		case (SOUTH_W):
			return 135;

		case (SOUTH_E):
			return 45;

		}
		return 0;
	}

}
