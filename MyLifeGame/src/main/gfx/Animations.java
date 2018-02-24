package main.gfx;

import java.awt.image.BufferedImage;

public class Animations {
	private int speed;
	private int index;
	
	private long lastTime;
	private long timer;
	
	private BufferedImage[] frames;
	
	public Animations(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void update() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}
	
	public BufferedImage getCurrentFrames() {
		return frames[index];
	}
}
