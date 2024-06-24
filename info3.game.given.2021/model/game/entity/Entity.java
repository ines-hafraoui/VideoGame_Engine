package game.entity;

import java.util.Timer;
import java.util.TimerTask;

import game.model.Model;

import java.util.ArrayList;
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
	protected String state_action = ActionType.IDLE;
	protected int HP;
	protected List<Entity> bots;
	protected boolean explode;
	protected int team;
	protected boolean injured;
	protected int current_nbot;
	protected int nb_bot_init;
	protected Category cat;
	protected boolean pickable;
	protected boolean selected;
	protected int view;
	protected HitBox hitBox;
	private List<Entity> list_adversary;
	private Entity Player_mate;

	protected String type;
	protected int index_inventory;
	protected int index_bot;
	protected String sprite;

	protected Position position;
	protected Float base_speed = 1F;
	protected Float acc_speed = 0F; // accumulated speed
	protected Absolute_Orientation speed_vct_abs_or = new Absolute_Orientation(Absolute_Orientation.EAST);
	public String name;

	public final static int FLECHE = 1;
	public final static int BOULE_FEU = 2;
	public final static int BOT = 3;
	public final static int dt = 1;

	public final static int TEAM1 = 1;
	public final static int TEAM2 = 2;
	public final static int NOTEAM = 0;
	private static Timer timer = new Timer();
	private static boolean TimerisRunning = false;

	public Entity(Automate a, Model m, Position p, Absolute_Orientation o, int team, int nb_bot, String name) {
		aut = a;
		model = m;
		position = p;
		abs_or = o;
		state_action = ActionType.IDLE;
		HP = 100;
		explode = false;
		index_inventory = 0;
		index_bot = 0;
		this.team = team;
		injured = false;
		current_nbot = nb_bot;
		nb_bot_init = nb_bot;
		selected = false;
		this.name = name;
	}

	public Entity(Model m, Position p, Absolute_Orientation o, int team, int nb_bot, String name) {
		model = m;
		position = p;
		abs_or = o;
		state_action = ActionType.IDLE;
		HP = 100;
		explode = false;
		index_inventory = 0;
		index_bot = 0;
		this.team = team;
		injured = false;
		current_nbot = nb_bot;
		nb_bot_init = nb_bot;
		selected = false;
		this.name = name;
	}

	public Entity(Model m, Position p, Absolute_Orientation o, int team, int nb_bot, boolean pickable, HitBox hb,
			String name) {
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
		nb_bot_init = nb_bot;
		this.pickable = pickable;
		selected = false;
		hitBox.setEntity(this);
		this.name = name;
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

	public Entity get_player_mate() {
		return this.Player_mate;
	}

	public List<Entity> get_list_adversary() {
		return this.list_adversary;
	}

	private float[] polarToCartesian(float norm, float angle) {
		float x = norm * (float) Math.cos(Math.toRadians(angle));
		float y = norm * (float) Math.sin(Math.toRadians(angle));
		return new float[] { x, y };
	}

	private float[] cartesianToPolar(float x, float y) {
		float norm = (float) Math.sqrt(x * x + y * y);
		float angle = (float) Math.toDegrees(Math.atan2(y, x));
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
		
		
		Position newPosition = null;

		float speed = newSpeed(1);
		int angle = speed_vct_abs_or.get_abs_Angle();
		double angleRad = Math.toRadians(angle);

		float X = (float) (Math.cos(angleRad) * speed);
		float Y = (float) (Math.sin(angleRad) * speed);

		float newX = this.position.getPositionX() + X;
		float newY = this.position.getPositionY() + Y;

		newPosition = new Position(newX, newY);
		if (!model.isValidPosition(newPosition)) {
			while (!Absolute_Orientation.orientations.isEmpty()) {
				speed = newSpeed(1);
				this.abs_or = Absolute_Orientation.randomOrientation();
				angle = speed_vct_abs_or.get_abs_Angle();
				angleRad = Math.toRadians(angle);

				X = (float) (Math.cos(angleRad) * speed);
				Y = (float) (Math.sin(angleRad) * speed);

				newX = this.position.getPositionX() + X;
				newY = this.position.getPositionY() + Y;

				newPosition = new Position(newX, newY);

				if (model.isValidPosition(newPosition)) {
					this.position = newPosition;

					return newPosition;
				}
			}
		}
		Absolute_Orientation.setListOrientation();
		return newPosition;
	}

	public boolean eval_cell_abs(Absolute_Orientation dir, Category cat, int porte) {
		return model.eval_cell(dir, cat, porte, this);
	}

	public boolean eval_cell_rel(Relative_Orientation dir, Category cat, int porte) {
		abs_or.set_abs_Orientation(model.from_rel_to_abs_orientation(abs_or, dir));
		return eval_cell_abs(abs_or, cat, porte);
	}

	public boolean eval_got() {

		return HP > 0;
	}

	public boolean eval_closest(Absolute_Orientation d, Category cat, float portee) {
		if (cat.toString() == "V") {
			return true;
		}
		List<Entity> list_cat = new ArrayList();
		if (cat.toString() != "A" && cat.toString() != "@") {
			list_cat = model.list_cat(cat, team); // retourne tous les elements d'une categorie (meme cette entity si
													// elle est dedans)
			return model.eval_closest(list_cat, d, this, portee);
		} else if (cat.toString() == "@") {
			list_cat.add(Player_mate);
			return model.eval_closest(list_cat, d, this, portee);
		}
		return model.eval_closest(list_adversary, d, this, portee);
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

	public void addHitBox(HitBox h) {
		this.getHitBox().setHbWidth(h.getHbWidth());
		this.getHitBox().setHbHeight(h.getHbHeight());
		this.getHitBox().setPolygone();

	}

	public int getCurrentNbot() {
		return current_nbot;
	}

	public void reduce_HP(int r) {
		HP = HP - r;
	}

	public void get_injured() {
		injured = true;
		int h = HP - 10;
		if (h <= 0) {
			this.do_explode();
		} else {
			HP -= 10;
		}

	}

	public boolean do_move() {
		Position p = newPosition();
		if (p == null)
			return false;
		position = p;
		state_action = ActionType.MOVE;
		return true;
	}

	public void do_egg(int cat) {
		aut.blocked = true;
		state_action = ActionType.EGG;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				aut.blocked = false;
				set_state_action("IDLE");
			}
		}, 3000);
		Entity e;
		Automate a;
		Position eggPos = new Position(position.getPositionX(), position.getPositionY());
		Absolute_Orientation eggOr = new Absolute_Orientation(abs_or.get_abs_Orientation());
		switch (cat) {
		case FLECHE:
			// Temporary just to test
			e = model.newEntity(model, eggPos, eggOr, EntityType.ARROW, team, 0, 0, false, new HitBox(2, 2), "Arrow");
			model.get_entities().add(e);
			a = model.automates.get(EntityType.ARROW);
			e.set_automate(a);
			break;
		case BOULE_FEU:
			// Temporary just to test
			e = model.newEntity(model, eggPos, eggOr, EntityType.FIREBALL, team, 0, 0, false, new HitBox(2, 2),
					"Fireball");
			model.get_entities().add(e);
			a = model.automates.get(EntityType.FIREBALL);
			e.set_automate(a);
			break;
		case BOT:
			// Temporary just to test
			e = model.newEntity(model, eggPos, eggOr, EntityType.TEAMMATE, team, 0, 0, false, new HitBox(2, 2),
					"Bot" + team);
			model.get_entities().add(e);
			a = model.automates.get(EntityType.TEAMMATE);
			e.set_automate(a);
			break;
		default:
			break;
		}
	}

	public void do_got(String s) {
		if (s.equals("Power"))
			this.do_rest(5);
	}

	/*
	 * an entity always
	 */
	public boolean do_hit(Absolute_Orientation o, String type, int porte) {
		state_action = ActionType.HIT;
		return model.do_hit(o,type,porte,this);

	}

	/*
	 * select is a parameter that indicate if it is the first of the second
	 * selection
	 */
	public boolean do_wait(int inc, int select) {
		switch (select) {
		case 1:
			index_bot += inc;
			break;
		case 2:
			index_inventory += inc;
			break;
		default:
			break;
		}
		return true;
	}

	/*
	 * take smth from the floor at a certain distance from itself param : t for the
	 * type of Entity
	 */
	public abstract boolean do_pick(int distance);

	// throw what is in its bag at the index. It will create an entity that will be
	// paint by the view
	public abstract Entity do_throw();

	public void do_explode() {
		explode = true;
		state_action = ActionType.EXPLODE;
	}

	public boolean do_rest(int p) {
		state_action = ActionType.REST;
		int h = HP + p;
		if (HP > 0 && h < 100) {
			HP += p;
			return true;
		}
		return false;
	}

	// method to fly
	public boolean do_jump() {
		return false;
	}

	// method for speed move
	public abstract boolean do_wizz(int factor);

	// take an automate from its bag and link it to the entity (bot)
	public abstract boolean do_get();

	public void do_turn(Absolute_Orientation o) {
		abs_or.set_abs_Orientation(o.get_abs_Orientation());
		;
		state_action = ActionType.TURN;
	}

	/*
	 * set the automate of the entity can use this methode to change the automate
	 */

	public void set_automate(Automate a) {
		aut = a;
		a.set_entity(this);

	}

	public void set_model(Model m) {
		model = m;
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

	public int get_HP() {
		return HP;
	}

	public Item[] get_inventory() {
		return null;
	}

	protected boolean is_pickable() {
		return pickable;
	}

}
