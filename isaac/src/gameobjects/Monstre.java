package gameobjects;
import libraries.*;	

public abstract class Monstre {
	protected String imagePath;
	protected Vector2 size;
	protected Vector2 direction;
	protected Vector2 position;
	protected double vitesse;
	protected double health;
	protected double damage;
	
	public Monstre (double vitesse,double health,double damage) {
		this.vitesse = vitesse;
		this.health = health;
		this.damage = damage;
	}
	public abstract void move();
	public abstract void draw();
	public Vector2 getSize() {
		return size;
	}
	public Vector2 getPosition() {
		return position;
	}
	public double getVitesse() {
		return vitesse;
	}
	public double getHealth() {
		return health;
	}
	public double getDamage() {
		return damage;
	}
	public void removeHealth(double i) {
		this.health=health-i;
	}
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(vitesse);
		return normalizedVector;
	}
	public void setHealth(double health) {
		this.health = health;
	}
}
