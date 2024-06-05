package game.automaton;

public class Direction {
	
	public char dir;  
	public static final char FRONT = 'F';
	public static final char RIGHT = 'R';
	public static final char LEFT = 'L';
	
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
