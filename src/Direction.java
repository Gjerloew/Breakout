
public class Direction {
    double x,y;

    public Direction(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Direction reflectX(){
        return new Direction(-x,y);
    }
    public Direction reflectY(){
        return new Direction(x,-y);
    }
}
