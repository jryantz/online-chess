package ConnectGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import node.Client;

public class ConnectionGUI extends Application {
    private Group LoginGroup = new Group(); // GUI TILE.
    private Group BackgroundGroup = new Group(); // GUI PIECES SIT ON TOP OF TILE.
    public String userName;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent(),400,400);
        primaryStage.setTitle("FSU Chess Client");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(ConnectionGUI.class.getResource("ConnectionGUI.css").toExternalForm());
        primaryStage.show();


    }

    private Parent createContent() {
        GridPane root = new GridPane();

        root.setPadding(new Insets(25,25,25,25));
        Text sceneTitle =  new Text("Welcome to Chess! Please input your username");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Label userName = new Label("User Name: ");
        root.add(userName,4,0);
        TextField userNameTextField = new TextField();
        root.add(userNameTextField,4,2);

        Button btn = new Button();
        btn.setText("Submit");
        root.add(btn, 4,4);

        btn.setOnAction(e -> {

            String s=(userNameTextField.getText());
            setUsername(s);
        });

        return root;

    }

    public void setUsername(String text){
        userName=text;
    }
    
    public String getUserName(){

        return userName;
    }
}
