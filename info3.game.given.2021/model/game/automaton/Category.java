package game.automaton;

public class Category {
	
	public static final String AUTRE = "A"; // adversaire ou membre de l"autre équipe
	public static final String CLUE = "C"; //indice d"un précédent passage
	public static final String DANGER = "D"; 
	public static final String GATE = "G"; 
	public static final String JUMPABLE = "J";
	public static final String MISSILE = "M";
	public static final String OBSTACLE = "O";
	public static final String PICKABLE = "P";
	public static final String TEAM = "T"; // Une entité de mon equipe mais pas moi
	public static final String VOID = "V";
	public static final String PLAYER_IN_MY_TEAM = "@";
	public static final String PLAYER_IN_ENEMY_TEAM = "#";
	public static final String ANYTHING = "_"; //but void
	
	
	//field
	private String cat;
	public Category(String c) {
		this.cat = c;
	}
	
	public String get_category(){
		return this.cat;
		
	}
	public static boolean is_category(Object o) {
		switch (o.toString()) {
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
