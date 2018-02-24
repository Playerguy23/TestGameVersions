package main.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	private boolean[] keys;
	private boolean[] justPressed;
	private boolean[] cantPressed;
	
	public boolean left;
	public boolean right;
	public boolean up;
	public boolean down;
	
	public boolean aDown;
	public boolean aUp;
	public boolean aRight;
	public boolean aLeft;
	
	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPressed = new boolean[keys.length];
	}
	
	public void update() {
		for(int i = 0; i < keys.length; i++) {
			if(cantPressed[i] && !keys[i]) {
				cantPressed[i] = false;
			}else if(justPressed[i]) {
				cantPressed[i] = true;
				justPressed[i] = false;
			}
			
			if(!cantPressed[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
		
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		
		aDown = keys[KeyEvent.VK_DOWN];
		aUp = keys[KeyEvent.VK_UP];
		aRight = keys[KeyEvent.VK_RIGHT];
		aLeft = keys[KeyEvent.VK_LEFT];
	}
	
	public boolean keyJustPressed(int keyCode) {
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
