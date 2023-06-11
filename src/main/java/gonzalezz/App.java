package gonzalezz;

import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import animatefx.animation.*;
import javafx.stage.StageStyle;

public class App extends Application {

	// Window size constants
	public final static int GAME_WIDTH = 450;
	public final static int GAME_HEIGHT = 800;

	// Game states
	public static final int TITLE_SCREEN = 0;
	public static final int PLAYING = 1;
	public static final int TILE_GROUP = 2;

	private int gameState = TITLE_SCREEN;

	// needed variables
	private int frameNumber = 1;

	// used to track keys as they are pressed/released.
	private final HashSet keyboard = new HashSet();

	private GameTimer gameTimer = new GameTimer(time -> updateGame(time));

	private Sprite[] background = {
			new Sprite(new Image("file:resource/UI/LandingScreenBg.png", GAME_WIDTH, 0, true, true)),
			new Sprite(new Image("file:resource/UI/LandingScreenBg.png", GAME_WIDTH, 0, true, true))
	};

	// UI elements
	private Sprite forggieStart = new Sprite(new Image("file:resource/UI/Frogie.png", 180, 0, true, true));
	private Sprite titleImage = new Sprite(new Image("file:resource/UI/Logo.png", 340, 0, true, true));
	private Sprite letsGoText = new Sprite(new Image("file:resource/UI/LetsGo_00.png", 340, 0, true, true));
	private Frog player = new Frog();
	// Buttons
	private Button startButton = new Button();
	private Button optionsButton = new Button();
	private Sprite optionsButtonImg = new Sprite(new Image("file:resource/UI/Option_icon.png", 35, 0, true, false));
	private Sprite startButtonImg = new Sprite(new Image("file:resource/UI/MoreGameBtn.png", 85, 0, true, true));
	private Button leaderboardButton = new Button();
	private Sprite leaderboardButtonImg = new Sprite(
			new Image("file:resource/UI/LeaderboardBtn.png", 85, 0, true, true));

	// Terrain Blocks
	private Sprite[] bushBlock = new Sprite[20];
	private Sprite[] fenceBlock = new Sprite[20];
	private Sprite[] treeBlock = new Sprite[20];
	private Sprite[] houseBlock = new Sprite[20];
	private Sprite[] addOn2 = new Sprite[20];
	private Sprite[] addOn3 = new Sprite[20];
	private Sprite[] addOn4 = new Sprite[20];
	private Car car1 = new Car();
	private Sprite backgroundPlaying = new Sprite(new Image("file:resource/Terrain/Bg.png", 500, 0, true, true));
	// Game Screen Groups
	private Group startingTerrain = new Group();
	private Group roadTerrain = new Group();
	private Group[] gameScreens = {
			new Group(background[0], background[1], forggieStart, titleImage, startButton, leaderboardButton,
					letsGoText, optionsButton), // Add the background Sprites to the title screen group
			new Group(backgroundPlaying, roadTerrain, startingTerrain, player),
			new Group() // Add the playing screen elements to this group
	};

	public App() {
		newGame();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		createStartingTerrain();
		createRoadTerrain();
		Scene gameScene = new Scene(root, GAME_WIDTH, GAME_HEIGHT, Color.WHITE);
		primaryStage.setScene(gameScene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Crossy Froggie");
		primaryStage.show();

		gameScene.setOnKeyPressed(key -> keyPressed(key));
		gameScene.setOnKeyReleased(key -> keyReleased(key));
		root.getChildren().addAll(gameScreens[PLAYING], gameScreens[TITLE_SCREEN]);
		gameScreens[TITLE_SCREEN].setVisible(true);
		gameScreens[PLAYING].setVisible(true);

		forggieStart.setTranslateX(135);
		forggieStart.setTranslateY(350);

		titleImage.setTranslateX(55);
		titleImage.setTranslateY(100);

		startButton.setTranslateX(90);
		startButton.setTranslateY(570);
		startButton.setPrefSize(150, 50);
		startButton.setStyle("-fx-background-color: transparent;");
		startButton.setGraphic(startButtonImg);
		startButton.setOnMouseEntered(
				e -> startButtonImg.setImage(new Image("file:resource/UI/MoreGameBtn_hover.png", 85, 0, true, false)));
		startButton.setOnMouseExited(
				e -> startButtonImg.setImage(new Image("file:resource/UI/MoreGameBtn.png", 85, 0, true, false)));
		startButtonAction();

		leaderboardButton.setTranslateX(210);
		leaderboardButton.setTranslateY(570);
		leaderboardButton.setPrefSize(150, 50);
		leaderboardButton.setStyle("-fx-background-color: transparent;");
		leaderboardButton.setGraphic(leaderboardButtonImg);
		leaderboardButton.setOnMouseEntered(e -> leaderboardButtonImg
				.setImage(new Image("file:resource/UI/LeaderboardBtn_hover.png", 85, 0, true, false)));
		leaderboardButton.setOnMouseExited(e -> leaderboardButtonImg
				.setImage(new Image("file:resource/UI/LeaderboardBtn.png", 85, 0, true, false)));

		letsGoText.relocate(45, 200);
		;
		Shake letsGoShake = new Shake(letsGoText);
		letsGoShake.setCycleCount(1000); // 1000 shakes
		letsGoShake.setSpeed(0.1);
		letsGoShake.play();

		optionsButton.setTranslateX(10);
		optionsButton.setTranslateY(10);
		optionsButton.setPrefSize(50, 50);
		optionsButton.setStyle("-fx-background-color: transparent;");
		optionsButton.setGraphic(optionsButtonImg);
		optionsButton.setOnMouseEntered(e -> optionsButtonImg
				.setImage(new Image("file:resource/UI/Option_icon_hover.png", 35, 0, true, false)));
		optionsButton.setOnMouseExited(
				e -> optionsButtonImg.setImage(new Image("file:resource/UI/Option_icon.png", 35, 0, true, false)));

	}

	/**
	 * Setup all sprites for a new game
	 */
	public void newGame() {
		// Reset the player to the center of the screen
		player.setTranslateX(GAME_WIDTH / 2 - 85);
		player.setTranslateY(650);

	}

	/**
	 * Game updates happen as often as the timer can cause an event
	 */
	public void updateGame(double elapsedTime) {
		updateBackground(elapsedTime);
		updatePlayer(elapsedTime);

		if (keyboard.contains(KeyCode.SPACE) && gameState == PLAYING) {
			player.update(elapsedTime);
		}

		car1.update(elapsedTime);
	}

	/**
	 * Move the player left/right depending on keyboard input
	 * 
	 * @param elapsedTime
	 */
	public void updatePlayer(double elapsedTime) {

	}

	/**
	 * Updates the background to create road animation. Moves both images down the
	 * window.
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
	 * @param key         KeyEvent program is responding to
	 * @param elapsedTime
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
				Platform.exit();
				;
			}
		}

		if (gameState == PLAYING) {
			if (!keyboard.contains(KeyCode.SPACE)) {
				if (key.getCode() == KeyCode.SPACE) {
				}
			}
		}

		// record this particular key has been pressed:
		keyboard.add(key.getCode());
	}

	private void startButtonAction() {
		startButton.setOnAction(e -> {
			newGame();
			startGame();
			gameState = PLAYING;
		});
	}

	// starting terrain group for the game
	private void createStartingTerrain() {
		bushBlock[0] = new Sprite(Resources.BUSH);
		bushBlock[0].relocate(0, 695);
		bushBlock[0].relocate(0, 695);
		bushBlock[1] = new Sprite(Resources.BUSH);
		bushBlock[1].relocate(60, 696);
		bushBlock[2] = new Sprite(Resources.BUSH);
		bushBlock[2].relocate(120, 695);
		bushBlock[3] = new Sprite(Resources.BUSH);
		bushBlock[3].relocate(0, 480);

		addOn2[0] = new Sprite(Resources.ADDON2);
		addOn2[0].relocate(350, 700);
		addOn2[1] = new Sprite(Resources.ADDON2);
		addOn2[1].relocate(370, 700);

		addOn4[0] = new Sprite(Resources.ADDON4);
		addOn4[0].relocate(340, 720);
		addOn4[1] = new Sprite(Resources.ADDON4);
		addOn4[1].relocate(360, 725);
		for (int i = 2; i < 8; i++) {
			addOn2[i] = new Sprite(Resources.ADDON2);
			addOn4[i] = new Sprite(Resources.ADDON4);
			addOn3[i] = new Sprite(Resources.ADDON3);

			// use Math.random() to generate random number between 700 and 480
			int max = 700;
			int min = 480;
			addOn3[i].relocate(Math.random() * (400 - 0 + 1), Math.random() * (max - min + 1) + min);
			addOn2[i].relocate(Math.random() * (400 - 0 + 1), Math.random() * (max - min + 1) + min);
			addOn4[i].relocate(Math.random() * (400 - 0 + 1), Math.random() * (max - min + 1) + min);

			startingTerrain.getChildren().addAll(addOn2[i], addOn4[i], addOn3[i]);
		}

		treeBlock[0] = new Sprite(Resources.TREE);
		treeBlock[0].relocate(330, 550);

		treeBlock[1] = new Sprite(Resources.TREE);
		treeBlock[1].relocate(350, 450);

		fenceBlock[0] = new Sprite(Resources.FENCE);
		fenceBlock[0].relocate(0, 420);

		// add another fence
		fenceBlock[1] = new Sprite(Resources.FENCE);
		fenceBlock[1].relocate(300, 420);

		Sprite floor = new Sprite(Resources.FLOOR);
		floor.relocate(2, 755);

		houseBlock[0] = new Sprite(Resources.BUILDING1);
		houseBlock[0].relocate(15, 460);

		player.setPosition(0, 0);    

		startingTerrain.getChildren().addAll(bushBlock[0], bushBlock[1], bushBlock[2], addOn2[0], addOn4[0], addOn2[1],
				addOn4[1], treeBlock[1], treeBlock[0], houseBlock[0], fenceBlock[0], fenceBlock[1], bushBlock[3],
				floor, player);
	}

	public void createRoadTerrain() {
		Sprite road = new Sprite(Resources.STREET);
		road.relocate(0, 210);

		// make to side walks
		Sprite sideWalk = new Sprite(Resources.FLOOR);
		sideWalk.relocate(0, 175);

		Sprite sideWalk2 = new Sprite(Resources.FLOOR);
		sideWalk2.relocate(0, 380);

		car1.setPosition(-200, 200);

		roadTerrain.getChildren().addAll(road, sideWalk, sideWalk2, car1);
	}

	/**
	 * Removes a key from the "keyboard" HashSet when it is released
	 * 
	 * @param key KeyEvent that triggered this method call
	 */
	public void keyReleased(KeyEvent key) {
		// remove the record of the key being pressed:
		keyboard.remove(key.getCode());
	}

	/**
	 * Starts the game play from the title screen
	 */
	public void startGame() {
		gameScreens[TITLE_SCREEN].setVisible(false);
		gameScreens[PLAYING].setVisible(true);
		gameState = PLAYING;
		gameTimer.start();
	}

	/**
	 * Switch the program to a new state when the player loses (by hitting another
	 * car).
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
		} else {
			gameTimer.stop();
		}
	}

	// --- launch the program --------------------------------------------------
	public static void main(String[] args) {
		launch();
	}
}