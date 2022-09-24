import java.awt.Font;

/**
 * Est utile pour compléter un {@link Afficheur} <br>
 * Un <b>CompteurSoleils</b> est un champ texte qui prend la valeur du nombre de soleils disponibles à l'instant T
 */
public class CompteurSoleils extends Entite {
	/** La valeur du compteur */
	private int valeur;

	public CompteurSoleils(double x, double y) {
		super(x, y);
		this.valeur = 50; // 50 est la valeur au début de la partie
	}

	@Override
	public void step() {
		this.position.setX(this.position.getX());
	}

	@Override
	public void dessine() {
		Font font = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(font);
		StdDraw.text(40, 522, this.valeur + "");
	}
	
	public int getValeur() {
		return this.valeur;
	}
	
	public void setValeur(int v) {
		this.valeur = v;
	}

}
