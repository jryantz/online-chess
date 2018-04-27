package gui.connect;

import gui.chess.GameGUI;
import gui.chess.UserColor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public static Stage primaryStage;

    Client c;

    private String inputtedUsername;

    ObservableList<String> connectedUsers;
    ObservableList<String> chosenUser;

    public static Button playChessButton;
    public static Label wantToPlayChessLabel;
    public static Button userDecisionYesSubmitButton;
    public static Button userDecisionNoSubmitButton;

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

        this.primaryStage = primaryStage;

    } // end start.

    @Override
    public void stop() {

        System.out.println("Closing the application.");

        if (c != null) {
            c.sendQuitToServer();
        }

    } // end stop.

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

        // Set the title of the given scene.
        Text sceneTitle = new Text("Welcome to Chess! Please input your username.");
        sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        // Add the label for the first text field.
        Label usernameLabel = new Label("Username: ");
        root.add(usernameLabel, 4, 0);

        // Add the username text field.
        TextField usernameTextField = new TextField();
        root.add(usernameTextField, 4, 2);

        // Add the username submit button.
        Button submitButton = new Button();
        submitButton.setText("Submit");
        root.add(submitButton, 4, 4);

        // Add the label for the list of online users.
        Label justOnlineUsers = new Label("Online Users: ");
        root.add(justOnlineUsers, 4, 6);

        // Will contain connected users, currently empty.
        connectedUsers = FXCollections.observableArrayList();

        // List of all of the online users.
        ListView<String> listOfOnlineUsers = new ListView(connectedUsers);
        listOfOnlineUsers.setOrientation(Orientation.VERTICAL);
        listOfOnlineUsers.setPrefSize(200, 200);

        VBox selectUsers = new VBox();

        selectUsers.setAlignment(Pos.BOTTOM_CENTER);
        selectUsers.getChildren().addAll(listOfOnlineUsers);
        root.add(selectUsers, 4, 8);

        // Add the label for the selected user.
        Label playWithUser = new Label("Chosen Chess Opponent: ");
        root.add(playWithUser, 4, 10);

        VBox playWithUserBox = new VBox();
        playWithUserBox.setAlignment(Pos.BOTTOM_CENTER);

        // Observable list that will contain all connected users.
        chosenUser = FXCollections.observableArrayList();

        ListView<String> playWithUserList = new ListView<>(chosenUser);
        playWithUserList.setPrefSize(27, 27);

        notifyListChanges = new Label();
        notifyListChanges.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        root.add(notifyListChanges, 4, 12);

        playWithUserBox.getChildren().add(playWithUserList);
        root.add(playWithUserBox, 4, 12);

        // Add button to send a request to another user.
        playChessButton = new Button();
        playChessButton.setAlignment(Pos.BOTTOM_CENTER);
        playChessButton.setText("Play Chess");
        playChessButton.setDisable(true);
        root.add(playChessButton, 4, 14);

        // Add the label for showing a request to play a game.
        wantToPlayChessLabel = new Label();
        wantToPlayChessLabel.setText("Waiting for request from other client.");
        root.add(wantToPlayChessLabel, 4, 16);

        // Add the button to say yes to a request
        userDecisionYesSubmitButton = new Button();
        userDecisionYesSubmitButton.setText("Yes");
        root.add(userDecisionYesSubmitButton, 4, 18);
        userDecisionYesSubmitButton.setDisable(true);

        // Add the button to say no to a request.
        userDecisionNoSubmitButton = new Button();
        userDecisionNoSubmitButton.setText("No");
        root.add(userDecisionNoSubmitButton, 4, 20);
        userDecisionNoSubmitButton.setDisable(true);

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
                    playChessButton.setDisable(false); // Enables the play chess button.
                }

                listOfOnlineUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, incomingConnectedUsers) -> {
                    System.out.println("ListView selection changed from oldValue = " + oldValue + " to newValue = " + incomingConnectedUsers);
                    chosenUser.removeAll(chosenUser);
                    chosenUser.add(incomingConnectedUsers);
                });

            }

        });

        playChessButton.setOnAction(event -> {

            c.sendGameRequestToServer(chosenUser.get(0));

        });

        userDecisionYesSubmitButton.setOnAction(event -> {

            c.sendAcceptOrRejectToServer(true, Main.getRequestingUser());

            Main.setRequestingUser(null);

            playChessButton.setDisable(true);
            wantToPlayChessLabel.setText("Joining chess game...");
            userDecisionYesSubmitButton.setDisable(true);
            userDecisionNoSubmitButton.setDisable(true);

            new GameGUI().start(new Stage());
            primaryStage.hide();

        });

        userDecisionNoSubmitButton.setOnAction(event -> {

            c.sendAcceptOrRejectToServer(false, Main.getRequestingUser());

            Main.setRequestingUser(null);

            playChessButton.setDisable(false);
            wantToPlayChessLabel.setText("Waiting for request from other client.");
            userDecisionYesSubmitButton.setDisable(true);
            userDecisionNoSubmitButton.setDisable(true);

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
