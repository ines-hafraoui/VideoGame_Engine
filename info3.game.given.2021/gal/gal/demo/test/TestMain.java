package gal.demo.test;
import java.util.LinkedList;
import java.util.List;
import gal.ast.export.*;
import gal.ast.AST;
import gal.ast.Automaton;
import gal.parser.Parser;
import game.automaton.Automate;

public class TestMain {
	
	 public static List<Automate> loadAutomata(String filename) {
	    List<Automate> fsm_list;
	    
	    try {
	      AST ast = (AST) Parser.from_file(filename);
	      //Testvisitor v = new Testvisitor();
	      Visitor v = new Visitor();
	      fsm_list =  (List<Automate>) ast.accept(v);
	      return fsm_list;
	    } catch (Exception ex) {
	      return null;
	    }
	  }

	public static void main(String args[]) {
		loadAutomata("/home/jessy/Polytech_Grenoble/INFO3/Projet/g3/info3.game.given.2021/gal/gal/automate.gal");
		
	}

}
