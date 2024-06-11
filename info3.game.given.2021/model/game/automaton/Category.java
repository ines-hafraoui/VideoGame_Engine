package game.automaton;

public class Category {
	
	public char category;
	public static final char EMPTY = 'E';
	public static final char APPLE = 'A';
	public static final char SNAKE = 'S';
	
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
