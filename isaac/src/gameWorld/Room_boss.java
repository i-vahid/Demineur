package gameWorld;

import gameobjects.*;	
import libraries.*;
import resources.*;

public class Room_boss extends Room {
	public Room_boss(Hero hero) {
		super(hero);
		doors();
		addBoss();
		obsadd();
		// TODO Auto-generated constructor stub
	}
	private void addBoss() {
		monsters.clear();
		monsters.add(new Boss(new Vector2(0.8,0.5),hero.getPosition()));
	}
	private void obsadd() {
		obs.add(new Spikes(new Vector2(0.2,0.3),hero));
		obs.add(new Spikes(new Vector2(0.6,0.4),hero));
		obs.add(new Spikes(new Vector2(0.8,0.7),hero));
		obs.add(new Rock(new Vector2(0.4,0.6),hero));
	}
	private void doors() {
		doorRoom.clear();
		Door doorleft_boss=new DoorLeft(false);
		doors.add(doorleft_boss);
		doorRoom.put(doorleft_boss,new Room_respawn(hero));
	}
	public void drawRoom(){
		// For every tile, set background color.
		StdDraw.setPenColor(StdDraw.CYAN);
		for (int i = 0; i < RoomInfos.NB_TILES; i++)
		{
			for (int j = 0; j < RoomInfos.NB_TILES; j++)
			{
				if(i>0 && i<RoomInfos.NB_TILES-1 && j>0 && j<RoomInfos.NB_TILES-1) {
					Vector2 position = positionFromTileIndex(i, j);
					StdDraw.picture(position.getX(),position.getY(),ImagePaths.ROOM,RoomInfos.TILE_SIZE.getX(),
							RoomInfos.TILE_SIZE.getY());
				}
				else {
					Vector2 position = positionFromTileIndex(i, j);
					StdDraw.picture(position.getX(),position.getY(),ImagePaths.WALL2,RoomInfos.TILE_SIZE.getX(),
							RoomInfos.TILE_SIZE.getY());
				}
			}
		}
		for(Door d:doors) {
			d.drawGameObject();
		}
		for(Monstre m:monsters) {
			if(m instanceof Boss) {
			((Boss) m).setPosition_hero(hero.getPosition());
			}
			m.draw();
		}
		for(Obstacles o:obs) {
			o.drawGameObject();
		}
		hero.drawGameObject();
	}
	
	
}
