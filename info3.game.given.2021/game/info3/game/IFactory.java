package info3.game;

import game.automaton.Automate;
import game.entity.Entity;
import game.entity.Position;
import game.model.Model;
import info3.game.view.Avatar;
import info3.game.view.View;

public interface IFactory {

	public Entity newEntity(Automate a, Model m, Position p, Absolute_Orientation o);

	public Entity newEntity(Model m, Position p, Absolute_Orientation o);

	public Avatar newAvatar(Entity e, View v);
}
