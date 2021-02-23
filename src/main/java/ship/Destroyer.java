package ship;

public class Destroyer extends AbstractShip {
    public Destroyer(orientation course) {
        super('D', "Destroyer", 2, course);
    }

    public Destroyer() {
        this(orientation.EAST);
    }
}
