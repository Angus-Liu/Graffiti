package shape;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * Shape Maker
 */
public class ShapeMaker extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        ObservableList<Node> groupNodes = group.getChildren();

        for (int i = 0; i < 600; i += 10) {
            Line l1 = new Line(i, 0, i, 600);
            l1.setStroke(Color.LIGHTGRAY);
            Line l2 = new Line(0, i, 600, i);
            l2.setStroke(Color.LIGHTGRAY);
            groupNodes.addAll(l1, l2);
        }

        Rectangle r1 = new Rectangle(50, 25, 100, 140);
        r1.setFill(null);
        r1.setStroke(Color.BLACK);
        r1.setStrokeWidth(3);

        Rectangle r2 = new Rectangle(250, 25, 100, 140);
        r2.setFill(null);
        r2.setStroke(Color.BLACK);
        r2.setStrokeWidth(3);
        r2.setArcWidth(25);
        r2.setArcHeight(25);

        Rectangle r3 = new Rectangle(450, 25, 100, 140);
        r3.setStroke(Color.BLACK);
        r3.setFill(null);
        r3.setStrokeWidth(3);
        r3.setArcWidth(75);
        r3.setArcHeight(125);

        groupNodes.addAll(r1, r2, r3);

        Circle c1 = new Circle(100, 300, 75);
        c1.setStroke(Color.BLACK);
        c1.setStrokeWidth(3);
        c1.setFill(null);
        groupNodes.add(c1);

        Ellipse e1 = new Ellipse(300, 300, 75, 40);
        e1.setStroke(Color.BLACK);
        e1.setStrokeWidth(3);
        e1.setFill(null);

        Ellipse e2 = new Ellipse(500, 300, 40, 75);
        e2.setStroke(Color.BLACK);
        e2.setStrokeWidth(3);
        e2.setFill(null);

        groupNodes.addAll(e1, e2);

        Arc a1 = new Arc(150, 550, 100, 100, 90, 90);
        a1.setType(ArcType.OPEN);
        a1.setStroke(Color.BLACK);
        a1.setStrokeWidth(3);
        a1.setFill(null);

        Arc a2 = new Arc(300, 550, 100, 100, 45, 90);
        a2.setType(ArcType.CHORD);
        a2.setStroke(Color.BLACK);
        a2.setStrokeWidth(3);
        a2.setFill(null);

        Arc a3 = new Arc(500, 550, 100, 100, 45, 90);
        a3.setType(ArcType.ROUND);
        a3.setStroke(Color.BLACK);
        a3.setStrokeWidth(3);
        a3.setFill(null);

        groupNodes.addAll(a1, a2, a3);

//        group.setRotate(30);
//        group.setTranslateX(110);
//        group.setTranslateY(110);

        Scene scene = new Scene(group, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
