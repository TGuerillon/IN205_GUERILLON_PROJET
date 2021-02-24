package ensta;

import ensta.ColorUtil.Color;
import ship.AbstractShip;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    public void addStrike() throws Exception {
        if (!struck) {
            ship.addStrike();
            this.struck = true;
        } else {
            throw new Exception("Already hit there");
        }
    }

    public boolean isStruck() {
        return struck;
    }

    public String toString() {
        if (this.isStruck()) {
            return ColorUtil.colorize(String.valueOf(ship.getLabel()), Color.RED);
        } else {
            return ColorUtil.colorize(String.valueOf(ship.getLabel()), Color.WHITE);
        }
    }

    public boolean isSunk() {
        return ship.isSunk();
    }

    public AbstractShip getShip() {
        return ship;
    }

    public ShipState(AbstractShip ship) {
        this.ship = ship;
        this.struck = false;
    }
}
