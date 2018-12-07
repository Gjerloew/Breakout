import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class GUI extends Application {

    final int W = 400;
    final int H = 400;

    GameController game;
    AnimationTimer timer;

    Random rng = new Random();

    double timeScalar = 1;

    @Override
    public void init(){}

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 500, 500);
        Pane gamePane = new Pane();

        root.setStyle("-fx-background-color: darkgrey;");

        gamePane.setMaxWidth(W);
        gamePane.setMaxHeight(H);
        gamePane.setStyle("-fx-background-color: lightgrey;");
        root.setCenter(gamePane);

        game = new GameController(W, H);

        VBox rightPane = new VBox();
        VBox leftPane = new VBox();
        HBox topPane = new HBox();
        HBox bottomPane = new HBox();

        root.setRight(rightPane);
        root.setLeft(leftPane);
        root.setTop(topPane);
        root.setBottom(bottomPane);

        primaryStage.setScene(mainScene);
        primaryStage.show();

        game.newBall(new Position(65, 50));
        game.newBrick(new Position(10, 10));

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                drawElements(gamePane);
                game.step(timeScalar);
            }
        };
        startTimer();
    }

    private void startTimer(){
        timer.start();
    }

    private void stopTimer(){
        timer.stop();
    }

    private void drawElements(Pane root){
        root.getChildren().clear();
        for (Ball b : game.getBalls()){
            Circle temp = new Circle(b.getPosition().getX(), b.getPosition().getY(), b.getSize(), Color.GREEN);
            root.getChildren().add(temp);
        }

        for(Brick br : game.getBricks()){
            Rectangle temp = new Rectangle(br.getPosition().getX(), br.getPosition().getY(), br.getWidth(), br.getHeight());
            temp.setFill(Color.BLUE);
            root.getChildren().add(temp);
        }
    }
}
