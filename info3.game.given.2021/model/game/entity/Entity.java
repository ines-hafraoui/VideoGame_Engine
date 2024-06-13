package game.entity;

import game.entity.Position;
import game.model.Model;
import info3.game.Grid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.automaton.State;

public abstract class Entity {
	protected Automate aut;
	protected Model model;
	protected Absolute_Orientation abs_or;
	protected State state;
	protected int HP;
	protected List<Automate> inventory;
	protected List<Entity> bots;
	protected boolean explode;

	protected Category c;

	protected Position position;
	protected Float base_speed;
	protected Float acc_speed; // accumulated speed
	protected Absolute_Orientation speed_vct_abs_or;

	public final static int FLECHE = 1;
	public final static int BOULE_FEU = 2;
	public final static int dt = 1;

	public Entity(Automate a, Model m, Position p, Absolute_Orientation o) {
		aut = a;
		model = m;
		position = p;
		abs_or = o;
		state = a.getcurrentstate();
		HP = 100;
		explode = false;
	}

	public Entity(Model m, Position p, Absolute_Orientation o) {
		model = m;
		position = p;
		abs_or = o;
		HP = 100;
		explode = false;

	}

	public static boolean haveCommonChar(String str1, String str2) {

		Set<Character> set1 = new HashSet<>();
		for (char c : str1.toCharArray()) {
			set1.add(c);
		}

		Set<Character> set2 = new HashSet<>();
		for (char c : str2.toCharArray()) {
			set2.add(c);
		}

		set1.retainAll(set2);

		return !set1.isEmpty();
	}

	protected void newSpeed() {

		if (acc_speed <= 0) {
			acc_speed = (float) 0;
			speed_vct_abs_or.set_abs_Orientation(abs_or.get_abs_Orientation());
		}

		LandType lt = model.getMap().getLandType(position);
		float BS_coeff;
		float ACC_coeff;

		float c_speed = 0;
		if (abs_or.get_abs_Orientation().equals(speed_vct_abs_or.get_abs_Orientation())) {
			BS_coeff = 0;
			ACC_coeff = 1;
		} else if (haveCommonChar(abs_or.get_abs_Orientation(), speed_vct_abs_or.get_abs_Orientation())) {
			BS_coeff = 0;
			ACC_coeff = (float) 0.5;
		} else {
			BS_coeff = -1;
			ACC_coeff = 1;
		}

		acc_speed = BS_coeff * base_speed + ACC_coeff * acc_speed;
	}

	protected Position newPosition() {
		float c_speed = 0;
		if (haveCommonChar(abs_or.get_abs_Orientation(), speed_vct_abs_or.get_abs_Orientation())) {
			c_speed = base_speed + acc_speed;
		} else {
			c_speed = 0;
		}
		
		return 

	}

	public boolean eval_cell(Direction dir, Category cat) {
		char response = model.get_grid().eval(dir, position.getPositionX(), position.getPositionY(), abs_or);
		if (response != 'X')
			return true;
		return false;
	}

	public float get_x() {
		return position.getPositionX();
	}

	public float get_y() {
		return position.getPositionY();
	}

	public void reduce_HP(int r) {
		HP = HP - r;
	}

	public abstract boolean do_move(Absolute_Orientation o);

	public abstract void do_egg(int cat);

	public abstract boolean do_hit();

	public boolean do_wait() {
		return true;

	}

	// take smth from the floor
	public abstract boolean do_pick(int distance, Category c);

	// throw what is in its bag at the index. It will create an entity that will be
	// paint by the view
	public abstract Entity do_throw(int index);

	public void do_explode() {
		explode = true;
	}

	public abstract void do_power(int p);

	// method to fly
	public abstract boolean do_jump();

	// method for speed move
	public abstract boolean do_wizz();

	// take an automate from its bag and link it to the entity (bot)
	public abstract boolean do_get(Entity e, int index);

	public abstract void do_turn(Absolute_Orientation o);

	/*
	 * set the automate of the entity can use this methode to change the automate
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

		System.out.print("TICK in " + this + "\n");
		if (aut != null) {
			aut.step(this);
		}
	}

}
