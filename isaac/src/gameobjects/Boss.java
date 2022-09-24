package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfos;

public class Boss extends Monstre{
	

	private Vector2 position_hero;
	public Boss(Vector2 position,Vector2 position_hero) {
		super(MonstersInfos.BOSS_SPEED,MonstersInfos.BOSS_HP,MonstersInfos.BOSS_DMG);
		// TODO Auto-generated constructor stub
		this.position=position;
		this.size=MonstersInfos.BOSS_SIZE;
		this.direction=new Vector2(vitesse,0);
		this.imagePath=ImagePaths.MAGDALENE;
		this.position_hero=position_hero;
		
	}

	private void setDirection() {
		if(position_hero.getX()>position.getX()) {
			if(position_hero.getY()>position.getY()) {
				direction=new Vector2(vitesse,vitesse);
			}else {
				direction=new Vector2(vitesse,-vitesse);
			}
		}else {
			if(position_hero.getY()>position.getY()) {
				direction=new Vector2(-vitesse,vitesse);
			}else {
				direction=new Vector2(-vitesse,-vitesse);
			}
		}
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		setDirection();
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = position.addVector(normalizedDirection);
		position=positionAfterMoving;
	}
	@Override
	public void draw() {
		
		StdDraw.picture(position.getX(),position.getY(),imagePath,size.getX(),size.getY());
		
	}
	public void setPosition_hero(Vector2 position_hero) {
		this.position_hero = position_hero;
	}

}
