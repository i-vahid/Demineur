/**
 * Représente la position d'une {@link Entite} dans le jeu
 */
public class Position {
	private double positionX;
	private double positionY;

	public Position(double x, double y) {
		positionX = x;
		positionY = y;
	}

	public double getX() {
		return positionX;
	}

	public double getY() {
		return positionY;
	}

	public boolean equals(Position p) {
		return (this.positionX == p.positionX && this.positionY == p.positionY);
	}

	public void setY(double y) {
		this.positionY = y;
	}

	public void setX(double x) {
		this.positionX = x;
	}

	public String toString() {
		return null;
	}
}
