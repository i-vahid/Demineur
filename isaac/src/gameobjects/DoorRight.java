package gameobjects;

import libraries.StdDraw;

public class DoorRight extends Door{
	public DoorRight(boolean isOpened) {
		super(isOpened,positionFromTileIndex(8,4));
		// TODO Auto-generated constructor stub
	}

	public void drawGameObject(){
		StdDraw.picture(getPosition().getX(),getPosition().getY(),this.getImagePath(),this.getSize().getX(),this.getSize().getY(),270);
	}
}
