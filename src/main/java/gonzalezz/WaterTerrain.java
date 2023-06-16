package gonzalezz;

public class WaterTerrain extends Background {

    private Wood[] wood = new Wood[4];

    public WaterTerrain() {
        createWaterTerrain();
    }

    private void createWaterTerrain() {
        Sprite water = new Sprite(Resources.WATER);
        water.relocate(0, 9.5);

        Sprite divider = new Sprite(Resources.ADDON5);
        divider.relocate(0, 0);

        wood[0] = new Wood();
        wood[0].setPositionY(15);
        wood[1] = new Wood();
        wood[2] = new Wood();
        wood[3] = new Wood();
        wood[3].setPositionY(15);

        getChildren().addAll(water, divider, wood[0], wood[1], wood[2]);
        setTranslateY(40);
    }

    @Override
    public void updateB(double elapsedTime) {
        super.updateB(elapsedTime);

        wood[0].updateW(elapsedTime, wood[3]);
        wood[1].updateW(elapsedTime, wood[2]);
        wood[2].updateW(elapsedTime, wood[1]);
        wood[3].updateW(elapsedTime, wood[0]);

        for(int i = 0; i < wood.length; i++) {

            if(wood[i].getTranslateX() > 420) 
            {
                wood[i].setTranslateX(-100);
                System.out.println("Moved Wood " + i); 
            }
        }
    }
}
