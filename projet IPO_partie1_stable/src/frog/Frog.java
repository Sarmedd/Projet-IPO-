package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import graphicalElements.Element;
import util.Direction;
import util.Case;

import java.awt.*;

public class Frog implements IFrog {
	
	private Game game;
	private Element element;
	private Direction direction;

	public Frog(Game g, int pos_x) {
		this.game = g;
		this.element = new Element(pos_x,0, Color.green);
		this.direction = Direction.up;
	}

	@Override
	public Case getPosition() {
		return new Case(this.element.absc, this.element.ord);
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public void move(Direction key) {
		this.direction = key;
		int new_abs = 0;
		int new_ord = 0;

		if (key == Direction.up) {
			new_abs = this.element.absc;
			new_ord = this.element.ord + 1;
		}
		else if (key == Direction.down && this.element.ord > 0) {
			new_abs = this.element.absc;
			new_ord = this.element.ord - 1;
		}
		else if (key == Direction.left && this.element.absc > 0) {
			new_abs = this.element.absc - 1;
			new_ord = this.element.ord;
		}
		else if (key == Direction.right && this.element.absc < game.width - 1){
			new_abs = this.element.absc + 1;
			new_ord = this.element.ord;
		}
		else {
			new_abs = this.element.absc;
			new_ord = this.element.ord;
		}

		Element res = new Element(new_abs, new_ord, Color.green);
		this.element = res;
	}

}
