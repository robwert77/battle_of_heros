package gonzalezz;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import animatefx.animation.*;

public class App extends Application {

	// Window size constants
	public final static int GAME_WIDTH = 450;
	public final static int GAME_HEIGHT = 800;
	public final static int OFF_SCREEN = 720;

	// Game states
	public static final int TITLE_SCREEN = 0;
	public static final int PLAYING = 1;
	public static final int BACKGROUND = 2;

	private int gameState = TITLE_SCREEN;

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
	private Text numberOfJumpsText = new Text("" + player.getNumberOfJumps());
	private Button startButton = new Button();
	private Button optionsButton = new Button();
	private Sprite optionsButtonImg = new Sprite(new Image("file:resource/UI/Option_icon.png", 35, 0, true, false));
	private Sprite startButtonImg = new Sprite(new Image("file:resource/UI/MoreGameBtn.png", 85, 0, true, true));
	private Button leaderboardButton = new Button();
	private Sprite leaderboardButtonImg = new Sprite(
			new Image("file:resource/UI/LeaderboardBtn.png", 85, 0, true, true));
	private Sprite[] backgroundPlaying = {
			new Sprite(new Image("file:resource/Terrain/Bg.png", 500, 0, true, true)),
			new Sprite(new Image("file:resource/Terrain/Bg.png", 500, 0, true, true)) };

	private Group backgroundDisplay = new Group(backgroundPlaying[0], backgroundPlaying[1]);
	private ArrayList<Background> startingTerrains = new ArrayList<Background>();

	private Group[] gameScreens = {
			new Group(background[0], background[1], forggieStart, titleImage, startButton, leaderboardButton,
					letsGoText, optionsButton), // Add the background Sprites to the title screen group
			new Group(),
			new Group(backgroundDisplay) // Add the playing screen elements to this group
	};

	public App() {
		newGame();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();

		StartingTerrain startingTerrain = new StartingTerrain();
		RoadTerrain roadTerrain = new RoadTerrain();
		GrassTerrain grassTerrain = new GrassTerrain();
		WaterTerrain waterTerrain = new WaterTerrain();
		startingTerrains.add(waterTerrain);
		startingTerrains.add(grassTerrain);
		startingTerrains.add(startingTerrain);
		startingTerrains.add(roadTerrain);

		gameScreens[PLAYING].getChildren().addAll(startingTerrains.get(0), startingTerrains.get(1),
				startingTerrains.get(2), startingTerrains.get(3), player, numberOfJumpsText);

		Scene gameScene = new Scene(root, GAME_WIDTH, GAME_HEIGHT, Color.WHITE);
		primaryStage.setScene(gameScene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Crossy Froggie");
		primaryStage.show();

		gameScene.setOnKeyPressed(key -> keyPressed(key));
		gameScene.setOnKeyReleased(key -> keyReleased(key));
		root.getChildren().addAll(gameScreens[BACKGROUND], gameScreens[PLAYING], gameScreens[TITLE_SCREEN]);
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

		numberOfJumpsText.setFont(Font.loadFont("file:resource/Fonts/gomarice_no_continue.ttf", 50));
		numberOfJumpsText.setFill(Color.WHITE);
		numberOfJumpsText.setTextAlignment(TextAlignment.CENTER);
		numberOfJumpsText.setStroke(Color.BLACK);
		numberOfJumpsText.relocate((GAME_WIDTH - numberOfJumpsText.getBoundsInLocal().getWidth()) / 2, 20);

	}

	/**
	 * Setup all sprites for a new game
	 */
	public void newGame() {

		backgroundPlaying[0].setPositionY(0);
		backgroundPlaying[1].setPositionY(-background[1].getHeight());
		backgroundPlaying[0].setVelocityY(200);
		backgroundPlaying[1].setVelocityY(200);

		player.setVisible(true);
	}

	/**
	 * Game updates happen as often as the timer can cause an event
	 */
	public void updateGame(double elapsedTime) {

		// update the starting terrain
		startingTerrains.get(0).updateNormal(elapsedTime);
		startingTerrains.get(1).updateB(elapsedTime, startingTerrains.get(0));
		startingTerrains.get(2).updateB(elapsedTime, startingTerrains.get(1));
		startingTerrains.get(3).updateB(elapsedTime, startingTerrains.get(2));

		updateTerrain(elapsedTime);

		player.update(elapsedTime);

	}

	/**
	 * Move the player left/right depending on keyboard input
	 * 
	 * @param elapsedTime
	 */
	public void updatePlayer(double elapsedTime) {

	}

	public void updateTerrain(double elapsedTime) {
		double speed = 1;

		/*
		 * roadTerrain.setTranslateY(roadTerrain.getTranslateY() + speed);
		 * if (roadTerrain.getTranslateY() >= GAME_HEIGHT) {
		 * System.out.println("Moving Raod Terrain");
		 * roadTerrain.setTranslateY(grassTerrain.getTranslateY() -
		 * roadTerrain.getBoundsInParent().getHeight());
		 * roadTerrainOffCount += 1;
		 * }
		 */
		/*
		 * waterTerrain.setTranslateY(waterTerrain.getTranslateY() + speed);
		 * if (waterTerrain.getTranslateY() >= GAME_HEIGHT) {
		 * System.out.println("Moving water Terrain");
		 * waterTerrain.setTranslateY(startingTerrains.get(1).getTranslateY() -
		 * waterTerrain.getBoundsInParent().getHeight());
		 * }
		 */
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
			if (key.getCode() == KeyCode.SPACE && !keyboard.contains(KeyCode.SPACE)) {
				player.startJump(); // Make the player jump when SPACE is pressed
				player.addJump();
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
		gameScreens[BACKGROUND].setVisible(true);
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