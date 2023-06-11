package gonzalezz;

import javafx.scene.image.Image;

public class Frog extends Sprite {

    private boolean animationEnded = true;

    private final static Image[] frames = {
            new Image("file:resource/Moving_Frog/Moving_1.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_2.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_3.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_4.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_5.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_6.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_7.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_8.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_9.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_10.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_11.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_12.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_13.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_14.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_15.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_16.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_17.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_18.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_19.png", 160, 0, true, true),
            new Image("file:resource/Moving_Frog/Moving_20.png", 160, 0, true, true)
    };
    private int currentFrame = 0;
    private double frameTime = 0.08;

    Frog() {
        super.setImage(Resources.PLAYER_IDLE);
    }

    public void update(double time) {
        super.update(time);

        if (!animationEnded) {
            if (frameTime <= 0) {
                currentFrame++;
                if (currentFrame >= frames.length)
                    currentFrame = 0;
                super.setImage(frames[currentFrame]);

                if (frames[currentFrame] == frames[19]) {
                    frameTime = 0;
                    animationEnded = true;
                    super.setVelocityY(0);
                } else {
                    super.setVelocityY(-131);
                    frameTime = 0.02;
                }
            } else {
                frameTime -= time;
            }
        }
    }

    public void startJump() {
        if (animationEnded) {
            animationEnded = false;
            currentFrame = 0;
            frameTime = 0.08;
        }
    }
}
