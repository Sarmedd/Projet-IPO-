package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	//TODO Constructeur(s)
	public Car(Game game, Case position, boolean leftToRight) {
		this.game = game;
		this.leftPosition = position;
		this.leftToRight = leftToRight;
		this.length = (int) ((java.lang.Math.random() * 3) + 1);
	}
	
	//TODO : ajout de methodes

	void move() {
		int abs = 0;
		if (leftToRight) {
			// sens de defilement : gauche vers droite
			abs = this.leftPosition.absc + 1;
		} else {
			// sens de defilement: droite vers la gauche
			abs = this.leftPosition.absc - 1;
		}

		Case c = new Case(abs, this.leftPosition.ord);
		this.leftPosition = c;
		this.addToGraphics();
	}

	public Case getPosition() {
		return this.leftPosition;
	}

	public void setPosition(Case c) {
		this.leftPosition = c;
	}

	
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}


	/**
	 * Permet de savoir si la grenouille se trouve sur une voiture
	 * @param c la position de la grenouille
	 * @return true si la case correspont à une voiture, false sinon.
	 */
	public boolean isInCase(Case c) {
		//System.out.println(this.leftPosition.absc + " ; " + this.leftPosition.ord + "  /  " + c.absc + " ; " + c.ord);
		return ( this.leftPosition.ord == c.ord)
				&&  (c.absc >= this.leftPosition.absc)  // position de la premiere case de la voiture
				&& (c.absc < this.leftPosition.absc + this.length); // position de la derniere case de la voiture
	}

}
