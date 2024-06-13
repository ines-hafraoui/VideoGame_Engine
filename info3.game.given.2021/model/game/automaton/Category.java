package game.automaton;

public class Category {
	
	public char category;
	public static final char EMPTY = 'E';
	public static final char ITEM = 'I';
	public static final char BOT = 'B';
	public static final char PARASITE = 'P';
	
	public Category(char cat) {
		category = cat; 
	}
	
	public char getCategory() {
		return category;
	}
	
	public void setCategory(char cat) {
		this.category = cat; 
	}	

}
