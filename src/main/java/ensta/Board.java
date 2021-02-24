package ensta;

import java.awt.Color;

import ship.*;

public class Board implements IBoard {
    private String name;
    private ShipState[][] Ships;
    private Boolean[][] Hits;

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
        Hits[x][y] = hit;
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
        System.out.println(this.name);
        System.out.print("Ships:");
        for (int i = 0; i < length; i++) {
            System.out.print(' ');
        }
        System.out.println("Hits:");
        for (int j = 0; j < length; j++) {
            for (int i = 0; i < 2 * length + 4; i++) {
                if (i < length) {
                    System.out.print(this.Ships[i][j].toString());
                }
                if (length < i && i < length + 4) {
                    System.out.print(' ');
                }
                if (i > length + 4) {
                    if (this.Hits[i - length - 4][j]){
                        System.out.print(ColorUtil.colorize('X', ColorUtil.Color.RED));
                    }
                    else if (!(this.Hits[i - length - 4][j])) {
                        System.out.print(ColorUtil.colorize('X', ColorUtil.Color.WHITE));
                    }
                    else {
                        System.out.print('.');
                    }
                }
            }
            System.out.println();
        }
    }
}