package game.entity;

public class Head extends Entity{
	
	private Snake parent;
	private Orientation orientation;
	

	public Head (Snake parent, Orientation orientation, Automate aut, Grid grid) {
		this.aut=aut;
		this.g = grid;
		this.parent=parent;
		this.orientation=orientation;
		super.x=0;
		super.y=0;
	}
	
	public Snake get_parent() {
		return parent;
	}
	
	public Orientation get_orientation() {
		return orientation;
	}
	
	public int get_x() {
		return x;
	}
	
	public int get_y() {
		return y;
	}
}
