package snake;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class Item {
	private RectangleShape 	_drawable;
	private int 			_x;
	private int 			_y;

	public Item(int x, int y) {
		_drawable = new RectangleShape(new Vector2f(32, 32));
		_drawable.setFillColor(new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));

		_x = x;
		_y = y;
	}

	public void draw(RenderWindow window) {
		_drawable.setPosition(_x * 32, _y * 32);
		window.draw(_drawable);
	}
}
