package gameWorld;

import gameobjects.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room_fly extends Room{
	public Room_fly(Hero hero,Room r) {
		super(hero);
		doors();
		addFly();
		obsadd();
		listobj(r);
		// TODO Auto-generated constructor stub
	}
	public void listobj(Room r) {
		objs.add(new Key(new Vector2(0.15,0.8),new Vector2(0.04,0.04),hero,r));
	}
	private void obsadd() {
		obs.add(new Spikes(new Vector2(0.2,0.3),hero));
		obs.add(new Rock(new Vector2(0.6,0.4),hero));
		obs.add(new Rock(new Vector2(0.8,0.7),hero));
		obs.add(new Rock(new Vector2(0.4,0.6),hero));
	}
	private void addFly() {
		double x=0.25;
		for(int i=0;i<3;i++) {
			monsters.add(new Fly(new Vector2(x,0.2),hero.getPosition()));
			x+=0.25;
		}
	}
	private void doors() {
		doorRoom.clear();
		Door doorUp_fly=new DoorUp(false);
		doors.add(doorUp_fly);
		
		
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
					StdDraw.picture(position.getX(),position.getY(),ImagePaths.ROOM2,RoomInfos.TILE_SIZE.getX(),
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
		for(Monstre f:monsters) {
			if(f instanceof Fly) {
				f.draw();
				((Fly) f).setPosition_hero(hero.getPosition());
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
