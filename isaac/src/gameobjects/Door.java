package gameobjects;

import resources.ImagePaths;
import resources.RoomInfos;
import libraries.*;

public abstract class Door {
private String imagePath;
private boolean isOpened;
private Vector2 position;
private Vector2 size;

public Door(boolean isOpened,Vector2 position) {
	this.isOpened=isOpened;
	if(isOpened) {
		this.imagePath=ImagePaths.OPENED_DOOR;
	}else {
		this.imagePath=ImagePaths.CLOSED_DOOR;
	}
	this.position=position;
	this.size=new Vector2(0.1,0.1);
}

public String getImagePath() {
	return imagePath;
}

public boolean isOpened() {
	return isOpened;
}
public void setOpened(boolean isOpened) {
	this.isOpened = isOpened;
	if(isOpened) {
		this.imagePath=ImagePaths.OPENED_DOOR;
		drawGameObject();
	}else {
		this.imagePath=ImagePaths.CLOSED_DOOR;
		drawGameObject();
	}
}

public abstract void drawGameObject();

/**
 * Convert a tile index to a 0-1 position.
 * 
 * @param indexX
 * @param indexY
 * @return
 */
public static Vector2 positionFromTileIndex(double indexX,double indexY)
{
	return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
			indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
}

public Vector2 getPosition() {
	return position;
}

public Vector2 getSize() {
	return size;
}

}
