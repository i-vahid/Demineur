package gameobjects;


import java.util.*;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfos;

public class Fly extends Monstre{
	private Vector2 position_hero;
	List<TireMonstre> projectiles;
	public List<TireMonstre> getProjectiles() {
		return projectiles;
	}
	double tearTime=System.nanoTime();
	public Fly(Vector2 position,Vector2 position_hero) {
		super(MonstersInfos.FLY_SPEED,MonstersInfos.FLY_HP,MonstersInfos.FLY_DMG);
		this.position=position;
		this.direction=new Vector2(vitesse,0);
		this.imagePath=ImagePaths.FLY;
		this.size=MonstersInfos.FLY_SIZE;
		this.setPosition_hero(position_hero);
		projectiles=new LinkedList<TireMonstre>();
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
		tir_projectile();
		for(TireMonstre tir : projectiles)tir.updateGameObject();
		
		
	}
	public void tir_projectile() {
		if(position_hero.getX()-position.getX()<0.3&&position_hero.getY()-position.getY()<0.3&&position_hero.getX()-position.getX()>-0.3&&position_hero.getY()-position.getY()>-0.3) {
			TireMonstre tir = new TireMonstre(this.position);
			projectiles.add(tir);
			if(position_hero.getX()>position.getX()) {
				if(position_hero.getY()>position.getY()) {
					tir.TearUpNext();
				}else {
					tir.TearRightNext();
				}
			}else {
				if(position_hero.getY()>position.getY()) {
					tir.TearLeftNext();
				}else {
					tir.TearDownNext();
				}
			}
		}
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub

		StdDraw.picture(position.getX(),position.getY(),imagePath,size.getX(),size.getY());		
		for(TireMonstre tir : projectiles)
			tir.draw();
	}
	public void setPosition_hero(Vector2 position_hero) {
		this.position_hero = position_hero;
	}
	
}
