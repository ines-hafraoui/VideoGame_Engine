package game.entity;


import game.model.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Relative_Orientation;
import game.entity.ActionType;

public abstract class Entity {
	protected Automate aut;
	protected Model model;
	protected Absolute_Orientation abs_or;
	protected String state_action;
	protected int HP;
	protected List<Entity> bots;
	protected boolean explode;
	protected int team;
	protected boolean injured;
	protected int current_nbot;
	protected Category cat;
	protected boolean pickable;
	protected boolean selected;
	protected int view;
	protected HitBox hitBox;

	protected String type;
	protected int index_inventory;
	protected int index_bot;

	protected Position position;
	protected Float base_speed = 1F;
	protected Float acc_speed = 0F; // accumulated speed
	protected Absolute_Orientation speed_vct_abs_or = new Absolute_Orientation(Absolute_Orientation.EAST);

	public final static int FLECHE = 1;
	public final static int BOULE_FEU = 2;
	public final static int BOT = 3;
	public final static int dt = 1;
	
	public final static int TEAM1 = 1;
	public final static int TEAM2 = 2;
	public final static int NOTEAM = 0;
	

	public Entity(Automate a, Model m, Position p, Absolute_Orientation o, int team, int nb_bot) {
		aut = a;
		model = m;
		position = p;
		abs_or = o;
		state_action = ActionType.IDLE;
		HP = 100;
		explode = false;
		index_inventory =0 ;
		index_bot =0;
		this.team = team;
		injured = false;
		current_nbot = nb_bot;
		selected = false;
	}
	
	public Entity(Model m, Position p, Absolute_Orientation o,int team, int nb_bot){
		model = m;
		position = p;
		abs_or = o;
		state_action = ActionType.IDLE;
		HP = 100;
		explode = false;
		index_inventory =0 ;
		index_bot =0;
		this.team = team;
		injured = false;
		current_nbot = nb_bot;
		selected = false;
	}
	
	public Entity(Model m,Position p, Absolute_Orientation o, int team,int nb_bot, int view,boolean pickable,HitBox hb) {
		hitBox = hb;
		model = m;
		position = p;
		abs_or = o;
		HP = 100;
		explode = false;
		index_inventory = 0;
		index_bot = 0;
		this.team = team;
		injured = false;
		current_nbot = nb_bot;
		this.view = view;
		this.pickable = pickable;
		selected = false;
	}
	

	public static boolean haveCommonChar(String str1, String str2) {

		Set<Character> set1 = new HashSet<>();
		for (char c : str1.toCharArray()) {
			set1.add(c);
		}

		Set<Character> set2 = new HashSet<>();
		for (char c : str2.toCharArray()) {
			set2.add(c);
		}

		set1.retainAll(set2);

		return !set1.isEmpty();
	}
	
	public int get_team() {
		return team;
	}

    private float[] polarToCartesian(float norm, float angle) {
        float x = norm * (float)Math.cos(Math.toRadians(angle));
        float y = norm * (float)Math.sin(Math.toRadians(angle));
        return new float[] { x, y };
    }

    private float[] cartesianToPolar(float x, float y) {
        float norm = (float)Math.sqrt(x * x + y * y);
        float angle = (float)Math.toDegrees(Math.atan2(y, x));
        return new float[] { norm, angle };
    }
    protected float newSpeed(int factor) {
        if (acc_speed == 0) {
            acc_speed = base_speed;
            speed_vct_abs_or.set_abs_Orientation(abs_or.get_abs_Orientation());
        }

        float viscosity = model.getMap().getViscosity(position);
        float base_speed_adjusted = Math.max(0, base_speed - viscosity);

        float[] acc_cartesian = polarToCartesian(acc_speed, speed_vct_abs_or.get_abs_Angle());
        float[] base_cartesian = polarToCartesian(base_speed_adjusted, abs_or.get_abs_Angle());

        float x_total = base_cartesian[0];
        float y_total = base_cartesian[1];

        float[] new_speed_polar = cartesianToPolar(x_total, y_total);

        acc_speed = factor * new_speed_polar[0];
        speed_vct_abs_or.set_abs_Angle(new_speed_polar[1]);


        return acc_speed;
    }

	protected Position newPosition() {
		
		float speed = newSpeed(1);
		int angle = speed_vct_abs_or.get_abs_Angle();
	    double angleRad = Math.toRadians(angle); 
		
		
		float X = (float) (Math.cos(angleRad) * speed);
		float Y = (float) (Math.sin(angleRad) * speed);
		
		position.setPositionX(this.position.getPositionX() + X);
		position.setPositionY(this.position.getPositionY() + Y);
		
		
		return position;

	}

	public boolean eval_cell_abs(Absolute_Orientation dir, Category cat, int porte) {
		return model.getMap().eval_abs(dir, position.getPositionX(), position.getPositionY(), porte);
	}
	
	public boolean eval_cell_rel(Relative_Orientation dir, Category cat, int porte) {
		return model.getMap().eval_rel(dir, position.getPositionX(), position.getPositionY(), porte);
	}
	
	public boolean eval_got() {
		return HP>0;
	}
	
	public boolean eval_closest(Absolute_Orientation d, Category cat, float portee) {
		if (cat.toString() == "V") {
			return true;
		}
		List<Entity> list_cat = model.list_cat(cat, team); 		//retourne tous les elements d'une categorie (meme cette entity si elle est dedans)
		return model.eval_closest(list_cat, d, this, portee);
	}
	
	public String get_type() {
		return type;
	}
	
	public Absolute_Orientation get_abs_or() {
		return abs_or;
	}

	public float get_x() {
		return position.getPositionX();
	}

	public float get_y() {
		return position.getPositionY();
	}
	
	public int getView() {
		return view;
	}
	
	public HitBox getHitBox() {
		return hitBox;
	}

	public void reduce_HP(int r) {
		HP = HP - r;
	}
	
	public void get_injured() {
		injured = true;
		HP -= 10;
	}

	public abstract boolean do_move();

	public abstract void do_egg(int cat);
	
	public void do_got(String s) {
		if (s.equals("Power"))
			this.do_power(5);
	}
	
	
	/*
	 * an entity always
	 */
	public abstract boolean do_hit(Absolute_Orientation o, String type, int porte);

	
	/*
	 * select is a  parameter that indicate if it is the first of the second selection 
	 */
	public boolean do_wait(int inc, int select ) {
		switch(select) {
		case 1 :
			index_bot +=inc;
			break;
		case 2 : 
			index_inventory += inc;
			break;
		default : 
			break;
		}
		return true;
	}
		

	/*
	 * take smth from the floor at a certain distance from itself
	 * param : t for the type of Entity
	 */
	public abstract boolean do_pick(int distance);

	// throw what is in its bag at the index. It will create an entity that will be
	// paint by the view
	public abstract Entity do_throw();

	public void do_explode() {
		explode = true;
	}

	public abstract void do_power(int p);

	// method to fly
	public abstract boolean do_jump();

	// method for speed move
	public abstract boolean do_wizz(int factor);

	// take an automate from its bag and link it to the entity (bot)
	public abstract boolean do_get();

	public abstract void do_turn(Absolute_Orientation o);

	/*
	 * set the automate of the entity can use this methode to change the automate
	 */

	public void set_automate(Automate a) {
		aut = a;
		a.set_entity(this);
		
	}
	
	public void set_model(Model m ) {
		model = m ;
	}

	public void set_state_action(String action) {
		state_action = action;
	}
	
	public String get_state_action() {
		return state_action;
	}

	/*
	 * return the automate of the entity
	 */

	public Automate get_automate() {
		return aut;
	}
	
	public void tick(long elpased) {

		if (aut != null) {
			aut.step(this);
		}
	}

	public Category get_category() {
		return cat;
	}

	public boolean eval_key(String touche) {
		return model.get_list_touche().contains(touche);
	}

}
