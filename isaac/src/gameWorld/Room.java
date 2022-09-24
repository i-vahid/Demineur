package gameWorld;

import gameobjects.*;
import libraries.*;
import resources.*;
import java.util.*;

public abstract class Room {
	protected Hero hero;
	protected Map<Door, Room> doorRoom;
	protected List<Door> doors;
	protected List<Monstre> monsters;
	protected List<Obstacles> obs;
	protected List<Obj> objs;
	double damageTime = System.nanoTime() / 1000000000;
	double startTime= System.nanoTime() / 1000000000;

	public Room(Hero hero) {
		this.hero = hero;
		this.doors = new ArrayList<Door>();
		this.monsters = new ArrayList<Monstre>();
		this.doorRoom = new HashMap<Door, Room>();
		this.obs = new ArrayList<Obstacles>();
		this.objs = new ArrayList<Obj>();
	}

	public List<Door> getDoors() {
		return doors;
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom() {
		makeHeroPlay();
		makeMonsterPlay();
		ObjectContact();
		ObstacleContact();
		MonsterContact();
		UpdateObjects();
		killMonsters();
		larmedmg();
		areDead();
		tirMonstre();
	}
	public boolean collisionRock() {
		for(Obstacles o:obs) {
		if(o instanceof Rock) {
		return (((Rock) o).collision());
		}
		}
		return false;
	}
	public void killAll() {
		monsters.clear();
	}

	public void larmedmg() {
		for(Tear t :hero.getProjectiles()) {
			for(Monstre mo:monsters) {
				if(Physics.rectangleCollision(t.getPosition(),t.getSize(),mo.getPosition(),mo.getSize())) {
					mo.removeHealth(t.getDamage());
				}
			}
		}
	}
	public void tirMonstre() {
		for(Monstre m:monsters) {
			if(m instanceof Fly) {
				if(colTirFlyHero(((Fly)m))&& (System.nanoTime() / 1000000000) - damageTime > 1) {
					hero.addHealth(-(int)m.getDamage());
					damageTime=System.nanoTime()/1000000000;
					}
				}
		}
	}
	public boolean colTirFlyHero(Fly f) {
		for(TireMonstre t:f.getProjectiles()) {
			if(Physics.rectangleCollision(hero.getPosition(),hero.getSize(),t.getPosition(), t.getSize())) {
				return true;
			}
		}
		return false;
		
	}
	public void areDead() {
		if(monsters.size()==0) {
			for(Door d:doors) {
				d.setOpened(true);
			}
		}
	}
	public void killMonsters() {
		List<Monstre> m=new ArrayList<Monstre>();
		for(Monstre mo:monsters) {
			if(mo.getHealth()>0) {
				m.add(mo);
			}
		}
		if(m.size()!=monsters.size()) {
			monsters=m;
		}
	}
	private void UpdateObjects() {
		List<Obj> tmp = new ArrayList<Obj>();
		for (Obj o : objs) {
			if (!o.isPicked()) {
				tmp.add(o);
			}
		}
		if (tmp.size() != objs.size()) {
			objs = tmp;
		}
	}

	private void ObjectContact() {
		for (Obj o : objs) {
			if (colHeroObj(o) && o.getPrix() <= hero.getCoins()) {
				o.effet();
				if(hero.getHealth()<6) {
				hero.setCoins(-o.getPrix());
				}else {
					if(o instanceof BloodOfMartyr) {
						hero.setCoins(-o.getPrix());
					}
				}
			}
		}
	}

	private void ObstacleContact() {
		for (Obstacles o : obs) {
			if (colHeroObs(o) && (System.nanoTime() / 1000000000) - damageTime > 1) {
				hero.addHealth(-o.getDmg());
				damageTime = System.nanoTime() / 1000000000;
			}
		}
	}

	private void MonsterContact() {
		for (Monstre m : monsters) {
			if (colHeroMonster(m) && (System.nanoTime() / 1000000000) - damageTime > 1) {
				hero.addHealth(-(int) m.getDamage());
				damageTime = System.nanoTime() / 1000000000;
			}
		}
	}

	private boolean colHeroObs(Obstacles o) {
		return Physics.rectangleCollision(hero.getPosition(), hero.getSize(), o.getPosition(), o.getSize());
	}

	private boolean colHeroMonster(Monstre m) {
		return Physics.rectangleCollision(hero.getPosition(), hero.getSize(), m.getPosition(), m.getSize());
	}

	private boolean colHeroObj(Obj c) {
		return (Physics.rectangleCollision(hero.getPosition(), hero.getSize(), c.getPosition(), c.getSize()));
	}

	private void makeMonsterPlay() {
		if (monsters.size() != 0) {
			
				for (Monstre m : monsters) {

					m.move();

				}
			
		}

	}

	private void makeHeroPlay() {
		hero.updateGameObject();
	}

	/*
	 * Drawing
	 */
	public abstract void drawRoom();

	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	public static Vector2 positionFromTileIndex(double indexX, double indexY) {
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}
}
