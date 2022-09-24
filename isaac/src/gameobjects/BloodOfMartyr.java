package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class BloodOfMartyr extends Passive{

	public BloodOfMartyr(Vector2 position, Vector2 size,Hero h) {
		super(position, size);
		// TODO Auto-generated constructor stub
		this.imagePath=ImagePaths.BLOOD_OF_THE_MARTYR;
		this.h=h;
		prix=15;
	}

	@Override
	public void effet() {
		// TODO Auto-generated method stub
		if(!isPicked) {
		h.AddDmg();
		isPicked=true;
		}
	}

	@Override
	public void drawGameObject() {
		// TODO Auto-generated method stub
		StdDraw.picture(getPosition().getX(),getPosition().getY(),this.getImagePath(),this.getSize().getX(),this.getSize().getY());
		
	}
	@Override
	public void drawPrix() {
		// TODO Auto-generated method stub
		StdDraw.text(getPosition().getX(),getPosition().getY()+0.1,""+prix);
	}

}
