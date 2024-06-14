package gal.ast.export;

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
import gal.ast.Transition;
import gal.ast.UnaryOp;
import gal.ast.Underscore;
import gal.ast.Value;

import game.automaton.*;

public class Visitor implements IVisitor {

	@Override
	public Object visit(Category cat) {
		game.automaton.Category c;
		
		return cat;
	}

	@Override
	public Object visit(Direction dir) {
		game.automaton.Direction d;
		return dir;
	}

	@Override
	public Object visit(Key key) {
	
		return key;
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
		LinkedList<Parameter> listout = new LinkedList<Parameter>();
		for (Object o : parameters) {
			listout.add((Parameter) o);
		}
		
		return new FunCall(funcall.percent, funcall.name, listout);
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
	public Object build(BinaryOp binop, Object left, Object right) {
		Expression l = (Expression) left;
		Expression r = (Expression) right;
		
		return new BinaryOp(binop.operator,l,r);
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
	public Object build(UnaryOp unop, Object expression) {
		Expression e = (Expression) expression;
		
		return new UnaryOp(unop.operator,e);
	}

	@Override
	public Object visit(State state) {
		return state;
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
	public Object build(Mode mode, Object source_state, Object behaviour) {
		State s = (State) source_state;
		Behaviour b = (Behaviour) behaviour;
		return new Mode(s,b);
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		LinkedList<Transition> t = new LinkedList<Transition>();
		for (Object o : transitions) {
			t.add((Transition) o);
		}
		return t;
		
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
	public Object build(Condition condition, Object expression) {
		Expression e = (Expression) expression;
		return new Condition(e);
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
		Actions a = new Actions();
		
		for (Object o : funcalls) {
			a.actions.add((FunCall) o);
		}
		a.operator = operator;
		return a;
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
	public Object build(Transition transition, Object condition, Object action, Object target_state) {
		Condition c = (Condition) condition;
		Actions a = (Actions) action;
		State s = (State) target_state;
		return new Transition(c,a,s);
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
