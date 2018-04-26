package gui.connect;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AlertBox extends Application {

    public AlertBox(){

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent(), 200, 200);

        primaryStage.setTitle("Alert");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(ConnectionGUI.class.getResource("ConnectionGUI.css").toExternalForm());

        primaryStage.show();

    }

    private Parent createContent() {

        GridPane root = new GridPane();

        Button reject = new Button ("Reject Request");
        Button accept = new Button ("Accept Request");

        accept.setOnAction(event -> {

        });

        return root;

    }
}
