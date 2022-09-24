package gameobjects;

import libraries.StdDraw;

public class DoorDown extends Door {

	public DoorDown(boolean isOpened) {
		super(isOpened,positionFromTileIndex(4,0));
		// TODO Auto-generated constructor stub
	}

	public void drawGameObject(){
		StdDraw.picture(getPosition().getX(),getPosition().getY(),this.getImagePath(),this.getSize().getX(),this.getSize().getY(),180);
	}
}
