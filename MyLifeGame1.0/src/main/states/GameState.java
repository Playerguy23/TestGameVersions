package main.states;

import java.awt.Graphics;

import main.Handler;
import main.states.maps.Map;

public class GameState extends State{
	
	private Map map;
	
	public GameState(Handler handler) {
		super(handler);
		
		map = new Map(handler, "res/maps/map1.txt");
		handler.setMap(map);
	}

	@Override
	public void update() {
		map.update();
	}

	@Override
	public void render(Graphics g) {
		map.render(g);
	}

}
