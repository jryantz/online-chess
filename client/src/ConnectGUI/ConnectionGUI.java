package ConnectGUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * This class is used to for the client to connect to the server. The client class is also created in here.
 */
public class ConnectionGUI extends Application {
    private Group LoginGroup = new Group(); // GUI TILE.
    private Group BackgroundGroup = new Group(); // GUI PIECES SIT ON TOP OF TILE.
    public String userName;
    public String connectedClients;
    Client client;

    /**
     * Start method for GUI. The scene calls the method createContent, which sends the username of the
     * client to the server
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent(primaryStage, userName),400,400);
        primaryStage.setTitle("FSU Chess Client");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(ConnectionGUI.class.getResource("ConnectionGUI.css").toExternalForm());

        primaryStage.show();


    }


    /**
     * This method creates the textfield, text, etc. portions for the GUI. The method
     * checks if the textfield is empty and if not, will start connecting as a client to
     * the server!!
     * @param primaryStage
     * @param userName
     * @return
     */
    private Parent createContent(Stage primaryStage, String userName) {
        GridPane root = new GridPane();

        root.setPadding(new Insets(25,25,25,25));
        Text sceneTitle =  new Text("Welcome to Chess! Please input your username");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Label username = new Label("User Name: ");
        root.add(username,4,0);
        TextField userNameTextField = new TextField();
        root.add(userNameTextField,4,2);

        Button btn = new Button();
        btn.setText("Submit");
        root.add(btn, 4,4);

        btn.setOnAction(e -> { //when the button is pressed, the textfield will be checked if empty and
            //then connect to the server if not.

            String s=(userNameTextField.getText());
            if(userNameTextField.getText() != null && !userNameTextField.getText().trim().isEmpty()){
                setUsername(userNameTextField.getText());
                client = new Client(s); //---client class! Connecting to the server
            }
        });

        return root;
    }

    public void setUsername(String text){
        userName=text;
    }
    
    public String getUserName(String s){

        return userName;
    }
}
