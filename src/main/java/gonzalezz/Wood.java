package gonzalezz;

import javafx.scene.image.Image;

public class Wood extends Sprite {

    private final static Image[] types = {
            new Image("file:resource/Terrain/Wood01.png", 150, 0, true, true),
            new Image("file:resource/Terrain/Wood02.png", 140, 0, true, true),
            new Image("file:resource/Terrain/Wood03.png", 140, 0, true, true),
    };

    // random number 0-2
    private int type = (int) (Math.random() * 3);

    public Wood() {
        super.setImage(types[type]);
        this.setVelocityX((int) (Math.random() * (70 - 40) + 40));
        this.setPositionX((int) (Math.random() * (450)));
        this.setPositionY(80);
    }

    public void updateW(double time, Wood woods) {
        super.update(time);

        if (intersect(woods)) {
            setVelocityX(-getVelocityX());
            woods.setVelocityX(150);
        }
    }
}
