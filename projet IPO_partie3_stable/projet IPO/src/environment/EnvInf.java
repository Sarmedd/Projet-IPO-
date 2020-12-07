package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Direction;

public class EnvInf implements IEnvironment {

    private ArrayList<Lane> roadLane;
    private Game game;

    public EnvInf(Game game) {
        this.game = game;
        this.roadLane = new ArrayList<Lane>();
        this.roadLane.add(new Lane(game, 0, 0.0));
        for (int i = 1; i < game.height - 1 ; i++)
            this.roadLane.add(new Lane(game, i, 0.1));

        this.roadLane.add(new Lane(game, game.height-1, 0.0));
    }

    public boolean isWinningPosition(Case c) {
        return false;
    }

    // TODO
    public boolean isSafe(Case c) {
        Lane l = this.roadLane.get(c.ord);
        //return l.isSafe(c);
        if (l.isSafe(c)) {
            return true;
        } else {
            return false;
        }


    }

    @Override
    public void update() {
        for (Lane l : this.roadLane)
            l.update();

        if (game.getFrogDirection() == Direction.up) {
            // On déplace la première ligne en dernière position
            // On décale toutes les autre d'un cran vers le bas

            ArrayList<Lane> tmp = new ArrayList<Lane>();
            for (int i = 1; i < roadLane.size(); i++) {
                Lane l = roadLane.get(i);
                l.setOrd(l.getOrd() - 1);
                tmp.add(l);
            }
            Lane l = roadLane.get(0);
            l.setOrd(game.height - 1);
            tmp.add(l);
            roadLane = tmp;
        }
        else if (game.getFrogDirection() == Direction.down) {
            if (game.getFrogDist() > 0) {
                // On déplace la dernière ligne en première position
                // On décale toutes les autres d'un cran vers le haut

                ArrayList<Lane> tmp = new ArrayList<Lane>();
                Lane first = roadLane.get(roadLane.size()-1);
                first.setOrd(0);
                tmp.add(first);
                for (int i = 0; i < roadLane.size() - 1; i++) {
                    Lane l = roadLane.get(i);
                    l.setOrd(l.getOrd() + 1);
                    tmp.add(l);
                }
                roadLane = tmp;
            }
        }

        game.resetFrogDir();
    }
}
