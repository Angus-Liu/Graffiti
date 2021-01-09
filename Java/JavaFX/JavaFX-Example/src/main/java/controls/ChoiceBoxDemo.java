package controls;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * ChoiceBox Demo
 */
public class ChoiceBoxDemo extends Application {

    static class Person {
        private final String name;
        private final Integer age;

        Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + " " + age;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ChoiceBox<Person> choiceBox = new ChoiceBox<>();
        ObservableList<Person> list = choiceBox.getItems();
        for (int i = 0; i < 10; i++) {
            list.add(new Person("NO." + i, 20 + i));
        }
        choiceBox.setOnAction(event -> {
            Person selected = choiceBox.getValue();
            System.out.println("You selected: " + selected);
        });

        BorderPane pane = new BorderPane(choiceBox);
        pane.setPadding(new Insets(20));

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
