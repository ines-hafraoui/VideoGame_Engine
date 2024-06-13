package info3.game;

import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.entity.Base;
import game.entity.Bot;
import game.entity.Boule_Feu;
import game.entity.Entity;
import game.entity.Fleche;
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
import info3.game.view.View;

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
			return new Boule_Feu(a, m, p, o, type);
		case ARROW:
			return new Fleche(a, m, p, o, type);
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
		case FIREBALL:
			return new Boule_Feu(m, p, o, type);
		case ARROW:
			return new Fleche(m, p, o, type);
		default:
			return null;
		}
	}

	@Override
	public Avatar newAvatar(Entity e, View v) {
		switch (e.get_type()) {
		case BASE:
			return new BaseAvatar(e, v);
		case ITEM:
			return new ItemAvatar(e, v);
		case BOT:
			return new BotAvatar(e, v);
		case PLAYER:
			return new PlayerAvatar((Player) e, v);
		case FIREBALL:
			return new FireBallAvatar(e, v);
		case ARROW:
			return new ArrowAvatar(e, v);
		default:
			return null;
		}
	}

}
