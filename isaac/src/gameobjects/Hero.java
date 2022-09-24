package gameobjects;
import java.util.*;			
import libraries.StdDraw;	
import libraries.Vector2;
import resources.ImagePaths;

public class Hero
{
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int health;
	private boolean addDmg;
	private int coins;
	private boolean invincible;
	private double tearTime=System.nanoTime() / 1000000000;
	private List<Tear> projectiles;

	public Hero(Vector2 position, Vector2 size, double speed, String imagePath)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.direction = new Vector2();
		this.health=6;
		this.coins=0;
		this.projectiles = new LinkedList<Tear>();
		this.invincible=false;
	}
	
	private void deleteProjectiles() {
		List<Tear> vide = new LinkedList<Tear>();
		for(Tear t:projectiles) {
			if(t.getPosition().getX()<0 || t.getPosition().getX()>1||t.getPosition().getY()>1||t.getPosition().getX()>1) {
				projectiles=vide;
			}
		}
		
	}
	public void updateGameObject()
	{
		move();
		for(Tear tear : projectiles)
			tear.updateGameObject();
		deleteProjectiles();
	}

	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}

	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		 showHealth();
		 showCoins();
		 for(Tear tear : projectiles)
				tear.draw();
	}
	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	public void goRightNext()
	{
		getDirection().addX(1);
	}
	/*
	 * shooting tears. Direction vector is later normalised.
	 */
	public void TearUp()
	{
		if((System.nanoTime() / 1000000000) - tearTime > 0.5){
		Tear tear = new Tear(this.position);
		projectiles.add(tear);
		tear.TearUpNext();
		tearTime = System.nanoTime() / 1000000000;
		}
	}

	public void TearDown()
	{
		if((System.nanoTime() / 1000000000) - tearTime > 0.5){
			Tear tear = new Tear(this.position);
			projectiles.add(tear);
			tear.TearDownNext();
			tearTime = System.nanoTime() / 1000000000;
			}
	}

	public void TearLeft()
	{
		if((System.nanoTime() / 1000000000) - tearTime > 0.5){
			Tear tear = new Tear(this.position);
			projectiles.add(tear);
			tear.TearLeftNext();
			tearTime = System.nanoTime() / 1000000000;
			}
	}

	public void TearRight()
	{
		if((System.nanoTime() / 1000000000) - tearTime > 0.5){
			Tear tear = new Tear(this.position);
			projectiles.add(tear);
			tear.TearRightNext();
			tearTime = System.nanoTime() / 1000000000;
			}
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}


	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public Vector2 getSize()
	{
		return size;
	}

	public void setSize(Vector2 size)
	{
		this.size = size;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public Vector2 getDirection()
	{
		return direction;
	}

	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}

	public int getHealth() {
		return health;
	}
	public void addHealth(int i) {
		if(health+i>6) {
			health=6;
		}else {
			if(health!=0) {
			health+=i;
			}
		}
	}
	public void showHealth() {
		int tmp=health;
		double x=0.12;
		while(tmp>0) {
			if(tmp%2==1) {
				StdDraw.picture(x,0.9,ImagePaths.HALF_HEART_HUD);
				tmp-=1;
			}else {
				StdDraw.picture(x,0.9,ImagePaths.HEART_HUD);
				tmp-=2;
			}
			x-=0.04;
		}
	}
	public void showCoins() {
		StdDraw.picture(0.04,0.8,ImagePaths.COIN);
		StdDraw.textLeft(0.05,0.8,""+coins);
	}
	public int getCoins() {
		return this.coins;
	}
	public void setCoins(int coins) {
		this.coins += coins;
	}

	public void addCoins(int i) {
		this.coins+=i;
	}

	public boolean isAddDmg() {
		return addDmg;
	}

	public void AddDmg() {
		this.addDmg = true;
	}

	public List<Tear> getProjectiles() {
		return projectiles;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

}
