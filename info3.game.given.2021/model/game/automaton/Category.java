package game.automaton;

public class Category {
	
	public static final char AUTRE = 'A'; // adversaire ou membre de l'autre équipe
	public static final char CLUE = 'C'; //indice d'un précédent passage
	public static final char DANGER = 'D'; 
	public static final char GATE = 'G'; 
	public static final char JUMPABLE = 'J';
	public static final char MISSILE = 'M';
	public static final char OBSTACLE = 'O';
	public static final char PICKABLE = 'P';
	public static final char TEAM = 'T'; // Une entité de mon equipe mais pas moi
	public static final char VOID = 'V';
	public static final char PLAYER_IN_MY_TEAM = '@';
	public static final char PLAYER_IN_ENEMY_TEAM = '#';
	public static final char ANYTHING = '_'; //but void
	
	
	//field
	private char cat;
	public Category(char c) {
		this.cat = c;
	}
	
	public char get_category(){
		return this.cat;
		
	}
	public static boolean is_category(Object o) {
		switch (o.toString().charAt(0)) {
		case AUTRE:
			return true;
		case CLUE:
			return true;
		case DANGER:
			return true;
		case GATE:
			return true;
		case JUMPABLE:
			return true;
		case MISSILE:
			return true;
		case OBSTACLE:
			return true;
		case PICKABLE:
			return true;
		case TEAM:
			return true;
		case VOID:
			return true;
		case PLAYER_IN_MY_TEAM:
			return true;
		case PLAYER_IN_ENEMY_TEAM:
			return true;
		case ANYTHING:
			return true;
		default:
			return false;
			
		}
	}
}
