package game.automaton;

import game.entity.Entity;

public class Move implements Action{

	@Override
	public boolean exec(Entity e) {
		// TODO Auto-generated method stub
		return e.do_move();
	}

}
