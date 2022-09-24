/**
 * Le {@link Zombie} de base dans le jeu
 */
public class ZombieDeBase extends Zombie {

	public ZombieDeBase(double x, double y) {
		super(x, y);
		this.setPointsDeVie(200);
		this.setImg("images/ajoutees/zombie_de_base.png");
		this.setTailleX(98);
		this.setTailleY(110);
	}

	@Override
	public void step() {
		if (Zombie.delaiDebutPartie.hasFinished() && this.getPeutAvancer()) {
			this.position.setX(this.position.getX() - this.getVitesseDeplacementX());
		}
		this.collision();
	}

	@Override
	public void dessine() {
		if (this.getPointsDeVie() > 0) {
			StdDraw.picture(this.getX(), this.getY(), this.getImg(), this.getTailleX(), this.getTailleY());
		} else {
			GameWorld.entitesToRemove.add(this);
		}
	}

}
