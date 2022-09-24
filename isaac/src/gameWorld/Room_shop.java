package gameWorld;

import gameobjects.*;	
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room_shop extends Room {
	public Room_shop(Hero hero) {
		super(hero);
		doors();
		objects();
		// TODO Auto-generated constructor stub
	}

	private void doors() {
		doorRoom.clear();
		Door doorUp_shop=new DoorUp(true);
		doors.add(doorUp_shop);
		Door doorRight_shop=new DoorRight(true);
		doors.add(doorRight_shop);
	}
	private void objects() {
		objs.add(new Half_Heart(new Vector2(0.4,0.5),new Vector2(0.04,0.04),hero));
		objs.add(new Heart(new Vector2(0.5,0.5),new Vector2(0.04,0.04),hero));
	    objs.add(new BloodOfMartyr(new Vector2(0.6,0.5),new Vector2(0.04,0.04),hero));
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
					StdDraw.picture(position.getX(),position.getY(),ImagePaths.WALL,RoomInfos.TILE_SIZE.getX(),
							RoomInfos.TILE_SIZE.getY());
				}
			}
		}
		for(Door d:doors) {
			d.drawGameObject();
		}
		for(Obj o:objs) {
			o.drawGameObject();
			o.drawPrix();
			}
		hero.drawGameObject();
	}
	
}
