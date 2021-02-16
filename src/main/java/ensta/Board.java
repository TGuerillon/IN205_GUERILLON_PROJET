package ensta;

public class Board {
    private String name;
    private char[][] Ships;
    private boolean[][] Hits;

    public Board(String name, int size) {
        this.name = name;
        this.Ships = new char[size][size];
        this.Hits = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.Ships[i][j] = '.';
                this.Hits[i][j] = false;
            }
        }
    }

    public Board(String name) {
        this.name = name;
        this.Ships = new char[10][10];
        this.Hits = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.Ships[i][j] = '.';
                this.Hits[i][j] = false;
            }
        }
    }

    public void print() {
        int length = this.Ships.length;
        System.out.println(this.name);
        System.out.print("Ships:");
        for (int i = 0; i < length; i++) {
            System.out.print(' ');
        }
        System.out.println("Hits:");
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 2 * length + 4; j++) {
                if (j < length) {
                    System.out.print(this.Ships[i][j]);
                }
                if (length < j && j < length + 4) {
                    System.out.print(' ');
                }
                if (j > length + 4) {
                    System.out.print(this.Hits[i][j - length - 4] ? 'x' : '.');
                }
            }
            System.out.println();
        }
    }
}