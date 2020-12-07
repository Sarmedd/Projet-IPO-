package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

    private ArrayList<Lane> roadLane;
    private Game game;

    public Environment(Game game) {
        this.game = game;
        this.roadLane = new ArrayList<Lane>();

        // on ajoute une premiere ligne pour le départ de la grenouille
        // il n'y a pas de voitures donc densité = 0
        this.roadLane.add(new Lane(game, 0, 0.0));

        // On ajoute ensuite autant de lignes que l'écran peut en contenir, en en laissant
        // une derniere vide pour la ligne d'arrivée
        for (int i = 1; i < game.height - 1; i++) {
            this.roadLane.add(new Lane(game, i));    // La densité est définie par game
        }

        // On ajoute enfin la ligne d'arrivée
        this.roadLane.add(new Lane(game, game.height-1, 0.0));
    }

    /**
     * Permet de savoir si la grenouille est sur la ligne d'arrivée
     * @param c la position actuelle de la grenouille
     * @return true si la grenouille se trouve sur la ligne d'arrivée, false sinon.
     */
    public boolean isWinningPosition(Case c) {
        return c.ord == (game.height - 1);
    }

    /**
     * Permet de savoir si la grenouille touche une voiture
     * @param c la position de la grenouille
     * @return false si la grenouille touche une voiture, true sinon.
     */
    public boolean isSafe(Case c) {
        Lane l = this.roadLane.get(c.ord);
        return l.isSafe(c);
    }

    public void update() {
        for (Lane l : this.roadLane) {
            l.update();
        }
    }

}
