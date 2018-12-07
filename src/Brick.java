
public class Brick {

    Position pos;
    int width, height;
    int id;

    public Brick(Position pos, int id, int width, int height) {
        this.pos = pos;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public Position getPosition() {
        return pos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
