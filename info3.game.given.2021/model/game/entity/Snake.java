package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.automaton.State;
import info3.game.Grid;

public class Snake extends Entity {

	private Head head;
	private Block corps;
	private Grid g;

	public Snake(Automate aut, Grid g, Head h) {
		super(aut, g, null, null);
		aut = aut;
		head = h;
	}

	public Snake(Grid g, Head h) {
		super(g, null, null);
		head = h;
	}

	public Block get_tail() {

		// i allow myself to cast since i'm in a snake
		// and i know it's corps is made of blocks only
		Block c_tail = corps;

		if (c_tail != null) {
			while (c_tail.get_next() != null) {
				c_tail = (Block) c_tail.get_next();
			}
		}

		return c_tail;
	}

	public void set_automate(Automate aut) {
		this.aut = aut;
	}

	@Override
	public void tick(long elapsed) {
		getHead().tick(elapsed);

		if (corps != null) {
			Block b = corps;

			while (b != null) {
				b.tick(elapsed);
				b = (Block) b.get_next();
			}
		}

	}

	@Override
	public Entity[] get_entity() {

		Entity[] eList = new Entity[length_corps() + 1];
		eList[0] = getHead();

		if (corps != null) {
			int i = 1;
			Block b = corps;

			while (b != null) {
				eList[i] = b;
				b = (Block) b.get_next();
				i++;
			}
		}

		return eList;
	}

	public int length_corps() {
		int i = 0;

		if (corps != null) {
			Block b = corps;

			while (b != null) {
				b = (Block) b.get_next();
				i++;
			}
		}

		return i;
	}

	/*
	 * eval_cell call the method eval of Grid that needs the direction and category
	 * of the condition and the coordinates + orientation of the snake
	 * 
	 * return value : boolean
	 */
	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_move() {
		
		boolean moved = false; 
		if (corps == null) {
			moved =  getHead().do_move();
		}

		Block tail = get_tail();
		Block prev = (Block)tail.get_prev();
		while(prev != null) {
			moved = tail.do_move();
		}
	
		return moved;
	}

	@Override
	public boolean do_egg() {
		if (corps == null) {
			corps = new Block(getHead(), g, getHead().position);
		}

		Block tail = get_tail();

		Block new_tail = new Block(getHead(), g, tail.position);
		tail.set_next(new_tail);
		new_tail.set_prev(tail);
		
		return true;
	}

	public Head getHead() {
		return head;
	}

}
