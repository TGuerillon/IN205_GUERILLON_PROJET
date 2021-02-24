package ensta;

import ship.*;

public class TestBoard {
    public static void main(String[] args) {
        Board what = new Board("hey", 10);
        AbstractShip ship = new Destroyer(orientation.NORTH);
        what.putShip(ship, 2, 3);
        what.print();
        try {
            what.sendHit(2, 3);
        } catch (Exception exception) {
            System.out.print("Already hit there");
        }
        try {
            what.sendHit(2, 2).toString();
        } catch (Exception exception) {
            System.out.print("Already hit there");
        }
        what.print();
    }
}
