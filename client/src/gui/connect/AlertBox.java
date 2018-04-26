package gui.connect;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AlertBox extends Application {

    public AlertBox(){

    } // end AlertBox.

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createContent(), 200, 200);

        primaryStage.setTitle("Alert");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(ConnectionGUI.class.getResource("ConnectionGUI.css").toExternalForm());

        primaryStage.show();

    } // end start.

    private Parent createContent() {

        GridPane root = new GridPane();

        Button accept = new Button ("Accept Request");
        Button reject = new Button ("Reject Request");

        accept.setOnAction(event -> {
            System.out.println("Accepted Request.");
        });

        reject.setOnAction(event -> {
            System.out.println("Reject Request.");
        });

        return root;

    } // end createContent.

} // end class AlertBox.
