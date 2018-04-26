package gui.connect;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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

/**
 * This class is used to for the client to gui.connect to the server. The client class is also created in here.
 */
public class ConnectionGUI extends Application {

    Client c;

    private String inputtedUsername;

    ObservableList<String> connectedUsers;
    ObservableList<String> chosenUser;

    private Labeled notifyListChanges;
    private String newNames = "";
    private String oldNames = "";

    /**
     * Start method for GUI. The scene calls the method createContent, which sends the username of the
     * client to the server
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createContent(), 500, 600);

        primaryStage.setTitle("FSU Chess Client");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(ConnectionGUI.class.getResource("ConnectionGUI.css").toExternalForm());

        primaryStage.show();

    } // end start.

    @Override
    public void stop() {

        System.out.println("Closing the application.");

        if (c != null) {
            c.sendQuitToServer();
        }

    } // end stop.

    public void exitApplication(ActionEvent event) {

        Platform.exit();

    } // end exitApplication.

    /**
     * This method creates the textfield, text, etc. portions for the GUI. The method
     * checks if the textfield is empty and if not, will start connecting as a client to
     * the server!!
     *
     * @return
     */
    private Parent createContent() {

        GridPane root = new GridPane();

        root.setPadding(new Insets(30, 30, 30, 30));
        Text sceneTitle = new Text("Welcome to Chess! Please input your username.");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        Label usernameLabel = new Label("Username: ");
        root.add(usernameLabel, 4, 0);

        TextField usernameTextField = new TextField();
        root.add(usernameTextField, 4, 2);

        notifyListChanges = new Label();
        notifyListChanges.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.add(notifyListChanges, 4, 9);

        // GUI STUFF FOR 'PLAYING CHESS BUTTON' AND 'SELECTING OPPONENT'.
        Label playWithUser = new Label("Chosen Chess Opponent: ");

        chosenUser = FXCollections.observableArrayList(); // Will contain connected users.

        ListView<String> playWithUserBox1 = new ListView<>(chosenUser);
        playWithUserBox1.setPrefSize(25, 25);

        VBox playwithUserBox = new VBox();
        playwithUserBox.setAlignment(Pos.BOTTOM_CENTER);
        root.add(playWithUser, 4, 8);

        playwithUserBox.getChildren().add(playWithUserBox1);
        root.add(playwithUserBox, 4, 10);

        Button playChess = new Button();
        playChess.setAlignment(Pos.BOTTOM_CENTER);
        playChess.setText("Play Chess");
        playChess.setDisable(true);
        root.add(playChess, 4, 14);

        // GUI STUFF FOR THE TABLE OF CONNECTED USERS, ONLINE USERS LABEL, USERNAME LABEL, AND CONNECT BUTTON.
        Label justOnlineUsers = new Label("Online Users ");
        root.add(justOnlineUsers, 4, 5);

        // CREATES A BLANK LIST.
        connectedUsers = FXCollections.observableArrayList(); // Will contain connected users.

        VBox selectUsers = new VBox();

        ListView<String> listOfOnlineUsers = new ListView(connectedUsers);
        listOfOnlineUsers.setOrientation(Orientation.VERTICAL);
        listOfOnlineUsers.setPrefSize(200, 200);

        selectUsers.setAlignment(Pos.BOTTOM_CENTER);
        selectUsers.getChildren().addAll(listOfOnlineUsers);
        root.add(selectUsers, 4, 6);

        Button submitButton = new Button();
        submitButton.setText("Submit");
        root.add(submitButton, 4, 4);

        // Put popup here for checking if user can play.
        // c.sendAcceptOrRejectToServer

        root.setAlignment(Pos.TOP_CENTER);

        // When the button is pressed, the textfield will be checked if empty and then gui.connect to the server if not.
        submitButton.setOnAction(e -> {

            inputtedUsername = (usernameTextField.getText());

            if (usernameTextField.getText() != null && !usernameTextField.getText().trim().isEmpty()) {

                setUsername(usernameTextField.getText());

                c = new Client(inputtedUsername, connectedUsers);

                while (Main.getNames() == null) {

                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                }

                oldNames = Main.getNames();

                if (newNames != oldNames) {
                    newNames = oldNames;
                    usernameLabel.setText("Welcome to Chess: " + inputtedUsername + "!"); // Sends greeting.
                    submitButton.setDisable(true); // Disables submit button.
                    playChess.setDisable(false); // Enables the play chess button.
                }

                listOfOnlineUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, incomingConnectedUsers) -> {
                    System.out.println("ListView selection changed from oldValue = " + oldValue + " to newValue = " + incomingConnectedUsers);
                    chosenUser.removeAll(chosenUser);
                    chosenUser.add(incomingConnectedUsers);
                });

            }

        });

        playChess.setOnAction(event -> {

            c.sendGameRequestToServer(chosenUser.get(0));

        });

        return root;

    } // end createContent.


    public void setUsername(String text) {

        inputtedUsername = text;

    } // end setUsername.

    public String getUsername(String s) {

        return inputtedUsername;

    } // end getUsername.

} // end class ConnectionGUI.
