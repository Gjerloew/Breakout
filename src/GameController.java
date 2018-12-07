import java.util.ArrayList;

public class GameController {

    ArrayList<Ball> balls = new ArrayList<>();
    ArrayList<Brick> bricks = new ArrayList<>();
    int ballIdCounter = 0;
    int brickIdCounter = 0;
    int width;
    int height;

    public GameController(int w, int h){
        this.width = w;
        this.height = h;
    }

    public void newBall(Position pos){
        Ball newBall = new Ball(pos, ballIdCounter);
        balls.add(newBall);
        ballIdCounter++;
    }

    public void newBrick(Position pos){
        Brick newBall = new Brick(pos, brickIdCounter, 50, 30);
        bricks.add(newBall);
        brickIdCounter++;
    }

    public void step(double scalar){
        for (Ball b : balls) {
            Position startPos = b.getPosition();
            double size = b.getSize();

            double newX = startPos.getX() + b.getVector().getX()*b.getSpeed()*scalar;
            double newY = startPos.getY() + b.getVector().getY()*b.getSpeed()*scalar;

            for (Brick br : bricks){

                double brXRight = br.getPosition().getX() + size;
                double brYBot = br.getPosition().getY() + size;
                double brXLeft = br.getPosition().getX() - size;
                double brYTop = br.getPosition().getY() - size;

                //check if the next position of a ball will intersect a brick
                if( (newX > brXLeft) && (newX < (brXRight + br.getWidth())) ){
                    if( (newY > brYTop) && (newY < (brYBot + br.getHeight())) ){
                        bricks.remove(br);

                        //if the ball is coming from directly underneath, the ball will always bounce on the Y-axis
                        if((startPos.getX() > brXLeft) && (startPos.getX() < (brXRight + br.getWidth()))){
                            b.setVector(b.getVector().reflectY());
                            newY = startPos.getY() + b.getVector().getY()*b.getSpeed()*scalar;
                            break;
                        }
                        //if the ball is coming from directly from the side, the ball will always bounce on the X-axis
                        if((startPos.getY() > brYTop) && (startPos.getY() < (brYBot + br.getHeight()))){
                            b.setVector(b.getVector().reflectX());
                            newX = startPos.getX() + b.getVector().getX()*b.getSpeed()*scalar;
                            break;
                        }

                        //1,1
                        if((b.getVector().getX() > 0) && (b.getVector().getY() > 0)){
                            if((newX - brXLeft) < (newY - brYTop)){
                                b.setVector(b.getVector().reflectX());
                                newX = startPos.getX() + b.getVector().getX()*b.getSpeed()*scalar;
                            } else {
                                b.setVector(b.getVector().reflectY());
                                newY = startPos.getY() + b.getVector().getY()*b.getSpeed()*scalar;
                            }
                        }

                        //-1,1
                        if((b.getVector().getX() < 0) && (b.getVector().getY() > 0)){
                            if(((brXRight + br.getWidth()) - newX) < (brYTop - newY)){
                                b.setVector(b.getVector().reflectX());
                                newX = startPos.getX() + b.getVector().getX()*b.getSpeed()*scalar;
                            } else {
                                b.setVector(b.getVector().reflectY());
                                newY = startPos.getY() + b.getVector().getY()*b.getSpeed()*scalar;
                            }
                        }

                        //1,-1
                        if((b.getVector().getX() > 0) && (b.getVector().getY() < 0)){
                            if((newX - brXLeft) < (newY - (brYBot + br.getHeight()))){
                                b.setVector(b.getVector().reflectX());
                                newX = startPos.getX() + b.getVector().getX()*b.getSpeed()*scalar;
                            } else {
                                b.setVector(b.getVector().reflectY());
                                newY = startPos.getY() + b.getVector().getY()*b.getSpeed()*scalar;
                            }
                        }

                        //-1,-1
                        if((b.getVector().getX() < 0) && (b.getVector().getY() < 0)){
                            if(((brXRight + br.getWidth()) - newX) < ((brYBot + br.getHeight()) - newY)){
                                b.setVector(b.getVector().reflectX());
                                newX = startPos.getX() + b.getVector().getX()*b.getSpeed()*scalar;
                            } else {
                                b.setVector(b.getVector().reflectY());
                                newY = startPos.getY() + b.getVector().getY()*b.getSpeed()*scalar;
                            }
                        }
                    }
                }
            }

            //Check wall bounce on X-axis
            if(((newX - size) < 0) || ((newX + size) > width)){
                b.setVector(b.getVector().reflectX());
                newX = startPos.getX() + b.getVector().getX()*b.getSpeed()*scalar;
            }

            //Check wall bounce on Y-axis
            if((newY - size) < 0 || ((newY + size) > height)){
                b.setVector(b.getVector().reflectY());
                newY = startPos.getY() + b.getVector().getY()*b.getSpeed()*scalar;
            }

            b.setPosition(new Position(newX, newY));
        }
    }

    public ArrayList<Ball> getBalls(){
        return balls;
    }

    public ArrayList<Brick> getBricks(){
        return bricks;
    }
}
