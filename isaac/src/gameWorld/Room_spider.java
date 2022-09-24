package gameWorld;
	
import gameobjects.*;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room_spider extends Room {
	
	public Room_spider(Hero hero) {
		super(hero);
		doors();
		addspiders();
		listobj();
	}
	private void addspiders() {
		double x=0.2;
		for(int i=0;i<4;i++) {
		monsters.add(new Spider(new Vector2(x,0.4)));
		x+=0.2;
		}
	}
	private void listobj() {
		objs.add(new Half_Heart(new Vector2(0.5,0.5),new Vector2(0.04,0.04),hero));
		objs.add(new Penny(new Vector2(0.4,0.5),new Vector2(0.04,0.04),hero));
	}
	private void doors() {
		doorRoom.clear();
		Door doordown_spider=new DoorDown(false);
		doors.add(doordown_spider);
		Door doorleft_spider=new DoorLeft(false);
		doors.add(doorleft_spider);
	}
	
	public void drawRoom()
	{
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
					StdDraw.picture(position.getX(),position.getY(),ImagePaths.WALL3,RoomInfos.TILE_SIZE.getX(),
							RoomInfos.TILE_SIZE.getY());
				}
			}
		}
		for(Door d:doors) {
			d.drawGameObject();
		}
		for(Monstre m:monsters) {
			if(m instanceof Spider)  m.draw();
		}
		hero.drawGameObject();
		if(monsters.isEmpty()) {
			for(Obj o:objs) {
				o.drawGameObject();
				o.setPrix(0);
			}
		}
	}
	

}
