public class Ball {

    Direction vector;
    Position position;
    double speed;
    double size;
    int id;

    public Ball(Position position, int id) {
        vector = new Direction(1,1);
        speed = 1;
        size = 10;
        this.position = position;
        this.id = id;
    }

    public Direction getVector() {
        return vector;
    }

    public Position getPosition() {
        return position;
    }

    public double getSpeed() {
        return speed;
    }

    public double getSize() {
        return size;
    }

    public int getId() {
        return id;
    }

    public void setVector(Direction vector) {
        this.vector = vector;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setId(int id) {
        this.id = id;
    }
}
