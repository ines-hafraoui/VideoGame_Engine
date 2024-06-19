package gal.demo.test;
import java.util.LinkedList;
import java.util.List;
import gal.ast.export.*;
import gal.ast.AST;
import gal.ast.Automaton;
import gal.parser.Parser;
import game.automaton.Automate;

public class TestMain {
	
	 public static Automate loadAutomata(String filename) {
	    Automate fsm;
	    
	    try {
	      AST ast = (AST) Parser.from_file(filename);
	      //Testvisitor v = new Testvisitor();
	      Visitor v = new Visitor();
	      fsm =  (Automate) ast.accept(v);
	      return fsm;
	    } catch (Exception ex) {
	      return null;
	    }
	  }

	public static void main(String args[]) {
		loadAutomata("/home/nada/Documents/INFO3/S2/Projet_Jeu/g3/info3.game.given.2021/gal/gal/automate.gal");
		
	}

}
