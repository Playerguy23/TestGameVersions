package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.display.Display;
import main.gfx.Assets;
import main.gfx.GameCamera;
import main.inputs.KeyManager;
import main.states.GameState;
import main.states.State;

public class Game implements Runnable{
	// window title
	private String title;
	
	// window size
	private int width;
	private int height;
	
	// display
	private Display display;
	
	// thread
	private Thread thread;
	private boolean running;
	
	// graphics
	private BufferStrategy bs;
	private Graphics g;
	
	// states
	private State gameState;
	
	// key manager
	private KeyManager keyManager;	
	
	// game camera
	private GameCamera gameCamera;
	
	// handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		keyManager = new KeyManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		
		Assets.init();
		
		handler = new Handler(this);
		
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		State.setState(gameState);
	}
	
	private void update() {
		keyManager.update();
		
		if(State.getState() != null)
			State.getState().update();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		// clear
		g.clearRect(0, 0, width, height);
		
		// render
		
		if(State.getState() != null)
			State.getState().render(g);
		
		// end render
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
				
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				render();
				delta--;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
	
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
