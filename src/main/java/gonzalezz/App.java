package gonzalezz;

import java.util.HashSet;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class App extends Application {

	//Window size constants
	public final static int GAME_WIDTH = 450;
	public final static int GAME_HEIGHT = 800;

	//Game states
	public static final int TITLE_SCREEN = 0;
	public static final int PLAYING = 1;
	
	private int gameState = TITLE_SCREEN;

	// used to track keys as they are pressed/released.
	private final HashSet keyboard = new HashSet();

	private GameTimer gameTimer = new GameTimer(time -> updateGame(time));

	
	//Interface elements for game screens
	private Text title = new Text("Battle Heros");

    private Sprite background = new Sprite(new Image("file:/resource/UI/background.png", GAME_WIDTH, 0, true, true));
	
	//Game Screen Groups
	private Group[] gameScreens = {
			new Group(title),								
			new Group(background), 
	};
	
	
	public App() {
		newGame();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene gameScene = new Scene(root, GAME_WIDTH, GAME_HEIGHT, Color.DARKGRAY);
		primaryStage.setScene(gameScene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Crazy Highway!");
		primaryStage.show();
		
		gameScene.setOnKeyPressed(key -> keyPressed(key));
		gameScene.setOnKeyReleased(key -> keyReleased(key));
		root.getChildren().addAll(gameScreens[PLAYING], gameScreens[TITLE_SCREEN]);
		gameScreens[TITLE_SCREEN].setVisible(true);
		gameScreens[PLAYING].setVisible(true);
		
		//Styling for the Text on Game Screens
		title.setFont(Font.font("Harlow Solid Italic", 50));
		title.setFill(Color.RED);
		title.setEffect(new DropShadow());
		title.setStroke(Color.WHITE);
		title.setStrokeWidth(2);
		title.setLayoutX(GAME_WIDTH/2 - title.getLayoutBounds().getWidth()/2);
		title.setLayoutY(300);
	

	}

	/**
	 * Setup all sprites for a new game
	 */
	public void newGame() {
	}
	
	/**
	 * Game updates happen as often as the timer can cause an event
	 */
	public void updateGame(double elapsedTime) {
	}	
	

	/**
	 * Move the player left/right depending on keyboard input
	 * @param elapsedTime
	 */
	public void updatePlayer(double elapsedTime) {
	}
	
	/**
	 * Updates the background to create road animation.  Moves both images down the window.
	 * When an image passes below the bottom of the window, resets it to the top
	 * 
	 * @param elapsedTime amount of time passed since last update.
	 */
	public void updateBackground(double elapsedTime) {
	}
	
	
	/**
	 * Respond to key press events by checking for the pause key (P),
	 * and starting or stopping the game timer appropriately
	 * 
	 * Other key presses are stored in the "Keyboard" HashSet for polling
	 * during the main game update.
	 * 
	 * @param key KeyEvent program is responding to 
	 */
	public void keyPressed(KeyEvent key) {
		if (gameState == PLAYING) {
			if (!keyboard.contains(KeyCode.P)) {
				if (key.getCode() == KeyCode.P) {
					pause();
				}
			}
		}
		
		if (gameState == TITLE_SCREEN) {
			if (!keyboard.contains(KeyCode.SPACE)) {
				if (key.getCode() == KeyCode.SPACE) {
					startGame();
				}
			}
		}

		if (!keyboard.contains(KeyCode.ESCAPE)) {
			if (key.getCode() == KeyCode.ESCAPE) {
				Platform.exit();;
			}
		}

		//record this particular key has been pressed:
		keyboard.add(key.getCode());		
	}

	/**
	 * Removes a key from the "keyboard" HashSet when it is released
	 * 
	 * @param key KeyEvent that triggered this method call
	 */
	public void keyReleased(KeyEvent key) {
		//remove the record of the key being pressed:
		keyboard.remove(key.getCode());
	}

	/**
	 * Starts the game play from the title screen
	 */
	public void startGame() {
		gameScreens[TITLE_SCREEN].setVisible(false);
		gameState = PLAYING;
		gameTimer.start();
	}
	
	/**
	 * Switch the program to a new state when the player loses (by hitting another car).
	 */
	public void gameOver() {
		gameTimer.stop();
	}
	
	
	/**
	 * Pause or unpause the game
	 */
	public void pause() {
		if (gameTimer.isPaused()) {
			gameTimer.start();
		}
		else {
			gameTimer.stop();
		}
	}

	//--- launch the program --------------------------------------------------
	public static void main(String[] args) { launch(); }
}