package main.states.entities.statics.enemies;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;
import main.gfx.Animations;
import main.gfx.Assets;
import main.states.entities.Entity;
import main.states.entities.creatures.Creature;
import main.states.tiles.Tile;

public class Zombie extends Enemy{
	
	private float dx;
	private float dy;
	
	private boolean left;
	private boolean right;
	
	private Animations animDown;
	
	public Zombie(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
		
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
		
		animDown = new Animations(350, Assets.zombie);
		
		dx = 0;
		dy = 0;
	}

	private void move() {
		moveX();
		moveY();
	}
	
	private void moveX() {
		x += dx;
		
		dx = 1;
		
		if(right) {
			dx = 1;
		}
		
		if(left) {
			dx = -1;
		}
		
		
		if(dx > 0) {
			// right
			
			int tx = (int)(x + dx + bounds.x + bounds.width) / Tile.TILEWIDTH;
			
			if(checkCollisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT) && checkCollisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				right = false;
				left = true;
			}
		}else if(dx < 0) {
			// left
			
			int tx = (int)(x + dx + bounds.x) / Tile.TILEWIDTH;
			
			if(checkCollisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT) && checkCollisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				left = false;
				right = true;
			}
		}
	}
	
	private void moveY() {
		y += dy;
	}
	
	@Override
	public void update() {
		move();
		animDown.update();
		// attack
		checkAttacks();
	}
	
	private void checkAttacks() {
		Rectangle ar = new Rectangle();
		
		if(handler.getMap().getEntityManager().getPlayer().isActive()) {
			ar.x = (int)x;
			ar.y = (int)y;
			ar.width = width;
			ar.height = height;
		}
		
		for(Entity e : handler.getMap().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}
	}
	
	private boolean checkCollisionWithTile(int x, int y) {
		return handler.getMap().getTile(x, y).isSolid();
	}

	@Override
	public void die() {
		active = true;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(animDown.getCurrentFrames(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	}

}
