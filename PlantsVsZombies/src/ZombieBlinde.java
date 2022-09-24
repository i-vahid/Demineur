/**
 * Un type de {@link Zombie}. Possède plus de points de vie que le {@link ZombieDeBase}
 */
public class ZombieBlinde extends Zombie {

	public ZombieBlinde(double x, double y) {
		super(x, y);
		this.setPointsDeVie(560);
	}

	@Override
	public void step() {
		if (Zombie.delaiDebutPartie.hasFinished()) {
			this.position.setX(this.position.getX() - this.getVitesseDeplacementX());
		}
	}

	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY(), "images/zombie2.png", 90, 90);
	}

}
