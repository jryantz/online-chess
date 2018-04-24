package ConnectGUI;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;
import node.Client;

import java.util.List;

/**
 * This class is used to for the client to connect to the server. The client class is also created in here.
 */
public class ConnectionGUI extends Application {
    public String userName;
    Client client;
    private String s;
    ObservableList<String> connectedUseres;
    ObservableList<String> chosenUser;
    private Labeled notifyListChanges;
    private String newNames ="";
    private String oldNames ="";

    /**
     * Start method for GUI. The scene calls the method createContent, which sends the username of the
     * client to the server
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent(primaryStage, userName),500,600);

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



        root.setPadding(new Insets(30,30,30,30));
        Text sceneTitle =  new Text("Welcome to Chess! Please input your username");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Label username = new Label("User Name: ");
        root.add(username,4,0);
        TextField userNameTextField = new TextField();
        root.add(userNameTextField,4,2);

        notifyListChanges = new Label();
        notifyListChanges.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.add(notifyListChanges,4,9);
        //------------------------GUI STUFF FOR PLAYING CHESS BUTTON AND SELECTING OPPONENNT
        Label playWithUser = new Label("Chosen Chess Opponent ");
        chosenUser= FXCollections.observableArrayList(); //will contain connected users
        ListView<String> playWithUserBox1 =new ListView<>(chosenUser);
        playWithUserBox1.setPrefSize(25,25);
        VBox playwithUserBox = new VBox();
        playwithUserBox.setAlignment(Pos.BOTTOM_CENTER);
        root.add(playWithUser, 4,8);
        playwithUserBox.getChildren().add(playWithUserBox1);
        root.add(playwithUserBox, 4,10);

        Button playChess = new Button();
        playChess.setAlignment(Pos.BOTTOM_CENTER);
        playChess.setText("Play Chess");
        root.add(playChess, 4,14);




        //--------------GUI STUFF FOR THE TABLE OF CONNECTED USERS, ONLINE USERS LABEL, USERNAME LABEL, & Connect button
        Label justOnlineUsers = new Label("Online Users ");
        root.add(justOnlineUsers,4,5);

        //just creates a blank list
        connectedUseres= FXCollections.observableArrayList(); //will contain connected users

        VBox selectUserse = new VBox();
        ListView<String> listOfOnlineUsers = new ListView(connectedUseres);
        listOfOnlineUsers.setOrientation(Orientation.VERTICAL);
        listOfOnlineUsers.setPrefSize(200,200);
        selectUserse.setAlignment(Pos.BOTTOM_CENTER);
        selectUserse.getChildren().addAll(listOfOnlineUsers);

        root.add(selectUserse,4,6);


        Button btn = new Button();
        btn.setText("Submit");
        root.add(btn, 4,4);

        root.setAlignment(Pos.TOP_CENTER);

        btn.setOnAction(e -> { //when the button is pressed, the textfield will be checked if empty and
            //then connect to the server if not.

            s=(userNameTextField.getText());
            if(userNameTextField.getText() != null && !userNameTextField.getText().trim().isEmpty()){
                setUsername(userNameTextField.getText());

                client = new Client(s, connectedUseres); //---client class! Connecting to the server

                while (Main.getNames() == null) {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    if(Main.getNames()!=null){
                        oldNames = Main.getNames();
                        if (newNames != oldNames) {
                            newNames = oldNames;
                            username.setText("Welcome to Chess: " + s + "!"); //disables button and sends greeting
                            //when user hits "submit"
                            btn.setDisable(true);
                    }


                        listOfOnlineUsers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                            @Override
                            public void changed(ObservableValue<? extends String> observable, String oldValue, String connectedUseres) {
                                System.out.println("ListView selection changed from oldValue = "
                                        + oldValue + " to newValue = " + connectedUseres);
                                chosenUser.removeAll(chosenUser);
                                chosenUser.add(connectedUseres);
                            }
                        });

                    }


               }



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
