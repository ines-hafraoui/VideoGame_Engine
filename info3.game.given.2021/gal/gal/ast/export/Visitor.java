package gal.ast.export;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import gal.ast.AST;
import gal.ast.Actions;
import gal.ast.Automaton;
import gal.ast.Behaviour;
import gal.ast.BinaryOp;
import gal.ast.Category;
import gal.ast.Condition;
import gal.ast.Direction;
import gal.ast.Expression;
import gal.ast.FunCall;
import gal.ast.IVisitor;
import gal.ast.Key;
import gal.ast.Mode;
import gal.ast.Parameter;
import gal.ast.State;
import gal.ast.Terminal;
import gal.ast.Transition;
import gal.ast.UnaryOp;
import gal.ast.Underscore;
import gal.ast.Value;

import game.automaton.*;
import game.entity.Absolute_Orientation;
import game.entity.Entity;

public class Visitor implements IVisitor {

	@Override
	public Object visit(Category cat) {
		return cat.terminal.content.charAt(0);
	}

	@Override
	public Object visit(Direction dir) {
		return dir.terminal.content.charAt(0);
	}

	@Override
	public Object visit(Key key) {

		return key.terminal.content;
	}

	@Override
	public Object visit(Value v) {
		return v;
	}

	@Override
	public Object visit(Underscore u) {
		return u;
	}

	@Override
	public void enter(FunCall funcall) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunCall funcall) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(FunCall funcall) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(FunCall funcall, List<Object> parameters) {

		switch (funcall.name) { // pas sur que le premier élément de parameters est l'entite
		case "Egg":
			return new Egg();
		case "Move":
			return new Move();
		case "Turn":
			Absolute_Orientation a = new Absolute_Orientation("N");
			switch ((String) parameters.get(0)) {

			case "N":
				return new Turn(a);
			case "S":
				a.abs_or = "S";
				return new Turn(a);
			case "E":
				a.abs_or = "E";
				return new Turn(a);
			case "W":
				a.abs_or = "W";
				return new Turn(a);
			case "NE":
				a.abs_or = "NE";
				return new Turn(a);
			case "NW":
				a.abs_or = "NW";
				return new Turn(a);
			case "SE":
				a.abs_or = "SE";
				return new Turn(a);
			case "SW":
				a.abs_or = "SW";
				return new Turn(a);
			}

		case "Die":
			return new Die();
		case "Hit":
			Absolute_Orientation ao = new Absolute_Orientation((String) parameters.get(0));
			game.automaton.Category c = new game.automaton.Category((char) parameters.get(1));
			int range = (int) parameters.get(2);
			//return new Hit(ao, c, range); // Nada must change his Hit constructor
			break; // wait to see what arguments we use with hit
		case "Wait":
			//return new Wait();
			break;
		case "Pick":
			break; //second arg of pick must be a Category
		case "Throw":
			//return new Throw(); // remove the entity or create another constructor
			break;
		case "Explode":
			return new Explode();
		case "Power":
			break;
		case "Jump":
			break;
		case "Wizz":
			// return new Wizz();
			break;
		case "Get":
			break;
		case "Cell":
			if (Relative_Orientation.is_relative_orientation(parameters.get(0))) {

				return new Cell(((Relative_Orientation) parameters.get(0)), (game.automaton.Category) parameters.get(1),
						(int) parameters.get(2));
			}
			return new Cell(((Absolute_Orientation) parameters.get(0)), (game.automaton.Category) parameters.get(1),
					(int) parameters.get(2));

		case "True":
			return true;

		}
		return true;
	}

	@Override
	public void enter(BinaryOp binop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BinaryOp binop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(BinaryOp binop) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(BinaryOp binop, Object left, Object right) { // maybe change it
/*Expression l = (Expression) left;
		Expression r = (Expression) right;

		return new BinaryOp(binop.operator, l, r);*/
		return null;
	}

	@Override
	public void enter(UnaryOp unop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(UnaryOp unop) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(UnaryOp unop, Object expression) { // mouais a modifier
		Expression e = (Expression) expression;

		return new UnaryOp(unop.operator, e);
	}

	@Override
	public Object visit(State state) { // in our implementation : Mode = State so the professor's "State" are useless
		
		
		return state.name;
	}

	@Override
	public void enter(Mode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Mode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Mode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Mode mode, Object source_state, Object behaviour) { // to change

		game.automaton.State s = new game.automaton.State((String) source_state);
		for (game.automaton.Transition t :(List<game.automaton.Transition>) behaviour) {
			s.add_transition(t);
		}
		return s;
			
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		return transitions; // not sure

	}

	@Override
	public void enter(Condition condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Condition condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Condition condition, Object expression) { // must create implementable class Condition and
																	// expression

		if (expression instanceof FunCall) {
			FunCall e = (FunCall) expression;
			switch (e.name) {
			case "Key":
				return new game.automaton.Key(e.parameters.get(0).toString());
			case "Got":
				game.automaton.Category c = new game.automaton.Category(
						(char) e.parameters.get(0).toString().charAt(0));
				return new Got(c);

			case "Cell":
				Absolute_Orientation ad = new Absolute_Orientation(e.parameters.get(0).toString());
				game.automaton.Category cat = new game.automaton.Category(e.parameters.get(1).toString().charAt(0));
				int portee = Integer.parseInt(e.parameters.get(2).toString());
				return new Cell(ad, cat, portee);

			case "Conjonction":
				Object o1 = e.parameters.get(0);
				Object o2 = e.parameters.get(1);
				return new Conjonction((game.automaton.Condition) o1, (game.automaton.Condition) o2);
			}
		} else if (expression instanceof Boolean){
			return new game.automaton.TrueFalse((boolean) expression);
		}
		
		return null;
	}

	@Override
	public void enter(Actions action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Actions action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Actions action) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Actions action, String operator, List<Object> funcalls) {

		return funcalls;
	}

	@Override
	public void enter(Transition transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Transition transition) {
		// TODO Auto-generated method stub
		
	

	}

	@Override
	public Object build(Transition transition, Object condition, Object action, Object target_state) { // to change
		game.automaton.Condition c = (game.automaton.Condition) condition;
		
		List<Action> al = new ArrayList<Action>();
		LinkedList<FunCall> a = (LinkedList<FunCall>) action;
		for (int i = 0; i<a.size(); i++) {
			al.add((Action) a.get(i));
		}
		String s = target_state.toString();
		
		return new game.automaton.Transition(s,c,al); // State cible, Condition cond; List< action> actions
		
	}

	@Override	
	public void enter(Automaton automaton) {

	}

	@Override
	public void exit(Automaton automaton) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Automaton automaton, Object initial_state, List<Object> modes) {

		LinkedList<game.automaton.State> l = new LinkedList<game.automaton.State>();
		for (Object o : modes) {
			l.add((game.automaton.State) o);
		}

		return new Automate((String) initial_state, l);
	}

	@Override
	public void enter(AST ast) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(AST ast) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(AST ast, List<Object> automata) {
		System.out.println("succeed");
		return automata;
	}

}
