package gonzalezz;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TerrainBlock {
    private int x;
    private int y;
    private ImageView view;
    
    public TerrainBlock(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.view = new ImageView(image);
        this.view.setX(x);
        this.view.setY(y);
    }
    
    public ImageView getView() {
        return view;
    }
}
