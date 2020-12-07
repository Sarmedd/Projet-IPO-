package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;

	// TODO : Constructeur(s)
	// Constructeur par défaut
	public Lane(Game game, int ord, double density) {
		this.game = game;
		this.ord = ord;
		this.density = density;
		this.speed = (int) ((java.lang.Math.random() * game.minSpeedInTimerLoops) + 1);
		this.leftToRight = ((this.speed % 2) == 0) ? true : false;
	}

	// Deuxieme constructeur qui laisse le jeu décider de la densité
	public Lane(Game game, int ord) {
		this(game, ord, game.defaultDensity);
	}

	public void update() {

		// TODO

		// Toutes les voitures se déplacent d'une case au bout d'un nombre "tic
		// d'horloge" égal à leur vitesse
		// Notez que cette méthode est appelée à chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut être ajoutée

		this.move();
		this.mayAddCar();
		this.mayRemoveCar();

	}

	// TODO : ajout de methodes

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private void mayRemoveCar() {
		for (int i = 0; i < this.cars.size(); i++) {
			Case c = this.cars.get(i).getPosition();
			if (c.ord >= game.width) {
				this.cars.remove(i);
			}
		}
	}

	private void move() {
		for (Car c : this.cars) {
			c.move();
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

	public boolean isSafe (Case c) {
		for (int i = 0; i < this.cars.size(); i++) {
			if (this.cars.get(i).isInCase(c)) {
				return false;
			}
		}

		return true;
	}

}
