/**
 * Modèle pour chaque élément du canvas disposant d'une image et de coordonnées
 */
public abstract class Entite {
	
	/** La position de l'<b>Entite</b> */
	protected Position position;
	/** Toutes les <b>Entites</b> ont des points de vie */
	private int pointsDeVie;
	/** Toutes les <b>Entites</b> sont représentées par une image (chemin du fichier) */
	private String img;
	/** Toutes les <b>Entites</b> ont une taille X et Y */
	private int tailleX;
	private int tailleY;
	
	public Entite(double x, double y) {
		position = new Position(x, y);
	}
	

	public double getX() {
		return this.position.getX();
	}
	
	public double getY() {
		return this.position.getY();
	}
	
	
	public void setPosition(Position p){
		this.position = p;
	}
	
	/** Met a jour l'<b>Entite</b> : déplacement, effectuer une action */
	public abstract void step();
	
	/** Dessine l'<b>Entite</b> aux bonnes coordonnees */
	public abstract void dessine();


	public int getPointsDeVie() {
		return pointsDeVie;
	}


	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public int getTailleX() {
		return tailleX;
	}


	public void setTailleX(int tailleX) {
		this.tailleX = tailleX;
	}


	public int getTailleY() {
		return tailleY;
	}


	public void setTailleY(int tailleY) {
		this.tailleY = tailleY;
	}
	

}
