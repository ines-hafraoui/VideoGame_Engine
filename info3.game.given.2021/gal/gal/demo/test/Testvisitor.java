package gal.demo.test;

import java.util.List;

import gal.ast.AST;
import gal.ast.Actions;
import gal.ast.Automaton;
import gal.ast.Behaviour;
import gal.ast.BinaryOp;
import gal.ast.Category;
import gal.ast.Condition;
import gal.ast.Direction;
import gal.ast.FunCall;
import gal.ast.IVisitor;
import gal.ast.Key;
import gal.ast.Mode;
import gal.ast.State;
import gal.ast.Transition;
import gal.ast.UnaryOp;
import gal.ast.Underscore;
import gal.ast.Value;

public class Testvisitor implements IVisitor{
	
	
	@Override
	public Object visit(Category cat) {
		System.out.println("visit category");
		return null;
	}

	@Override
	public Object visit(Direction dir) {
		System.out.println("visit dir " + dir.toString());
		return null;
	}

	@Override
	public Object visit(Key key) {
		System.out.println("visit key");
		return null;
	}

	@Override
	public Object visit(Value v) {
		System.out.println("visit value " +v.value);
		return null;
	}

	@Override
	public Object visit(Underscore u) {
		System.out.println("visit underscore");

		return null;
	}

	@Override
	public void enter(FunCall funcall) {
		System.out.println("enter funcall " +funcall.name);

		
	}

	@Override
	public void visit(FunCall funcall) {
		System.out.println("visit funcall " +funcall.name);

		
	}

	@Override
	public void exit(FunCall funcall) {
		System.out.println("exit funcall " +funcall.name);

		
	}

	@Override
	public Object build(FunCall funcall, List<Object> parameters) {
		System.out.println("build funcall " +funcall.name );

		return null;
	}

	@Override
	public void enter(BinaryOp binop) {
		System.out.println("enter binop " +binop.operator);

		
	}

	@Override
	public void visit(BinaryOp binop) {
		System.out.println("visit binop " +binop.operator);
		
	}

	@Override
	public void exit(BinaryOp binop) {
		System.out.println("exit binop " +binop.operator);
		
	}

	@Override
	public Object build(BinaryOp binop, Object left, Object right) {
		System.out.println("built binop");
		return null;
	}

	@Override
	public void enter(UnaryOp unop) {
		System.out.println("enter UnaryOp " +unop.operator);
		
	}

	@Override
	public void exit(UnaryOp unop) {
		System.out.println("exit UnaryOp " +unop.operator);
		
	}

	@Override
	public Object build(UnaryOp unop, Object expression) {
		System.out.println("built UnaryOp " +unop.operator);
		return null;
	}

	@Override
	public Object visit(State state) {
		System.out.println("visit state "+ state.name );
		return null;
	}

	@Override
	public void enter(Mode mode) {
		
		System.out.println("enter Mode");
		
	}

	@Override
	public void visit(Mode mode) {
		System.out.println("visit Mode ");
		
	}

	@Override
	public void exit(Mode mode) {
		System.out.println("exit Mode");
		
	}

	@Override
	public Object build(Mode mode, Object source_state, Object behaviour) {
		System.out.println("Built mode");
		return null;
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		System.out.println("visit behaviour");
		
		return null;
	}

	@Override
	public void enter(Condition condition) {
		System.out.println("enter condition");
		
	}

	@Override
	public void exit(Condition condition) {
		System.out.println("exit condition");	
		
	}

	@Override
	public Object build(Condition condition, Object expression) {
		System.out.println("built condition");
		return null;
	}

	@Override
	public void enter(Actions action) {
		System.out.println("enter action");	
		
	}

	@Override
	public void visit(Actions action) {
		System.out.println("visit action");			
	}

	@Override
	public void exit(Actions action) {
		System.out.println("exit action");		
		
	}

	@Override
	public Object build(Actions action, String operator, List<Object> funcalls) {
		System.out.println("built action");
		return null;
	}

	@Override
	public void enter(Transition transition) {
		System.out.println("enter transition " + "target: " + transition.target.name);		
		
		
	}

	@Override
	public void exit(Transition transition) {
		System.out.println("exit transition " + "target: " + transition.target.name);		
	}

	@Override
	public Object build(Transition transition, Object condition, Object action, Object target_state) {
		System.out.println("built transition " + "target: " + transition.target.name);		
		return null;
	}

	@Override
	public void enter(Automaton automaton) {
		
		System.out.println("enter Automaton " + automaton.name);
		
	}

	@Override
	public void exit(Automaton automaton) {
		System.out.println("Exit Automaton "+ automaton.name);
		
	}

	@Override
	public Object build(Automaton automaton, Object initial_state, List<Object> modes) {
		System.out.println("Built automaton "+ automaton.name + "\n");
		return null;
	}

	@Override
	public void enter(AST ast) {
		
		System.out.println("enter AST");
		System.out.println("Automatons for this are: ");
		for (Automaton aut : ast.aut_list) {
			System.out.println("	" + aut.name+"\n");
		}
		
	}

	@Override
	public void exit(AST ast) {
		System.out.println("Exit AST");
		
	}

	@Override
	public Object build(AST ast, List<Object> automata) {
		System.out.println("Built AST");
		return null;
	}
	
	

}
