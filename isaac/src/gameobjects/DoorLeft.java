package gameobjects;

import libraries.StdDraw;

public class DoorLeft extends Door{

	public DoorLeft(boolean isOpened) {
		super(isOpened,positionFromTileIndex(0,4));
		// TODO Auto-generated constructor stub
	}

	public void drawGameObject(){
		StdDraw.picture(getPosition().getX(),getPosition().getY(),this.getImagePath(),this.getSize().getX(),this.getSize().getY(),90);
	}
	
}
