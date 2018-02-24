package main.gfx;

import main.Handler;
import main.states.entities.Entity;
import main.states.tiles.Tile;

public class GameCamera {
	private Handler handler;
	
	private float xOffset;
	private float yOffset;
	
	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void checkBlanckScreen() {
		if(xOffset < 0){
			xOffset = 0;
		}else if(xOffset > handler.getMap().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
			xOffset = handler.getMap().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		
		if(yOffset < 0){
			yOffset = 0;
		}else if(yOffset > handler.getMap().getHeight() * Tile.TILEHEIGHT - handler.getHeight()){
			yOffset = handler.getMap().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset = xAmt;
		yOffset = yAmt;
		checkBlanckScreen();
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 - e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 - e.getHeight() / 2;
		
		checkBlanckScreen();
	}


	public float getxOffset() { return xOffset; }
	public void setxOffset(float xOffset) { this.xOffset = xOffset; }
	public float getyOffset() { return yOffset; }
	public void setyOffset(float yOffset) {this.yOffset = yOffset; }
}
