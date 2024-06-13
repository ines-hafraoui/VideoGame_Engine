package game.entity;

import game.entity.Position;
import game.model.Model;
import info3.game.Grid;

import java.util.List;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.automaton.Relative_Orientation;
import game.automaton.State;

public abstract class Entity {
	protected Automate aut;
	protected Model model;
	protected Absolute_Orientation abs_or;
	protected Position position;
	protected State state;
	protected int HP;
	protected List<Automate> inventory; 
	protected List<Entity> bots;
	protected boolean explode;
	protected int index_inventory;
	protected int index_bot;
	
	public final static int FLECHE = 1;
	public final static int BOULE_FEU = 2;
	public final static int BOT = 3;
	
	public Entity(Automate a,Model m, Position p, Absolute_Orientation o) {
		aut = a;
		model = m;
		position = p;
		abs_or = o;
		state = a.getcurrentstate();
		HP = 100; 
		explode = false;
		index_inventory =0 ;
	}
	
	public Entity(Model m,  Position p, Absolute_Orientation o) {
		model = m;
		position = p;
		abs_or = o;
		HP = 100; 
		explode = false;
		index_inventory = 0;
		
	}
	
	public boolean eval_cell(Relative_Orientation dir, Category cat) {
		char response = model.get_grid().eval(dir, position.getPositionX(), position.getPositionY(), abs_or);
		if (response != 'X') return true;
		return false;
	}
	
	public float get_x() {
		return position.getPositionX();
	}
	
	public float get_y() {
		return position.getPositionY();
	}
	
	public void reduce_HP(int r) {
		HP = HP-r;
	}

	public abstract boolean do_move();

	public abstract void do_egg(int cat);
		
	public abstract boolean do_hit(Absolute_Orientation o, Category c,int porte );
	
	public  boolean do_wait(int n) {
		
		if (index_inventory== 0) 
			index_inventory = 0;
		else
			index_inventory +=n;
		return true;
		
	}
	
	// take smth from the floor 
	public abstract boolean do_pick(Category c,int distance);
	
	// throw what is in its bag at the index. It will create an entity that will be 
	// paint by the view
	public abstract Entity do_throw();
	
	
	public  void do_explode() {
		explode = true;
	}
	
	public abstract void do_power(int p);
	
	// method to fly
	public abstract boolean do_jump();
	
	
	// method for speed move
	public abstract boolean do_wizz(int factor);
	
	
	// take an automate from its bag and link it to the entity (bot)
	public abstract boolean do_get();
	
	public abstract void do_turn(Absolute_Orientation o);
	
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

	
	public boolean calcul_newSpeed(int acc_factor) {
		// TODO Auto-generated method stub
		return false;
	}
}
