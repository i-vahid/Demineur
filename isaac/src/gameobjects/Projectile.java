package gameobjects;

import libraries.Vector2;

public abstract class Projectile {
	public enum projectileType{
		BOMB, TEAR, MONSTER 	
		}
	protected double speed;
	protected String imagePath;
	protected Vector2 position;
	protected Vector2 size;
	protected Vector2 direction;
	protected boolean isVisible;
	protected double reloadTime;
	protected double shooting_range;
	protected double distance_target;
	protected double damage;
	
	public Projectile(Vector2 position) {
		this.position=position;
		this.direction=new Vector2();
		this.speed=0;
		this.damage=0;
		this.reloadTime=0;
		this.shooting_range=0;
		this.distance_target=0;
	}

	public double getSpeed() {
		return speed;
	}

	public String getImagePath() {
		return imagePath;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getSize() {
		return size;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public double getReloadTime() {
		return reloadTime;
	}

	public double getShooting_range() {
		return shooting_range;
	}

	public double getDistance_target() {
		return distance_target;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setReloadTime(double reloadTime) {
		this.reloadTime = reloadTime;
	}

	public void setShooting_range(double shooting_range) {
		this.shooting_range = shooting_range;
	}

	public void setDistance_target(double distance_target) {
		this.distance_target = distance_target;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
	public abstract void draw();
}
