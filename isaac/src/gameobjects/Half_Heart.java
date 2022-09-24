package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Half_Heart extends Consommables {

	public Half_Heart(Vector2 position, Vector2 size, Hero h) {
		super(position, size, h);
		this.imagePath = ImagePaths.HALF_HEART_PICKABLE;
		// TODO Auto-generated constructor stub
		this.prix = 5;
	}

	@Override
	public void drawGameObject() {
		// TODO Auto-generated method stub
		StdDraw.picture(getPosition().getX(), getPosition().getY(), this.getImagePath(), this.getSize().getX(),
				this.getSize().getY());
	}

	@Override
	public void effet() {
		// TODO Auto-generated method stub
		if (!isPicked) {
			if (h.getHealth() < 6) {
				h.addHealth(1);
				isPicked = true;
			}
		}
	}

	public int getPrix() {
		return prix;
	}

	@Override
	public void drawPrix() {
		// TODO Auto-generated method stub
		StdDraw.text(getPosition().getX(),getPosition().getY()+0.1,""+prix);
	}

}
