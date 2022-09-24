package gameobjects;

import libraries.StdDraw;	

public class DoorUp extends Door {

	public DoorUp(boolean isOpened) {
		super(isOpened,positionFromTileIndex(4,8));
		// TODO Auto-generated constructor stub
	}

	public void drawGameObject(){
		StdDraw.picture(getPosition().getX(),getPosition().getY(),this.getImagePath(),this.getSize().getX(),this.getSize().getY(),0);
	}
}
