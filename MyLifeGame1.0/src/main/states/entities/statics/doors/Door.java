package main.states.entities.statics.doors;

import java.awt.Graphics;

import main.Handler;

public abstract class Door {
	public static final int DEFAULT_DOOR_WIDTH = 100;
	public static final int DEFAULT_DOOR_HEIGHT = 100;
	
	// handler
	protected Handler handler;
	
	// position
	protected float x;
	protected float y;
	
	// size
	protected int width;
	protected int height;
	
	public Door(Handler handler, float x, float y) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		width = DEFAULT_DOOR_WIDTH;
		height = DEFAULT_DOOR_HEIGHT;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);

	public float getX() { return x; }
	public void setX(float x) { this.x = x; }
	public float getY() { return y; }
	public void setY(float y) { this.y = y; }
	public Handler getHandler() { return handler; }
	public void setHandler(Handler handler) { this.handler = handler; }
	
}