package game.automaton;

import game.entity.Entity;

public class Got extends Condition {
	
	//quoiqu'il arrive on evalue les HP de l'entite donc inutile de mettre quelconque champ
	
	public boolean eval (Entity e) {
		return e.eval_got();
	}

}
