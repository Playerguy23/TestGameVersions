package main;

import main.gfx.GameCamera;
import main.inputs.KeyManager;
import main.states.maps.Map;

public class Handler {
	private Game game;
	private Map map;
	
	public Handler(Game game) {
		this.game = game;
	}

	public KeyManager getKeyManager() { return game.getKeyManager(); }
	public GameCamera getGameCamera() { return game.getGameCamera(); }
	public int getWidth() { return game.getWidth(); }
	public int getHeight() { return game.getHeight(); }
	public Game getGame() { return game; }
	public void setGame(Game game) { this.game = game; }
	public Map getMap() { return map; }
	public void setMap(Map map) { this.map = map; }
	
}
