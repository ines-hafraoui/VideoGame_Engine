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
}
