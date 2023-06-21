package gonzalezz;

import javafx.scene.image.Image;

public class Frog extends Sprite {

    private int numberOfJumps = 0;
    private double speed = 0.5;
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
    private double frameTime;

    Frog() {
        super.setImage(Resources.PLAYER_IDLE);
        super.setPosition(150, 650);
        super.setVisible(false);
    }

    public void update(double time) {
        super.update(time);

        setTranslateY(getTranslateY() + speed);

        if (!animationEnded) {
            if (frameTime <= 0) {
                currentFrame++;
                if (currentFrame >= frames.length)
                    currentFrame = 0;
                super.setImage(frames[currentFrame]);

                if (frames[currentFrame] == frames[19]) {
                    frameTime = 0;
                    animationEnded = true;
                    System.out.println("Animation ended");
                    setVelocityX(0);
                } else {
                    setTranslateY(getTranslateY() - 2.8);
                    frameTime = 0.008;
                }
            } else {
                frameTime -= time;
            }
        }
    }

    public void startJump() {
        if (animationEnded) {
            animationEnded = false;
        }
    }

    public void jumpLeft() {
        if (animationEnded) {
            animationEnded = false;
            setVelocityX(-50);
        }
    }

    public void jumpRight() {
        if (animationEnded) {
            animationEnded = false;
            setVelocityX(50);
        }
    }

    public boolean getAnimationEnded() {
        return animationEnded;
    }

    public int getNumberOfJumps() {
        return numberOfJumps;
    }

    public int addJump() {
        return numberOfJumps++;
    }

}
