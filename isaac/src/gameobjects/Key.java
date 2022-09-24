package gameobjects;

import gameWorld.Room;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Key extends Consommables {
	Room r;

	public Key(Vector2 position, Vector2 size, Hero h,Room r) {
		super(position, size, h);
		this.imagePath = ImagePaths.KEY;
		this.prix = 0;
		this.r=r;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effet() {
		// TODO Auto-generated method stub
		if (!isPicked()) {
			for (Door d : r.getDoors()) {
				if (!d.isOpened()) {
					d.setOpened(true);
				}
			}
			isPicked=true;
		}
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

	}

	public Room getR() {
		return r;
	}

	public void setR(Room r) {
		this.r = r;
	}

}
