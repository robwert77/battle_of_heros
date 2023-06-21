package gonzalezz;

public class RoadTerrain extends Background {

    private Car car1 = new Car();

    public RoadTerrain() {
        createRoadTerrain();
    }

    private void createRoadTerrain() {
        Sprite road = new Sprite(Resources.STREET);
        road.relocate(0, 35);

        // make to side walks
        Sprite sideWalk = new Sprite(Resources.FLOOR);
        sideWalk.relocate(0, 0);

        Sprite sideWalk2 = new Sprite(Resources.FLOOR);
        sideWalk2.relocate(0, 205);

        getChildren().addAll(road, sideWalk, sideWalk2, car1);

        setTranslateY(175);
    }

    @Override
    public void updateB(double elapsedTime, Background source, Frog frog) {
        super.updateB(elapsedTime, source, frog);
        car1.update(elapsedTime);
    }
}