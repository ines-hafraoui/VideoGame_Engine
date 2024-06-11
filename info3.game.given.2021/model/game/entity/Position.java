package game.entity;

public class Position {
	
	int x; 
	int y;
	
	public Position(int x, int y) {
		this.x = x; 
		this.y = y;
	}
	
	public int getPositionX() {
		return x; 
	}
	
	public int getPositionY() {
		return y; 
	}
	
	public void SetPositionX(int x) {
		this.x = x; 
	}
	
	public void SetPositionY(int y) {
		this.y = y; 
	}

}
