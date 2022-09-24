package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Spikes extends Obstacles{
	public Spikes(Vector2 position,Hero h) {
		super(new Vector2(0.05,0.05),h);
		// TODO Auto-generated constructor stub
		this.imagePath=ImagePaths.SPIKES;
		this.position=position;
		this.dmg=1;
	}
	@Override
	public void drawGameObject() {
		// TODO Auto-generated method stub
		StdDraw.picture(getPosition().getX(),getPosition().getY(),this.getImagePath(),this.getSize().getX(),this.getSize().getY());
	}

	public Hero getH() {
		return h;
	}
	
	
}
