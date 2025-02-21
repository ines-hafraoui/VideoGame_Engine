package game.automaton;

import game.entity.Entity;

public class Key extends Condition {
	
	String touche; //changed Touche touche to String touche for better use in parser
		
	public static final String ARROW_UP = "FU";
	public static final String ARROW_DOWN = "FD";
	public static final String ARROW_LEFT = "FL";
	public static final String ARROW_RIGHT = "FR";
	public static final String SPACE = "SPACE";
	public static final String ENTER = "ENTER";
	public static final String INTERROGATION_MARK = "IM";
	public static final String Z = "z";
	public static final String Q = "q";
	public static final String S = "s";
	public static final String D = "d";
	public static final String A = "a";
	public static final String E = "e";
	public static final String U_ACCENT = "UA";
	
	/*
	 * a completer avec les touches utilisables en jeu
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
	
	public Key (String t) {
		this.touche = t; 

	}
	
	public boolean eval (Entity e) { 
		return e.eval_key(touche);
	}

}
