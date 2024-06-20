package gal.demo.test;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import gal.ast.export.*;
import gal.ast.AST;
import gal.ast.Automaton;
import gal.parser.Parser;
import game.automaton.Automate;

public class TestMain {
	
	 public static Automate loadAutomata(String filename) {
<<<<<<< HEAD
	    Automate fsm_list;
=======
	    Automate fsm;
>>>>>>> Model
	    
	    try {
	      AST ast = (AST) Parser.from_file(filename);
	      //Testvisitor v = new Testvisitor();
	      Visitor v = new Visitor();
<<<<<<< HEAD
	      fsm_list = (Automate) ast.accept(v);
	      return fsm_list;
=======
	      fsm =  (Automate) ast.accept(v);
	      return fsm;
>>>>>>> Model
	    } catch (Exception ex) {
	      return null;
	    }
	  }

	public static void main(String args[]) {
		String path = new File("gal/gal/test.gal").getAbsolutePath();
		
		loadAutomata(path);
		
	}

}
