package snake;
import java.util.ArrayList;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Player {

	public enum Direction {
		LEFT, RIGHT, TOP, BOTTOM
	}
	
	private RectangleShape 		_drawable;
	private Direction 			_direction;
	private int 				_x;
	private int 				_y;
	private ArrayList<Vector2i>	_parts;

	public Player() {
		_drawable = new RectangleShape(new Vector2f(32, 32));
		_drawable.setFillColor(Color.BLUE);

		_direction = Direction.RIGHT;
		
		_parts = new ArrayList<Vector2i>();
		
		_x = 0;
		_y = 0;
	}

	public void draw(RenderWindow window) {
		_drawable.setPosition(_x * 32, _y * 32);
		window.draw(_drawable);

		for (Vector2i part: _parts) {
			_drawable.setPosition(part.x * 32, part.y * 32);
			window.draw(_drawable);
		}
	}

	public void move() {
		// Update parts
		if (_parts.size() > 0) {
			_parts.remove(_parts.size() - 1);
			_parts.add(0, new Vector2i(_x, _y));
		}
		
		switch (_direction) {
		case BOTTOM:	_y++; break;
		case LEFT: 		_x--; break;
		case RIGHT: 	_x++; break;
		case TOP: 		_y--; break;
		default: break;
		}
		
		// Player touch bound
		if (_x == Game.GAME_WIDTH) { _x = 0; }
		if (_x == -1) { _x = Game.GAME_WIDTH - 1; }
		if (_y == Game.GAME_HEIGHT) { _y = 0; }
		if (_y == -1) { _y = Game.GAME_HEIGHT - 1; }
		
		System.out.println("x: " + (_x * 32) + ", y: " + (_y * 32));
	}

	public void setDirection(Direction direction) {
		if (direction == Direction.RIGHT && _direction != Direction.LEFT) { _direction = direction; }
		if (direction == Direction.TOP && _direction != Direction.BOTTOM) { _direction = direction; }
		if (direction == Direction.BOTTOM && _direction != Direction.TOP) { _direction = direction; }
		if (direction == Direction.LEFT && _direction != Direction.RIGHT) { _direction = direction; }
	}

	public int getX() { return _x; }
	public int getY() { return _y; }

	public void setX(int x) { _x = x; }

	public void addPart() {
		_parts.add(new Vector2i(_x, _y));
	}

	public boolean check() {
		for (Vector2i part: _parts) {
			if (_x == part.x && _y == part.y){
				return true;
			}
		}
		return false;
	}
}
