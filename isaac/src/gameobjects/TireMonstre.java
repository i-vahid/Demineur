package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.TearInfos;

public class TireMonstre extends Projectile{
	 public TireMonstre(Vector2 position) {
		 super(position);
		 this.speed=HeroInfos.ISAAC_SPEED/2;
		 this.imagePath=ImagePaths.TIR;
		 this.size=new Vector2(0.01,0.01);
		 this.reloadTime=TearInfos.RELOADTIME;
		 this.shooting_range=TearInfos.SHOOTING_RANGE;
		 this.damage=1;
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
