package game.automaton;

public class Category {
	
	public char category;
	public static final char EMPTY = 'E';
	public static final char BOULE_FEU = 'B';
	public static final char FLECHE = 'F';
	public static final char ITEM = 'I';
	
	public Category(char cat) {
		category = cat; 
	}
	
	public char getCategory() {
		switch (category) {
		case 'E' : 
			return EMPTY;
		case 'B' :
			return BOULE_FEU;
		case 'F' :
			return FLECHE;
		case 'I' : 
			return ITEM;
		default :
			return 'X';
		}
	}
	
	public void setCategory(char cat) {
		this.category = cat; 
	}	

}
