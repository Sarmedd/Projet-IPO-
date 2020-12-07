package gameCommons;

import util.Case;
import util.Direction;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est à dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection();
	
	/**
	 * Déplace la grenouille dans la direction donnée et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key);

	/**
	 * @param param : 1 pour avoir old_dist, 2 pour avoir distance_parcourue.
	 * @return le nombre de lignes parcourues par la grenouille si le mode de jeu
	 * 			est infini, -1 sinon.
	 */
	public int getDist(int param);

	/**
	 * Permet de reinitialiser la direction de la grenouille.
	 */
	public void resetDir();
}
