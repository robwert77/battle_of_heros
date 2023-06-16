package gonzalezz;

public class GrassTerrain extends Background {

    private Sprite[] addOn2 = new Sprite[10];
    private Sprite[] addOn4 = new Sprite[10];
    private Sprite[] addOn3 = new Sprite[10];

    public GrassTerrain() {
        createGrassTerrain();
    }

    private void createGrassTerrain() {
        Sprite building = new Sprite(Resources.BUILDING2);
        building.relocate(0, 0);

        for (int i = 3; i < 10; i++) {
            addOn2[i] = new Sprite(Resources.ADDON2);
            addOn4[i] = new Sprite(Resources.ADDON4);
            addOn3[i] = new Sprite(Resources.ADDON3);

            // use Math.random() to generate random number between 700 and 480
            int max = 120;
            int min = 210;
            addOn3[i].relocate(Math.random() * (400 - 0 + 1), Math.random() * (max - min + 1) + min);
            addOn2[i].relocate(Math.random() * (400 - 0 + 1), Math.random() * (max - min + 1) + min);
            addOn4[i].relocate(Math.random() * (400 - 0 + 1), Math.random() * (max - min + 1) + min);

           getChildren().addAll(addOn2[i], addOn4[i], addOn3[i]);
        }

        getChildren().addAll(building);
        setTranslateY(-200);
    }

}