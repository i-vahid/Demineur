package gameobjects;

import libraries.Vector2;
import resources.RoomInfos;

public abstract class Obj {
	protected String imagePath;
	protected Vector2 position;
	protected Vector2 size;
	protected boolean isPicked;
	protected Hero h;
	protected int prix;

	public Obj(Vector2 position,Vector2 size) {
		this.imagePath=null;
		this.position=position;
		this.size=size;
		this.isPicked=false;
		this.prix=0;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public boolean isPicked() {
		return isPicked;
	}

	public Vector2 getSize() {
		return size;
	}

	public Vector2 getPosition() {
		return position;
	}
	public void setPrix(int prix) {
		this.prix=prix;
	}
	public int getPrix() {
		return prix;
	}

	public abstract void effet();
	public abstract void drawGameObject();
	public abstract void drawPrix();

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
}
