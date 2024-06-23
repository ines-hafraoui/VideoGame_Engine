package info3.game;

import java.io.IOException;

import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.entity.Base;
import game.entity.Bot;
import game.entity.Entity;
import game.entity.EntityType;
import game.entity.HitBox;
import game.entity.Item;
import game.entity.Player;
import game.entity.Position;
import game.entity.Projectile;
import game.model.Model;
import game.model.Parser;
import info3.game.avatar.ArrowAvatar;
import info3.game.avatar.Avatar;
import info3.game.avatar.BotAvatar;
import info3.game.avatar.FireBallAvatar;
import info3.game.avatar.ItemAvatar;
import info3.game.avatar.PlayerAvatar;
import info3.game.avatar.TestAvatar;
import info3.game.view.View;

public class Game1Factory implements IFactory {

	@Override
	public Entity newEntity(Automate a, Model m, Position p, Absolute_Orientation o, String type, int team,
			String name) {
		switch (type) {
		case EntityType.BASE:
			return new Base(a, m, p, o, team, 0, name);
		case EntityType.ITEM:
			return new Item(a, m, p, o, team, 0, name);
		case EntityType.TEAMMATE:
			return new Bot(a, m, p, o, team, 0, name);
		case EntityType.PLAYER:
			return new Player(a, m, p, o, team, Model.nb_bot_init, name);
		case EntityType.FIREBALL:
		case EntityType.ARROW:
			return new Projectile(a, m, p, o, type, team, 0, name);
		default:
			return null;
		}
	}

	@Override
	public Entity newEntity(Model m, Position p, Absolute_Orientation o, String type, int team, int nb_bot,
			Boolean pickable, HitBox hb, String name) {
		switch (type) {
		case EntityType.BASE:
			return new Base(m, p, o, team, nb_bot, pickable, hb, name);
		case EntityType.ITEM:
			return new Item(m, p, o, team, nb_bot, pickable, hb,name);
		case EntityType.TEAMMATE:
			return new Bot(m, p, o, team, nb_bot, pickable, hb,name);
		case EntityType.PLAYER:
			return new Player(m, p, o, team, nb_bot, pickable, hb,name);
		case EntityType.FIREBALL:
		case EntityType.ARROW:
			return new Projectile(m, p, o, type, team, nb_bot, pickable, hb,name);
		default:
			return null;
		}
	}

	@Override
	public Avatar newAvatar(Entity e, View v) throws IOException {
		return new TestAvatar(e, v, Game.configParse.getSprite(e.name), e instanceof Player);
	}

}
