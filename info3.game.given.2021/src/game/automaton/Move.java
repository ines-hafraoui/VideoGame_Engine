package game.automaton;

import game.entity.Entity;

public class Move implements Action{

	Entity e;
	
	public Move(Entity e) {
		this.e = e;
	}
	
	@Override
	public boolean exec(Entity e) {
		// TODO Auto-generated method stub
		return e.do_move();
	}

}
