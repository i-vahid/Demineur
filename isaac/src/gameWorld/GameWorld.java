package gameWorld;

import java.util.*;	

import gameobjects.*;
import libraries.*;
import resources.*;
public class GameWorld
{
	private List<Room> room;
	private Room currentRoom;
	private Hero hero;
	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		room=new ArrayList<Room>();
		getRooms(hero);
		currentRoom = room.get(0);
		
	}
	public void getRooms(Hero hero) {
		room.add(new Room_respawn(hero));  //0
		room.add(new Room_spider(hero));	//1
		room.add(new Room_snake(hero));		//2
		room.add(new Room_shop(hero));		//3
		room.add(new Room_boss(hero));		//4
		room.add(new Room_fly(hero,room.get(0)));//5
	}
	public void changeRoom() {
		for(Door d:currentRoom.getDoors()) {
			if(d instanceof DoorUp) {
				if(Physics.rectangleCollision(hero.getPosition(),hero.getSize(),d.getPosition(),d.getSize()) && d.isOpened()) {
					 if(currentRoom==room.get(0)) {
						 currentRoom=room.get(1);
					 }
					 else if(currentRoom==room.get(3)) {
						 currentRoom=room.get(2);
					 }
					 else {
						 currentRoom=room.get(0);
					 }
					if(currentRoom.monsters.isEmpty()) {
						for(Door dor:room.get(0).doors) {
							if(dor instanceof DoorDown) {
								dor.setOpened(true);
							}
						}
					}
					hero.setPosition(RoomInfos.POSITION_BOTTOM_OF_ROOM);
				}
				
			}else if(d instanceof DoorDown) {
				if(Physics.rectangleCollision(hero.getPosition(),hero.getSize(),d.getPosition(),d.getSize()) && d.isOpened()) {
					 if(currentRoom==room.get(0)) {
						 currentRoom=room.get(5);
					 }
					 else if(currentRoom==room.get(2)) {
						 currentRoom=room.get(3);
					 }
					 else {
						 currentRoom=room.get(0);
					 }
					hero.setPosition(RoomInfos.POSITION_TOP_OF_ROOM);
		        }
				
			}else if(d instanceof DoorLeft) {
				 if(Physics.rectangleCollision(hero.getPosition(),hero.getSize(),d.getPosition(),d.getSize())  && d.isOpened()) {
					 if(currentRoom==room.get(0)) {
						 currentRoom=room.get(3);
					 }
					 else if(currentRoom==room.get(1)) {
						 currentRoom=room.get(2);
					 }
					 else {
						 currentRoom=room.get(0);
					 }
					 hero.setPosition(RoomInfos.POSITION_RIGHT_OF_ROOM);
			        }
			}else {
				  if(Physics.rectangleCollision(hero.getPosition(),hero.getSize(),d.getPosition(),d.getSize())  && d.isOpened()) {
					  if(currentRoom==room.get(0)) {
						  currentRoom=room.get(4);
					  }
					  else if(currentRoom==room.get(2)) {
							 currentRoom=room.get(1);
						 }
					  else {
						  currentRoom=room.get(0);
					  }
						  hero.setPosition(RoomInfos.POSITION_LEFT_OF_ROOM);
				  }
			}
		}
	}
	public void openDoors() {
		if(room.get(1).monsters.isEmpty()) {
			room.get(0).doors.get(1).setOpened(true);
		}
	}
	public void processUserInput()
	{
		processKeysForMovement();
	}
	
	public void processUserTears() {

		processKeysForTear();
	}

	
	public boolean gameOver()
	{
		return (hero.getHealth()<=0)|| room.get(4).monsters.isEmpty();
	}

	public void updateGameObjects()
	{
		currentRoom.updateRoom();
		changeRoom();
		openDoors();
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
	}

	/*
	 * Keys processing
	 */

	private void processKeysForMovement()
	{
		if (StdDraw.isKeyPressed(Controls.goUp) )
		{
			if(hero.getPosition().getY()<0.9) hero.goUpNext();
			
		}
		if (StdDraw.isKeyPressed(Controls.goDown))
		{
			if(hero.getPosition().getY()>0.14)hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight))
		{
			if(hero.getPosition().getX()<0.86)  hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft))
		{
			if(hero.getPosition().getX()>0.14) hero.goLeftNext();
		}
		if(StdDraw.isKeyPressed(Controls.KillAll)) {
			currentRoom.killAll();
		}
		if(StdDraw.isKeyPressed(Controls.Argent)) {
			hero.addCoins(10);
		}
		if(StdDraw.isKeyPressed(Controls.Puissant)) {
			TearInfos.DAMAGE=100;
		}
		if(StdDraw.isKeyPressed(Controls.Rapide)) {
			hero.setSpeed(HeroInfos.ISAAC_SPEED*2);
		}
		if(StdDraw.isKeyPressed(Controls.Invincible)) {
			hero.setInvincible(!hero.isInvincible());
		}
	}
	private void processKeysForTear()
	{
		if (StdDraw.isKeyPressed(Controls.TearUp))
		{
			hero.TearUp();
		}
		if (StdDraw.isKeyPressed(Controls.TearDown))
		{
			hero.TearDown();
		}
		if (StdDraw.isKeyPressed(Controls.TearRight))
		{
			hero.TearRight();
		}
		if (StdDraw.isKeyPressed(Controls.TearLeft))
		{
			hero.TearLeft();
		}
	}
}
