package ship;

public class AbstractShip {
    private char label;
    private String name;
    private int size;
    private orientation course;

    public char getLabel() {
        return this.label;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public orientation getOrientation() {
        return this.course;
    }

    public void setOrientation(orientation course) {
        this.course = course;
    }

    public AbstractShip() {
    }

    public AbstractShip(char label, String name, int size, orientation course) {
        this.label = label;
        this.name = name;
        this.size = size;
        this.course = course;
    }
}
