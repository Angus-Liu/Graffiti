package controls;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * TableView Demo
 */
public class TableViewDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TableView<Movie> table = new TableView<>();
        TableColumn<Movie, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Movie, Integer> colYear = new TableColumn<>("Year");
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        TableColumn<Movie, Integer> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        table.getColumns().addAll(colName, colYear, colPrice);
        table.getItems().addAll(
                new Movie("1", 1, 1),
                new Movie("2", 1, 1)
        );

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Pane pane = new BorderPane(table);

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

