package info3.game;

import java.awt.Container;
import java.io.IOException;


import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.entity.Arrow;
import game.entity.Base;
import game.entity.Bot;
import game.entity.Entity;
import game.entity.Fire_Ball;
import game.entity.Item;
import game.entity.Player;
import game.entity.Position;
import game.model.Model;
import info3.game.avatar.ArrowAvatar;
import info3.game.avatar.Avatar;
import info3.game.avatar.BaseAvatar;
import info3.game.avatar.BotAvatar;
import info3.game.avatar.FireBallAvatar;
import info3.game.avatar.ItemAvatar;
import info3.game.avatar.PlayerAvatar;

public class Game1Factory implements IFactory {

	public static final String BASE = "BA";
	public static final String ITEM = "I";
	public static final String BOT = "BO";
	public static final String PLAYER = "J";
	public static final String FIREBALL = "BF";
	public static final String ARROW = "F";

	@Override
	public Entity newEntity(Automate a, Model m, Position p, Absolute_Orientation o, String type) {
		switch (type) {
		case BASE:
			return new Base(a, m, p, o, type);
		case ITEM:
			return new Item(a, m, p, o, type);
		case BOT:
			return new Bot(a, m, p, o, type);
		case PLAYER:
			return new Player(a, m, p, o, type);
		case FIREBALL:
<<<<<<< HEAD
			return new Fire_Ball(a,m, p, o, type);
		case ARROW:
			return new Arrow(a,m, p, o, type);
=======
			return new Fire_Ball(a, m, p, o, type);
		case ARROW:
			return new Arrow(a, m, p, o, type);
>>>>>>> origin/View
		default:
			return null;
		}
	}

	@Override
	public Entity newEntity(Model m, Position p, Absolute_Orientation o, String type) {
		switch (type) {
		case BASE:
			return new Base(m, p, o, type);
		case ITEM:
			return new Item(m, p, o, type);
		case BOT:
			return new Bot(m, p, o, type);
		case PLAYER:
			return new Player(m, p, o, type);
		default:
			return null;
		}
	}

	@Override
	public Avatar newAvatar(Entity e, Container p) throws IOException {
		switch (e.get_type()) {
		case BASE:
			return new BaseAvatar(e, p);
		case ITEM:
			return new ItemAvatar(e, p);
		case BOT:
			return new BotAvatar(e, p);
		case PLAYER:
			return new PlayerAvatar((Player) e, p);
		case FIREBALL:
			return new FireBallAvatar(e, p);
		case ARROW:
			return new ArrowAvatar(e, p);
		default:
			return null;
		}
	}

}
