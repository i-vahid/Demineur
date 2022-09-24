/**
 * Représente les pois tirés par les {@link TirePois}
 */
public class Pois extends Entite {

	/** la vitesse du pois tiré en cases parcourues par seconde * 2 */
	private double vitesseTirPois;
	/** les degats infliges par un tir */
	private double pointsDeDegats;

	public Pois(double x, double y) {
		super(x, y);
		this.setVitesseTirPois(2);
		this.setImg("images/ajoutees/pois.png");
		this.setTailleX(20);
		this.setTailleY(20);
		this.pointsDeDegats = 20; //**
	}

	@Override
	public void step() {
		this.position.setX(this.position.getX() + this.getVitesseTirPois());
	}

	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY(), this.getImg(), this.getTailleX(), this.getTailleY());
		this.collision();
	}

	/**
	 * En cas de collision avec un {@link Zombie}, le <b>Zombie</b> prend pour dégats les dégats infligés par un {@link TirePois}
	 */
	private void collision() {
		for (Zombie z : GameWorld.listeDeZombies) {
			if (this.position.getX() >= (z.position.getX() - (z.getTailleX() / 3))
					&& this.position.getY() >= (z.position.getY() - (z.getTailleY() / 2))
					&& this.position.getY() <= (z.position.getY() + (z.getTailleY() / 2))) {
				z.setPointsDeVie((int) (z.getPointsDeVie() - this.pointsDeDegats));
				GameWorld.entitesToRemove.add(this);
				return;
			}
		}
	}

	public double getVitesseTirPois() {
		return vitesseTirPois;
	}

	public void setVitesseTirPois(double vitesseTirPois) {
		this.vitesseTirPois = vitesseTirPois;
	}

}
