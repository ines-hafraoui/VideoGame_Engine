package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.grid.Grid;

public class Head extends Entity{
	
	private Snake parent;

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

	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		return g.eval(dir, cat, x, y , orientation);
	}
}
