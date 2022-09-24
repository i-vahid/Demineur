package gameobjects;

import libraries.Vector2;

public abstract class Consommables extends Obj{

	public Consommables(Vector2 position, Vector2 size,Hero h) {
		super(position, size);
		this.h=h;
		// TODO Auto-generated constructor stub
	}

}
