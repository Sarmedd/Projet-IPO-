package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import graphicalElements.Element;
import util.Direction;
import util.Case;

import java.awt.*;

public class FrogInf implements IFrog {

    private Game game;
    private Element element;
    private Direction direction;
    private int distance_parcourue;   // distance parcourue par la grenouille

    public FrogInf(Game g, int pos_x) {
        this.game = g;
        this.element = new Element(pos_x,0, Color.green);
        this.direction = Direction.nulldir;
        this.distance_parcourue = 0;
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
        int new_abs = this.element.absc;

        if (key == Direction.left && this.element.absc > 0) {
            new_abs = this.element.absc - 1;
        }
        else if (key == Direction.right && this.element.absc < game.width - 1) {
            new_abs = this.element.absc + 1;
        }
        else if (key == Direction.up) {
            this.distance_parcourue++;
        }
        else if (key == Direction.down && this.distance_parcourue != 0) {
            this.distance_parcourue--;
        }

        Element res = new Element(new_abs, this.element.ord, Color.green);
        this.element = res;
    }

    @Override
    public int getDist(int param) {
            return this.distance_parcourue;
    }

    @Override
    public void resetDir() {
        this.direction = Direction.nulldir;
    }
}
