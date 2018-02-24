package main.states.entities.creatures;

import main.Handler;
import main.states.entities.Entity;
import main.states.tiles.Tile;

public abstract class Creature extends Entity{
	public static final int DEFAULT_WIDTH = 64;
	public static final int DEFAULT_HEIGHT = 64;
	public static final float DEFAULT_SPEED = 2.5f;	 
	
	protected float speed;
	
	// vectors
	protected float dx;
	protected float dy;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		
		dx = 0;
		dy = 0;
	}
	
	public void move() {
		if(!checkEntityCollision(dx, 0f))
			moveX();
		if(!checkEntityCollision(0f, dy))
			moveY();
	}
	
	public void moveX() {
		if(dx > 0) {
			// right
			
			int tx = (int)(x + dx + bounds.x + bounds.width) / Tile.TILEWIDTH;
			
			if(!checkCollisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT) && !checkCollisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += dx;
			}
		}else if(dx < 0) {
			// left
			
			int tx = (int)(x + dx + bounds.x) / Tile.TILEWIDTH;
			
			if(!checkCollisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT) && !checkCollisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += dx;
			}
		}
	}
	
	public void moveY() {
		if(dy < 0) {
			// up
			int ty = (int)(y + dy + bounds.y) / Tile.TILEHEIGHT;
			
			if(!checkCollisionWithTile((int)(x + bounds.x) / Tile.TILEWIDTH, ty) && !checkCollisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += dy;
			}
		}if(dy > 0) {
			// down
			int ty = (int)(y + dy + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(!checkCollisionWithTile((int)(x + bounds.x) / Tile.TILEWIDTH, ty) && !checkCollisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += dy;
			}
		}
	}
	
	protected boolean checkCollisionWithTile(int x, int y) {
		return handler.getMap().getTile(x, y).isSolid();
	}
}
