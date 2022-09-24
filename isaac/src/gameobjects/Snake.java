package gameobjects;
import java.util.Random;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfos;

public class Snake extends Monstre{
	double tearTime=System.nanoTime();
	public Snake(Vector2 position) {
		super(MonstersInfos.SNAKE_SPEED,MonstersInfos.SNAKE_HP,MonstersInfos.SNAKE_DMG);
		this.position=position;
		this.direction=new Vector2(vitesse,0);
		this.imagePath=ImagePaths.SNAKE;
		this.size=MonstersInfos.SNAKE_SIZE;
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub

		Random r=new Random();
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = position.addVector(normalizedDirection);
		position=positionAfterMoving;
		boolean random_direction=r.nextBoolean();
		if(random_direction) {		
			random_direction=r.nextBoolean();
			if(position.getX()<0.85 && random_direction) {
				direction = new Vector2(vitesse,0);
			}
			else if(position.getX()>0.35){
				direction=new Vector2(-vitesse,0);
			}
		} 
		
		if(random_direction){		
			random_direction=r.nextBoolean();
			if(position.getY()<0.75 && random_direction) {
				direction=new Vector2(0,vitesse);
			}
			else if(position.getY()>0.35){
				direction=new Vector2(0,-vitesse);
			}
		}
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub

		StdDraw.picture(position.getX(),position.getY(),imagePath,size.getX(),size.getY());		
	}
	
}