package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Heart extends Consommables {

	public Heart(Vector2 position, Vector2 size, Hero h) {
		super(position, size, h);
		this.imagePath = ImagePaths.HEART_PICKABLE;
		this.prix = 10;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawGameObject() {
		// TODO Auto-generated method stub
		StdDraw.picture(getPosition().getX(), getPosition().getY(), this.getImagePath(), this.getSize().getX(),
				this.getSize().getY());
	}
	@Override
	public void drawPrix() {
		// TODO Auto-generated method stub
		StdDraw.text(getPosition().getX(),getPosition().getY()+0.1,""+prix);
	}

	@Override
	public void effet() {
		// TODO Auto-generated method stub
		if (!isPicked) {
			if (h.getHealth() < 6) {
				h.addHealth(2);
				isPicked = true;
			}
		}
	}

	public int getPrix() {
		return prix;
	}

}
