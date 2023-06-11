package gonzalezz;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends Group {

	private Image image;
	private ImageView imgView = new ImageView();
	private double positionX = 0;
	private double positionY = 0;
	private double velocityX = 0;
	private double velocityY = 0;
	private double width = 0;
	private double height = 0;
	private boolean isDead = false;

	public Sprite() {
		this.getChildren().add(imgView);
	}

	public Sprite(Image i) {
		image = i;
		width = (i != null) ? i.getWidth() : 0;
		height = (i != null) ? i.getHeight() : 0;
		imgView.setImage(i);

		this.getChildren().add(imgView);
	}

	public void setImage(Image i) {
		image = i;
		width = (i != null) ? i.getWidth() : 0;
		height = (i != null) ? i.getHeight() : 0;
		imgView.setImage(i);
	}

	public void getimage(Image i) {
		image = i;
		width = (i != null) ? i.getWidth() : 0;
		height = (i != null) ? i.getHeight() : 0;
		imgView.setImage(i);
	}

	public void setResizedImage(Image i, double width, double height) {
		Image resizedImage = new Image(i.getUrl(), width, height, true, false);
		image = resizedImage;
		this.width = (resizedImage != null) ? resizedImage.getWidth() : 0;
		this.height = (resizedImage != null) ? resizedImage.getHeight() : 0;
		imgView.setImage(resizedImage);
	}

	/**
	 * @return the positionX
	 */
	public double getPositionX() {
		return positionX;
	}

	/**
	 * @param positionX the positionX to set
	 */
	public void setPositionX(double positionX) {
		this.positionX = positionX;
		this.setLayoutX(positionX);
	}

	/**
	 * @return the positionY
	 */
	public double getPositionY() {
		return positionY;
	}

	/**
	 * @param positionY the positionY to set
	 */
	public void setPositionY(double positionY) {
		this.positionY = positionY;
		this.setLayoutY(positionY);
	}

	/**
	 * @return the velocityX
	 */
	public double getVelocityX() {
		return velocityX;
	}

	/**
	 * @param velocityX the velocityX to set
	 */
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	/**
	 * @return the velocityY
	 */
	public double getVelocityY() {
		return velocityY;
	}

	/**
	 * @param velocityY the velocityY to set
	 */
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Move the sprite to a new location
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
		this.relocate(positionX, positionY);
	}

	/**
	 * Set the speed of the sprite in both directions.
	 * 
	 * @param x
	 * @param y
	 */
	public void setVelocity(double x, double y) {
		velocityX = x;
		velocityY = y;
	}

	/**
	 * Add to the the speed of the sprite in both directions.
	 * 
	 * @param x
	 * @param y
	 */
	public void addVelocity(double x, double y) {
		velocityX += x;
		velocityY += y;
	}

	/**
	 * Update the position of the sprite based on an an elapsed time in seconds
	 * 
	 * @param elapsedTime
	 */
	public void update(double elapsedTime) {
		positionX = positionX + velocityX * elapsedTime;
		positionY = positionY + velocityY * elapsedTime;

		this.relocate(positionX, positionY);
	}

	/**
	 * Returns a rectangle that encloses the sprite.
	 * 
	 * @return
	 */
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}

	/**
	 * Determines if the given sprite intersects with this sprite
	 * 
	 * @param s - the other sprite to test
	 * @return true if the two sprites are touching, false otherwise.
	 */
	public boolean intersect(Sprite s) {
		return this.getBoundary().intersects(s.getBoundary());
	}

	public boolean isReadyForCleanup() {
		return isDead;
	}

	public void kill() {
		isDead = true;
	}

	public boolean collidesWith(Sprite s) {
		return false;
	}

}
