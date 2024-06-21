package game.automaton;

import java.util.Random;

import game.entity.Entity;

public class RandomCond implements Condition {

	public int value;
	
	public RandomCond(int v){
		value = v;
	}
	
	@Override
	public boolean eval(Entity e) {
		
		Random random = new Random();
		if (random.nextInt(Integer.MAX_VALUE) <= value) {
			return true;
		}
		return false;
	}

}
