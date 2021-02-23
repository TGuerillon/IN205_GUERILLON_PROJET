package ship;

public class Submarine extends AbstractShip {
    public Submarine(orientation course) {
        super('S', "Submarine", 3, course);
    }

    public Submarine() {
        this(orientation.EAST);
    }
}
