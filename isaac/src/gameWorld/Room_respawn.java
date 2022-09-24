package gameWorld;

import gameobjects.*;
import libraries.*;
import resources.*;

public class Room_respawn extends Room{

	public Room_respawn(Hero hero) {
		super(hero);
		doors();
		monsters.add(new Fly(new Vector2(0,0),new Vector2(0,0)));
		// TODO Auto-generated constructor stub
	}
	private void doors() {
		doorRoom.clear();
		Door doorup_resp=new DoorUp(true);
		doors.add(doorup_resp);
		Door doordown_resp=new DoorDown(false);
		doors.add(doordown_resp);
		Door doorright_resp=new DoorRight(false);
		doors.add(doorright_resp);
		Door doorleft_resp=new DoorLeft(true);
		doors.add(doorleft_resp);
	}
	public void drawRoom(){
		// For every tile, set background color.
		StdDraw.setPenColor(StdDraw.CYAN);
		for (int i = 0; i < RoomInfos.NB_TILES; i++)
		{
			for (int j = 0; j < RoomInfos.NB_TILES; j++)
			{
				Vector2 position = positionFromTileIndex(i, j);
				if(i>0 && i<RoomInfos.NB_TILES-1 && j>0 && j<RoomInfos.NB_TILES-1) {
					
					StdDraw.picture(position.getX(),position.getY(),ImagePaths.ROOM,RoomInfos.TILE_SIZE.getX(),
							RoomInfos.TILE_SIZE.getY());
				}else {
					StdDraw.picture(position.getX(),position.getY(),ImagePaths.WALL,RoomInfos.TILE_SIZE.getX(),RoomInfos.TILE_SIZE.getY());
				}
			}
		}
		for(Door d:doors) {
			d.drawGameObject();
		}
		for(Obstacles o:obs) {
			o.drawGameObject();
		}
		hero.drawGameObject();
	}
	

}
