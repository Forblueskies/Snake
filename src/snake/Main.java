package snake;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;

public class Main {
	private static final int WINDOW_WIDTH = 1024;
	private static final int WINDOW_HEIGHT = 768;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RenderWindow window = new RenderWindow();
		window.create(new VideoMode(WINDOW_WIDTH, WINDOW_HEIGHT), "Click le clown", WindowStyle.DEFAULT);

		Game game = new Game();
		Clock clock = new Clock();
		while (window.isOpen()) {
			
			window.clear();
			
			// Pool events
			Event event = null;
			while ((event = window.pollEvent()) != null) {
				// Handle close event
				if (event.type == Event.Type.CLOSED) {
					window.close();
					return;
				}
				// Escape
				if (event.type == Event.Type.KEY_RELEASED) {
					if (event.asKeyEvent().key == Key.ESCAPE || event.asKeyEvent().key == Key.K) {
						return;
					}
				}
				// Keyboard direction
				if (event.type == Event.Type.KEY_RELEASED) {
					if (event.asKeyEvent().key == Key.LEFT) { game.setDirection(Player.Direction.LEFT); }
					if (event.asKeyEvent().key == Key.RIGHT) { game.setDirection(Player.Direction.RIGHT); }
					if (event.asKeyEvent().key == Key.UP) { game.setDirection(Player.Direction.TOP); }
					if (event.asKeyEvent().key == Key.DOWN) { game.setDirection(Player.Direction.BOTTOM); }
				}
			}
			
			if (game.isRunning()) {
				game.draw(window);
				
				if (clock.getElapsedTime().asMilliseconds() >= 200) {
					clock.restart();
					game.update();
				}
			}
			
			window.display();
		}
	}

}
