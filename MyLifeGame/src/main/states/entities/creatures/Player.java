package main.states.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Handler;
import main.gfx.Animations;
import main.gfx.Assets;
import main.states.entities.Entity;
import main.states.entities.items.inventory.Inventory;

public class Player extends Creature{
	// animations
	private Animations animDown;
	private Animations animUp;
	private Animations animLeft;
	private Animations animRight;
	
	// attack timer
	private long lastAttackTime;
	private long attackCooDown = 400;
	private long attackTimer = attackCooDown;
	
	// inventory
	private Inventory inventory;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
		
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
		
		// animations
		animDown = new Animations(450, Assets.player_down);
		animUp = new Animations(450, Assets.player_up);
		animRight = new Animations(450, Assets.player_right);
		animLeft = new Animations(450, Assets.player_left);
		
		// inventory
		inventory = new Inventory(handler);
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTime;
		lastAttackTime = System.currentTimeMillis();
		if(attackTimer < attackCooDown)
			return;
		if(inventory.isActive())
			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - cb.height;
		}else if(handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft) {
			ar.x = ar.x - ar.width;
			ar.y = cb.y + cb.height / 2 - arSize;
		}else if(handler.getKeyManager().aRight) {
			ar.x = ar.x + ar.width;
			ar.y = cb.y + cb.height / 2 - arSize;
		}else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getMap().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}
	}

	@Override
	public void update() {
		// animations
		animDown.update();
		animUp.update();
		animRight.update();
		animLeft.update();
		// movements
		move();
		getInputs();
		handler.getGameCamera().centerOnEntity(this);
		// attacks
		checkAttacks();
		// inventory
		inventory.update();
	}
	
	private void getInputs() {
		dx = 0;
		dy = 0;
		
		if(inventory.isActive())
			return;
		
		if(handler.getKeyManager().left) {
			dx = -speed;
		}
		
		if(handler.getKeyManager().right) {
			dx = speed;
		}
		
		if(handler.getKeyManager().up) {
			dy = -speed;
		}
		
		if(handler.getKeyManager().down) {
			dy = speed;
		}
	}
	
	@Override
	public void die() {
		System.exit(0);
//		active = true;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(dx < 0) {
			return animLeft.getCurrentFrames();
		}else if(dx > 0) {
			return animRight.getCurrentFrames();
		}else if(dy < 0) {
			return animUp.getCurrentFrames();
		}else {
			return animDown.getCurrentFrames();
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
}
