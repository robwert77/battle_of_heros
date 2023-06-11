package gonzalezz;

import javafx.scene.image.Image;

public class Car extends Sprite {
    private final static Image[] frames = {
            new Image("file:resource/Car01/CarMoving_0.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_1.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_2.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_3.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_4.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_5.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_6.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_7.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_8.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_9.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_10.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_11.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_12.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_13.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_14.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_15.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_16.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_17.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_18.png", 110, 0, true, true),
            new Image("file:resource/Car01/CarMoving_19.png", 110, 0, true, true)
    };

    private int currentFrame = 1;
    private double frameTime = 0.08;

    Car() {
        super.setVelocityX(60);
    }

    public void update(double time) {
        super.update(time);

        if (frameTime <= 0) {
            currentFrame++;
            if (currentFrame >= frames.length)
                currentFrame = 0;

            super.setImage(frames[currentFrame]);
            if (super.getPositionX() > 450)
                super.setPositionX(-350);
            frameTime = 0.03;
        } else {
            frameTime -= time;
        }
    }

}
