package ship;

public class Carrier extends AbstractShip {
    public Carrier(orientation course) {
        super('C', "Carrier", 5, course);
    }

    public Carrier() {
        this(orientation.EAST);
    }
}
