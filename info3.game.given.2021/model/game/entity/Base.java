package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Base extends Entity {
	
	boolean picked; 
	
	public Base(Automate a, Model m,Position p, Absolute_Orientation o ) {
		super(a,m,p,o);
		picked = false;
	}
	
	public Base(Model m,Position p, Absolute_Orientation o ) {
		super(m,p,o);
		picked = false;
	}

	@Override
	public Entity do_egg(Automate a) {
		return new Bot(a,model,position,abs_or);
	}
	
	@Override
	public boolean do_store(Entity e) {
		if (picked)
			return items.add(e);
		return false;
	}
	
	//tells the model that it has to disappear from the map
	@Override
	public boolean do_explode() {
		return model.explode(this);
	}

	@Override
	public boolean do_power() {
		return false;
	}
	
	@Override
	public boolean do_throw() {
		return false;
	}
	
	@Override
	public boolean do_move(Absolute_Orientation o) {
		return false;
	}

	@Override
	public boolean do_jump() {
		return false;
	}
	
	@Override
	public boolean do_wizz() {
		return false;
	}
	
	@Override
	public boolean do_turn() {
		return false;
	}

	@Override
	public boolean do_hit() {
		return false;
	}

	@Override
	public boolean do_pick() {
		return false;
	}
	
	

}
