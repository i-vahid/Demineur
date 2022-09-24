package gameobjects;

import java.util.Random;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Penny extends Consommables {
	public Penny(Vector2 position, Vector2 size, Hero h) {
		super(position, size, h);
		// TODO Auto-generated constructor stub
		this.imagePath = ImagePaths.COIN;
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
			Random r = new Random();
			int x = r.nextInt(3);
			if (x == 1) {
				h.addCoins(10);
			} else if (x == 2) {
				h.addCoins(15);
			} else {
				h.addCoins(5);
			}
			System.out.println("Isaac coins: " + h.getCoins());
			isPicked=true;
		}
	}

	@Override
	public void drawPrix() {
		// TODO Auto-generated method stub
	}

}
