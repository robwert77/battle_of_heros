package gonzalezz;

import javafx.scene.Group;

public class Background extends Group {

    private double positionX = 0;
    private double positionY = 0;
    private double velocityX = 0;
    private double velocityY = 0;
    private double GAME_HEIGHT = 800;
    private double speed = 0.5;

    public void updateB(double elapsedTime, Background target) {
        positionX = positionX + velocityX * elapsedTime;
        positionY = positionY + velocityY * elapsedTime;

        relocate(positionX, positionY);

        setTranslateY(getTranslateY() + speed);
        if (getTranslateY() >= GAME_HEIGHT) {
            setTranslateY(target.getTranslateY() - getBoundsInParent().getHeight());
        }
    }

    public void updateNormal(double elapsedTime) {
        positionX = positionX + velocityX * elapsedTime;
        positionY = positionY + velocityY * elapsedTime;

        relocate(positionX, positionY);

        setTranslateY(getTranslateY() + speed);
    }

}
