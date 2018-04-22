package ConnectGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FindaGame extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene2 = new Scene(nextContent(primaryStage), 700, 600);
        scene2.getStylesheets().add(ConnectionGUI.class.getResource("ConnectionGUI.css").toExternalForm());
        

        primaryStage.show();
    }

    private Parent nextContent(Stage primaryStage) {

        GridPane root = new GridPane();

        root.setPadding(new Insets(25,25,25,25));
        Text sceneTitle =  new Text("Find a Match!");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Label username = new Label("Welcome ");
        root.add(username,4,0);

        // ObservableList<Client> clients = FXCollections.observableArrayList();
        // clients.add();

        return root;
    }
}
