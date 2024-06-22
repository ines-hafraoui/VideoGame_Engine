package game.automaton;

import game.entity.Entity;

public class Conjonction extends Condition {
	
	Condition m_c1, m_c2;
	

	public Conjonction(Condition c1, Condition c2) {
		m_c1 = c1;
		m_c2 = c2;
	}

	@Override
	public boolean eval(Entity e) {
		return m_c1.eval(e) && m_c2.eval(e);
	}

}
