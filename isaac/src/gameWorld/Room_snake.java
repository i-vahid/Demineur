package gameWorld;

import gameobjects.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room_snake extends Room{
	public Room_snake(Hero hero) {
		super(hero);
		doors();
		addSnake();
		obsadd();
		objadd();
		
	}
	private void objadd() {
		objs.add(new Penny(new Vector2(0.4,0.5),new Vector2(0.04,0.04),hero));
		objs.add(new Penny(new Vector2(0.7,0.5),new Vector2(0.04,0.04),hero));
	}
	private void obsadd() {
		obs.add(new Spikes(new Vector2(0.3,0.3),hero));
		obs.add(new Spikes(new Vector2(0.6,0.6),hero));
		obs.add(new Spikes(new Vector2(0.4,0.5),hero));
		obs.add(new Spikes(new Vector2(0.7,0.2),hero));
		obs.add(new Spikes(new Vector2(0.2,0.7),hero));
		obs.add(new Spikes(new Vector2(0.8,0.8),hero));
		obs.add(new Spikes(new Vector2(0.7,0.8),hero));
	}
	private void addSnake() {
		double x=0.25;
		for(int i=0;i<3;i++) {
			monsters.add(new Snake(new Vector2(x,0.8)));
			x+=0.25;
		}
	}
	private void doors() {
		doorRoom.clear();
		Door doorDown_snake=new DoorDown(false);
		doors.add(doorDown_snake);
		Door doorRight_snake=new DoorRight(false);
		doors.add(doorRight_snake);
		
		
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
					StdDraw.picture(position.getX(),position.getY(),ImagePaths.WALL4,RoomInfos.TILE_SIZE.getX(),
							RoomInfos.TILE_SIZE.getY());
				}
			}
		}
		for(Door d:doors) {
			d.drawGameObject();
		}
		for(Monstre s:monsters) {
			if(s instanceof Snake) {
				s.draw();
			}
		}
		for(Obstacles o:obs) {
			o.drawGameObject();
		}
		if(monsters.isEmpty()) {
			for(Obj o:objs) {
				o.drawGameObject();
				o.setPrix(0);
			}
		}
		hero.drawGameObject();
	}
	

}