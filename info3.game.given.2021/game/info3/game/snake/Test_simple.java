package info3.game.snake;

import game.automaton.*;
import game.entity.*;


public class Test_simple {

	public void main() {
		
		
		State s1 = new State();
		State s2 = new State();
		Transition t1 = new Transition(s2, true, );
		Transition t2 = new Transition(s1, true, );
		Automate aut = new Automate()
		Orientation orientation = new Orientation('E');
		Grid grille = new Grid(5,5);
		
		Snake serpent = new Snake(aut,orientation,grille,next,1,0,0);
	}
	
}
