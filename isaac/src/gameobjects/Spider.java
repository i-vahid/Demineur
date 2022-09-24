package gameobjects;

import java.util.*;	
import libraries.*;
import resources.*;

public class Spider extends Monstre{
	public Spider(Vector2 position) {
		super(MonstersInfos.SPIDER_SPEED,MonstersInfos.SPIDER_HP,MonstersInfos.SPIDER_DMG);
		this.position=position;
		this.direction=new Vector2(vitesse,0);
		this.imagePath=ImagePaths.SPIDER;
		this.size=MonstersInfos.SPIDER_SIZE;
	}

	@Override
	public void move() {

			Random r=new Random();
			Vector2 normalizedDirection = getNormalizedDirection();
			Vector2 positionAfterMoving = position.addVector(normalizedDirection);
			position=positionAfterMoving;
			boolean random_direction=r.nextBoolean();
			
		/*	if(random_direction) {		//			move diagonal 
				random_direction=r.nextBoolean();
				if(position.getX()<0.85 && position.getY()<0.75 && random_direction) {
					direction = new Vector2(vitesse,vitesse);
				}
				else if(position.getX()>0.35){
					direction=new Vector2(-vitesse,0);
				}
			} 
		*/
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
	
		StdDraw.picture(position.getX(),position.getY(),imagePath,size.getX(),size.getY());
		
	}
}
