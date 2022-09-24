package gameobjects;

import libraries.Physics;
import libraries.StdDraw;	
import libraries.Vector2;
import resources.ImagePaths;

public class Rock extends Obstacles{

	public Rock(Vector2 position,Hero h) {
		super(new Vector2(0.1,0.1),h);
		this.imagePath=ImagePaths.ROCK;
		this.position=position;
		this.dmg=0;
		// TODO Auto-generated constructor stub
	}
	
	public boolean collision() {
		return Physics.rectangleCollision(h.getPosition(), h.getSize(),getPosition(),getSize());
		
	}
	public void drawGameObject(){
		StdDraw.picture(this.position.getX(),this.position.getY(),this.getImagePath(),this.getSize().getX(),this.getSize().getY());
	}
	
}
