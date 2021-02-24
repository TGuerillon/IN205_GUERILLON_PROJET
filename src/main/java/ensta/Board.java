package ensta;

import java.awt.Color;

import ship.*;

public class Board implements IBoard {
    private String name;
    private ShipState[][] Ships;
    private Boolean[][] Hits;

    public Hit sendHit(int x, int y) throws Exception {
        if (this.getHit(x, y) != null) {
            throw new Exception("Already shot there");
        } else if (this.hasShip(x, y)) {
            this.setHit(true, x, y);
            Ships[x][y].addStrike();
            if (Ships[x][y].isSunk()) {
                System.out.println(Ships[x][y].getShip().getName() + " has been sinked");
                return Hit.fromInt(Ships[x][y].getShip().getSize());
            } else {
                return Hit.STRIKE;
            }
        } else {
            this.setHit(false, x, y);
            return Hit.MISS;
        }
    }

    public int getSize() {
        return this.Hits[0].length;
    }

    public boolean hasShip(int x, int y) {
        return (Ships[x][y] != null);
    }

    public void putShip(AbstractShip ship, int x, int y) throws ArrayIndexOutOfBoundsException {
        int size = getSize();
        if (size < ship.getSize() || x >= size || y >= size || x < 0 || y < 0)
            throw new ArrayIndexOutOfBoundsException("Coordinates are outside the board");
        switch (ship.getOrientation()) {
            case EAST:
                if (size < x + ship.getSize())
                    throw new ArrayIndexOutOfBoundsException("Ship goes outside the board");
                for (int i = 0; i < ship.getSize(); i++) {
                    if (hasShip(x + i, y))
                        throw new ArrayIndexOutOfBoundsException("There is already a ship there");
                    Ships[x + i][y] = new ShipState(ship);
                }
                break;
            case WEST:
                if (x + 1 < ship.getSize())
                    throw new ArrayIndexOutOfBoundsException("Ship goes outside the board");
                for (int i = 0; i < ship.getSize(); i++) {
                    if (hasShip(x - i, y))
                        throw new ArrayIndexOutOfBoundsException("There is already a ship there");
                    Ships[x - i][y] = new ShipState(ship);
                }
                break;
            case NORTH:
                if (y + 1 < ship.getSize())
                    throw new ArrayIndexOutOfBoundsException("Ship goes outside the board");
                for (int i = 0; i < ship.getSize(); i++) {
                    if (hasShip(x, y - i))
                        throw new ArrayIndexOutOfBoundsException("There is already a ship there");
                    Ships[x][y - i] = new ShipState(ship);
                }
                break;
            case SOUTH:
                if (size < y + ship.getSize())
                    throw new ArrayIndexOutOfBoundsException("Ship goes outside the board");
                for (int i = 0; i < ship.getSize(); i++) {
                    if (hasShip(x, y + i))
                        throw new ArrayIndexOutOfBoundsException("There is already a ship there");
                    Ships[x][y + i] = new ShipState(ship);
                }
                break;
        }
    }

    public void setHit(boolean hit, int x, int y) {
        this.Hits[x][y] = hit;
    }

    public Boolean getHit(int x, int y) {
        return Hits[x][y];
    }

    public Board(String name, int size) {
        this.name = name;
        this.Ships = new ShipState[size][size];
        this.Hits = new Boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.Ships[i][j] = null;
                this.Hits[i][j] = null;
            }
        }
    }

    public Board(String name) {
        this(name, 10);
    }

    public void print() {
        int length = this.Ships[0].length;
        System.out.print("Ships:");
        for (int i = 0; i < length - 2; i++) {
            System.out.print(' ');
        }
        System.out.println("Hits:");
        for (int j = 0; j < length; j++) {
            for (int i = 0; i < 2 * length + 5; i++) {
                if (i < length) {
                    System.out.print((this.Ships[i][j] != null) ? this.Ships[i][j].toString() : '.');
                }
                if (length < i && i < length + 5) {
                    System.out.print(' ');
                }
                if (i >= length + 5) {
                    if (this.Hits[i - length - 5][j] == null) {
                        System.out.print('.');
                    } else if (this.Hits[i - length - 5][j] == false) {
                        System.out.print(ColorUtil.colorize('X', ColorUtil.Color.WHITE));
                    } else {
                        System.out.print(ColorUtil.colorize('X', ColorUtil.Color.RED));
                    }
                }
            }
            System.out.println();
        }
    }
}