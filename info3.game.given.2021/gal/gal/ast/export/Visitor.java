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
			return new Egg(Integer.parseInt(parameters.get(0).toString()));
		case "Move":
			return new Move();
		case "Turn":
			Absolute_Orientation a = new Absolute_Orientation("N");
			switch (parameters.get(0).toString()) {

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
			if (Absolute_Orientation.is_absolute_orientation(parameters.get(0))) {

				Absolute_Orientation ao = new Absolute_Orientation(parameters.get(0).toString());
				String t = parameters.get(1).toString();
				int range = Integer.parseInt(parameters.get(2).toString());
				return new Hit(ao, t, range);
			}
			Relative_Orientation ro = new Relative_Orientation(parameters.get(0).toString());
			String t = parameters.get(1).toString();
			int range =Integer.parseInt(parameters.get(2).toString());
			return new Hit(ro, t, range);

		case "Wait":
			return new Wait(Integer.valueOf(parameters.get(0).toString()),
					Integer.valueOf(parameters.get(1).toString()));

		case "Pick":
			return new Pick(Integer.parseInt(parameters.get(0).toString()));
		case "Throw":
			return new Throw();
		case "Explode":
			return new Explode();
		case "Rest":
			return new Rest(Integer.parseInt(parameters.get(0).toString()));
		case "Jump":
			return new Jump();
			
		case "Wizz":
			return new Wizz(Integer.parseInt(parameters.get(0).toString()));
		case "Get":
			return new Get();
		case "Cell":
			if (Relative_Orientation.is_relative_orientation(parameters.get(0))) {
				Relative_Orientation or = new Relative_Orientation(parameters.get(0).toString());
				game.automaton.Category cat = new game.automaton.Category(parameters.get(1).toString());
				return new Cell(or, cat, Integer.valueOf(parameters.get(2).toString()));
			}
			Absolute_Orientation o = new Absolute_Orientation(parameters.get(0).toString());
			game.automaton.Category c = new game.automaton.Category(parameters.get(1).toString());
			return new Cell(o, c, Integer.valueOf(parameters.get(2).toString()));

		case "True":
			return new TrueFalse(true);
			
		case "Got":
			return new Got();

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
		if (left instanceof game.automaton.Condition) {
			if (right instanceof game.automaton.Condition) {
				return new game.automaton.Conjonction((game.automaton.Condition) left,
						(game.automaton.Condition) right);
			}
			return new game.automaton.Conjonction((game.automaton.Condition) left, new TrueFalse((Boolean)right));
		}
		else {
			if (right instanceof game.automaton.Condition) {
				return new game.automaton.Conjonction(new TrueFalse((Boolean) left), (game.automaton.Condition) right);
			}
		}
		return new game.automaton.Conjonction(new TrueFalse((Boolean)left), new TrueFalse((Boolean)right));

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
		// Expression e = (Expression) expression;
		if (expression instanceof game.automaton.Condition) {
			return (game.automaton.Condition) expression;
		}
		Boolean e = (Boolean) expression;

		// return new UnaryOp(unop.operator, e);
		return new TrueFalse(e);
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
		for (game.automaton.Transition t : (List<game.automaton.Transition>) behaviour) {
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
				// game.automaton.Category c = new game.automaton.Category(
				// (String) e.parameters.get(0).toString());
				return new Got(); // got only used to check HP

			case "Cell":
				Absolute_Orientation ad = new Absolute_Orientation(e.parameters.get(0).toString());
				game.automaton.Category cat = new game.automaton.Category(e.parameters.get(1).toString());
				int portee = Integer.parseInt(e.parameters.get(2).toString());
				return new Cell(ad, cat, portee);

			
			}
		} else {
			return expression;
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

		List<Action> al = new ArrayList<Action>();
		LinkedList<FunCall> a = (LinkedList<FunCall>) action;
		for (int i = 0; i < a.size(); i++) {
			al.add((Action) a.get(i));
		}
		String s = target_state.toString();
		if (condition instanceof game.automaton.Condition) {
			return new game.automaton.Transition(s, (game.automaton.Condition) condition, al);// State cible, Condition cond; List< action> actions
		}
		else if (condition instanceof Boolean){
			return new game.automaton.Transition(s, new TrueFalse((Boolean)condition), al);
		}
		return condition;

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
		return automata.get(0); // parse only the first automaton of gal file
	}

}
