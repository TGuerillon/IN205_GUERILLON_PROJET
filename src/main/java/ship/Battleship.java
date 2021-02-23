package ship;

public class Battleship extends AbstractShip {
    public Battleship(orientation course) {
        super('B', "Battleship", 4, course);
    }

    public Battleship() {
        this(orientation.EAST);
    }
}
