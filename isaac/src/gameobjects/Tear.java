package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.*;

public class Tear extends Projectile{

 public Tear(Vector2 position) {
	 super(position);
	 this.speed=TearInfos.VITESSE;
	 this.imagePath=TearInfos.IMAGEPATH;
	 this.size=TearInfos.SIZE;
	 this.reloadTime=TearInfos.RELOADTIME;
	 this.shooting_range=TearInfos.SHOOTING_RANGE;
	 this.damage=TearInfos.DAMAGE;
 }
 public Tear(Vector2 position,double damage) {
	 super(position);
	 this.speed=TearInfos.VITESSE;
	 this.imagePath=TearInfos.IMAGEPATH;
	 this.size=TearInfos.SIZE;
	 this.reloadTime=TearInfos.RELOADTIME;
	 this.shooting_range=TearInfos.SHOOTING_RANGE;
	 this.damage=damage;
 }
		
 public void updateGameObject()
	{
		move();
	}


	public void TearUpNext()
	{
		
		getDirection().addY(getSpeed());
	}
	
	public void TearDownNext()
	{
		getDirection().addY(-getSpeed());
	}

	public void TearLeftNext()
	{
		getDirection().addX(-getSpeed());
	}

	public void TearRightNext()
	{
		getDirection().addX(getSpeed());
	}

	
	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
	}

	public void draw()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}


	
}
