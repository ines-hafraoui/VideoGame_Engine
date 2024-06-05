package game.entity;

public class Apple extends Entity{

	private boolean eaten;
	
	public Apple (Automate aut, Grid grid) {
		this.aut=aut;
		this.g=grid;
		eaten = false;
	}
	
	public boolean eaten () {
		return eaten;
	}
	
}
