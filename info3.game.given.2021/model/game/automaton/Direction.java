package game.automaton;

public class Direction {
	public int percent = Integer.MAX_VALUE;

	public char dir;  
	public static final char FRONT = 'F';
	public static final char RIGHT = 'R';
	public static final char LEFT = 'L';
	public static final char HERE = 'H';
	
	public Direction(char direction) {
		dir = direction; 
	}
	
	public char getDirection() {
		return dir;
	}
	
	public void setDirection(char dir) {
		this.dir = dir; 
	}
	

}
