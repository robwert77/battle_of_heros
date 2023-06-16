package gonzalezz;

import javafx.scene.Group;

public class Background extends Group {

    private double positionX = 0;
    private double positionY = 0;
    private double velocityX = 0;
    private double velocityY = 0;
    private double speed = 1;

    public void updateB(double elapsedTime) {
        positionX = positionX + velocityX * elapsedTime;
        positionY = positionY + velocityY * elapsedTime;

        relocate(positionX, positionY);

        setTranslateY(getTranslateY() + speed);
    }

}
