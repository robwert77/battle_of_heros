package gonzalezz;

public class StartingTerrain extends Background {
    private Sprite[] bushBlock = new Sprite[5];
    private Sprite[] addOn2 = new Sprite[10];
    private Sprite[] addOn4 = new Sprite[10];
    private Sprite[] addOn3 = new Sprite[10];
    private Sprite[] treeBlock = new Sprite[3];
    private Sprite[] fenceBlock = new Sprite[3];
    private Sprite[] houseBlock = new Sprite[2];


    public StartingTerrain() {
        createStartingTerrain();
    }

    private void createStartingTerrain() {
        bushBlock[0] = new Sprite(Resources.BUSH);
        bushBlock[0].relocate(0, 275);
        bushBlock[0].relocate(0, 275);
        bushBlock[1] = new Sprite(Resources.BUSH);
        bushBlock[1].relocate(60, 276);
        bushBlock[2] = new Sprite(Resources.BUSH);
        bushBlock[2].relocate(120, 275);
        bushBlock[3] = new Sprite(Resources.BUSH);
        bushBlock[3].relocate(0, 60);

        addOn2[0] = new Sprite(Resources.ADDON2);
        addOn2[0].relocate(350, 280);
        addOn2[1] = new Sprite(Resources.ADDON2);
        addOn2[1].relocate(370, 280);

        addOn4[0] = new Sprite(Resources.ADDON4);
        addOn4[0].relocate(340, 300);
        addOn4[1] = new Sprite(Resources.ADDON4);
        addOn4[1].relocate(360, 305);
        for (int i = 2; i < 8; i++) {
            addOn2[i] = new Sprite(Resources.ADDON2);
            addOn4[i] = new Sprite(Resources.ADDON4);
            addOn3[i] = new Sprite(Resources.ADDON3);
            int max = 700;
            int min = 480;
            addOn3[i].relocate(Math.random() * (400 - 0 + 1), Math.random() * (max - min + 1) + min - 420);
            addOn2[i].relocate(Math.random() * (400 - 0 + 1), Math.random() * (max - min + 1) + min - 420);
            addOn4[i].relocate(Math.random() * (400 - 0 + 1), Math.random() * (max - min + 1) + min - 420);

            getChildren().addAll(addOn2[i], addOn4[i], addOn3[i]);
        }

        treeBlock[0] = new Sprite(Resources.TREE);
        treeBlock[0].relocate(330, 130);

        treeBlock[1] = new Sprite(Resources.TREE);
        treeBlock[1].relocate(350, 30);

        fenceBlock[0] = new Sprite(Resources.FENCE);
        fenceBlock[0].relocate(0, 0);

        // add another fence
        fenceBlock[1] = new Sprite(Resources.FENCE);
        fenceBlock[1].relocate(300, 0);

        Sprite floor = new Sprite(Resources.FLOOR);
        floor.relocate(2, 335);

        houseBlock[0] = new Sprite(Resources.BUILDING1);
        houseBlock[0].relocate(15, 40);

        getChildren().addAll(bushBlock[0], bushBlock[1], bushBlock[2], addOn2[0], addOn4[0], addOn2[1],
                addOn4[1], treeBlock[1], treeBlock[0], houseBlock[0], fenceBlock[0], fenceBlock[1], bushBlock[3],
                floor);
        setTranslateY(420);
    }

    @Override
    public void updateNormal(double elapsedTime) {
        super.updateNormal(elapsedTime);

        
    }
}
