package gameobjects;

import libraries.Vector2;	
public abstract class Obstacles {

	protected String imagePath;
	protected Vector2 size;
	protected Vector2 position;
	protected int dmg;
	protected Hero h;
	
	
	public Obstacles(Vector2 size,Hero h) {
		this.size=size;
		this.h=h;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public Vector2 getSize() {
		return size;
	}

	public Vector2 getPosition() {
		return position;
	}
	public int getDmg() {
		return dmg;
	}
	public abstract void drawGameObject();

	
}
