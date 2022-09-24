/**
 * Représente un soleil <br>
 * Les {@link Tournesol} produisent des <b>Soleil</b> à intervalle régulier
 */
public class Soleil extends Entite {

	public Soleil(double x, double y) {
		super(x, y);
		this.setImg("images/ajoutees/sun.png");
		this.setTailleX(85);
		this.setTailleY(85);
	}

	@Override
	public void step() {
		this.position.setX(this.position.getX());
	}

	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY(), this.getImg(), this.getTailleX(), this.getTailleY());
	}

}
