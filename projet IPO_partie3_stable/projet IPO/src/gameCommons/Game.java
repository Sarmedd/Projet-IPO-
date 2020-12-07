package gameCommons;

import java.awt.Color;
import java.util.Random;

import environment.EnvInf;
import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

import javax.swing.text.Position;

import util.Case;
import util.Direction;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		// TODO
		Case c = frog.getPosition();

		return !this.environment.isSafe(c);
	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {
		// TODO
		Case c = frog.getPosition();
		if (c.ord == this.height-1)
			return true;
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		/*
		testLose();
		testWin();
		 */


		if (testLose()) {
			graphic.clear();
			if (frog.getDist(1) == -1)
				graphic.endGameScreen("GAME OVER");
			else
				graphic.endGameScreen("GAME OVER", frog.getDist(2));
			return;
		}


		if (testWin()) {
			graphic.clear();
			if (frog.getDist(1) == -1)
				graphic.endGameScreen("YOU WIN !");
			else
				graphic.endGameScreen("YOU WIN", frog.getDist(2));
			return;
		}
	}

	public Direction getFrogDirection() {
		return frog.getDirection();
	}

	public void resetFrogDir() {
		this.frog.resetDir();
	}

	public Case getFrogPos() {
		return frog.getPosition();
	}

	public int getFrogDist() {
		return frog.getDist(1);
	}
}
