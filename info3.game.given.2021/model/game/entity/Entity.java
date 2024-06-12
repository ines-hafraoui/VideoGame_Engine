package game.entity;

import game.entity.Position;
import game.model.Model;
import info3.game.Grid;
import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.automaton.State;

public abstract class Entity {
	protected Automate aut;
	protected Model model;
	protected Absolute_Orientation abs_or;
	protected Position position;
	protected State state;
	protected int HP;
	
	public Entity(Automate a,Model m, Position p, Absolute_Orientation o) {
		aut = a;
		model = m;
		position = p;
		abs_or = o;
		state = a.getcurrentstate();
		HP = 100; 
	}
	
	public Entity(Model m,  Position p, Absolute_Orientation o) {
		model = m;
		position = p;
		abs_or = o;
		HP = 100; 
	}
	
	public abstract boolean eval_cell(Direction dir, Category cat);
	
	public int get_x() {
		return position.x;
	}
	
	public int get_y() {
		return position.y;
	}
	
	public void reduce_HP(int r) {
		HP = HP-r;
	}

	public abstract boolean do_move();

	public abstract boolean do_egg();
	
	public abstract boolean do_turn();
	
	public abstract boolean do_hit();
	
	public abstract boolean do_wait();
	
	// take smth from the floor 
	public abstract boolean do_pick();
	
	// throw what is in its hand
	public abstract boolean do_throw();
	
	//stock the entity in its bag
	public abstract boolean do_store();
	
	// take an entity from its bag
	public abstract boolean do_get(int index);
	
	public abstract boolean do_explode();
	
	public abstract boolean do_power();
	
	// method to fly
	public abstract boolean do_jump();
	
	
	// method for speed move
	public abstract boolean do_wizz();
	
	public  boolean do_turn(Absolute_Orientation o) {
		abs_or = o;
		return true;
	}
	
	/*
	 * set the automate of the entity
	 * can use this methode to change the automate
	 */
	
	public void set_automate(Automate a) {
		aut = a;
		state = a.getcurrentstate();
	}
	
	public void get_state(Automate a) {
		state = a.getcurrentstate();
	}
	
	/* 
	 * return the automate of the entity
	 */
	
	public Automate get_automate() {
		return aut;
	}
	
	/*
	 * méthode qui renvoie la list des entités qui compose cette entité 
	 */
	
	public Entity[] get_entity() {
		Entity[] eList = new Entity[1];
		eList[0] = this;
		
		return eList;
	}
	
	public void tick(long elpased) {
		
		System.out.print("TICK in " + this + "\n" );
		if (aut != null) {
			aut.step(this);
		}
	}
}
