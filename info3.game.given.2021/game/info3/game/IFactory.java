package info3.game;

import java.io.IOException;

import game.automaton.Automate;
import game.automaton.Category;
import game.entity.Absolute_Orientation;
import game.entity.Entity;
import game.entity.Position;
import game.model.Model;
import info3.game.avatar.Avatar;
import info3.game.view.View;

public interface IFactory {
	public Entity newEntity(Automate a, Model m, Position p, Absolute_Orientation o, String type);

	public Entity newEntity(Model m, Position p, Absolute_Orientation o, String type);

	public Avatar newAvatar(Entity e, View v) throws IOException;

}
