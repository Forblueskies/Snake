package snake;
import org.jsfml.graphics.RenderWindow;

public class Game {
	
	public final static int GAME_WIDTH = 32;
	public final static int GAME_HEIGHT = 24;
	
	private Player 			_player;
	private Item[][]		_area;
	private boolean _isRunning;

	public Game() {
		_player = new Player();
		_area = new Item[GAME_WIDTH][GAME_HEIGHT];
		_isRunning = true;
		
		for (int i = 0; i < 32; i++) {
			addItem();
		}
	}

	private void addItem() {
		int x = (int)(Math.random() * GAME_WIDTH);
		int y = (int)(Math.random() * GAME_HEIGHT);
		_area[x][y] = new Item(x, y);
	}

	public void draw(RenderWindow window) {
		_player.draw(window);
		
		for (int x = 0; x < GAME_WIDTH; x++) {
			for (int y = 0; y < GAME_HEIGHT; y++) {
				if (_area[x][y] != null) {
					_area[x][y].draw(window);
				}
			}
		}
	}

	public void update() {
		_player.move();
		if (_player.check()) {
			_isRunning = false;
		}
		
		// Player take item
		if (_area[_player.getX()][_player.getY()] != null) {
			_area[_player.getX()][_player.getY()] = null;
			
			_player.addPart();
			
			addItem();
		}
	}

	public void setDirection(Player.Direction direction) {
		_player.setDirection(direction);
	}

	public boolean isRunning() {
		return _isRunning;
	}
	

}
