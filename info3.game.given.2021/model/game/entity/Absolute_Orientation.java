package game.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

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
	
	public static List<String> orientations = new ArrayList<>(Arrays.asList(
            Absolute_Orientation.NORTH,
            Absolute_Orientation.SOUTH,
            Absolute_Orientation.EAST,
            Absolute_Orientation.WEST,
            Absolute_Orientation.NORTH_E,
            Absolute_Orientation.NORTH_W,
            Absolute_Orientation.SOUTH_E,
            Absolute_Orientation.SOUTH_W));

	public Absolute_Orientation(String orientation) {
		abs_or = orientation;
	}

	public String get_abs_Orientation() {
		return abs_or;
	}

	public void set_abs_Orientation(String orientation) {
		abs_or = orientation;
	}
    public void set_abs_Angle(float angle) {
        angle = angle % 360;
        if (angle < 0) {
            angle += 360;
        }

        if ((angle >= 337.5 && angle < 360) || (angle >= 0 && angle < 22.5)) {
            abs_or = EAST;
        } else if (angle >= 22.5 && angle < 67.5) {
            abs_or = SOUTH_E;
        } else if (angle >= 67.5 && angle < 112.5) {
            abs_or = SOUTH;
        } else if (angle >= 112.5 && angle < 157.5) {
            abs_or = SOUTH_W;
        } else if (angle >= 157.5 && angle < 202.5) {
            abs_or = WEST;
        } else if (angle >= 202.5 && angle < 247.5) {
            abs_or = NORTH_W;
        } else if (angle >= 247.5 && angle < 292.5) {
            abs_or = NORTH;
        } else if (angle >= 292.5 && angle < 337.5) {
            abs_or = NORTH_E;
        }
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

		case (NORTH_E):
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

	public static boolean is_absolute_orientation(Object o) {
		switch (o.toString()){
		case NORTH:
			return true;
		case SOUTH:
			return true;
		case EAST:
			return true;
		case WEST:
			return true;
		case NORTH_E:
			return true;
		case NORTH_W:
			return true;
		case SOUTH_E:
			return true;
		case SOUTH_W:
			return true;
		}
		return false;
	}
	
	private static Random random = new Random();
	
	public static Absolute_Orientation randomOrientation() {

        int index = random.nextInt(orientations.size());
        String newOr = orientations.remove(index);
        return new Absolute_Orientation(newOr);
    }

	public static void setListOrientation() {
		orientations = new ArrayList<>(Arrays.asList(
	            Absolute_Orientation.NORTH,
	            Absolute_Orientation.SOUTH,
	            Absolute_Orientation.EAST,
	            Absolute_Orientation.WEST,
	            Absolute_Orientation.NORTH_E,
	            Absolute_Orientation.NORTH_W,
	            Absolute_Orientation.SOUTH_E,
	            Absolute_Orientation.SOUTH_W));
		
	}

}
