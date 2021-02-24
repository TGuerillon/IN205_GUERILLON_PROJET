package ensta;

import ship.*;

public class TestBoard {
    public static void main(String[] args) {
        Board what = new Board("hey",5);
        AbstractShip ship = new Destroyer(orientation.NORTH);
        what.putShip(ship, 2, 3);
        what.print();
    }
}
