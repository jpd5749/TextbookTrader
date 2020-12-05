package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupFrameViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink signInButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button createAccountButton;

    Scene previousScene;

    @FXML
    void clickSignin(ActionEvent event) throws IOException {
        // fxml loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginFrameView.fxml"));

        // load the ui elements
        Parent loginFrameView = loader.load();

        // load the scene
        Scene loginViewScene = new Scene(loginFrameView);

        //access the detailedControlled and call a method
        LoginFrameViewController loginController = loader.getController();

        // pass current scene to return
        Scene currentScene = ((Node) event.getSource()).getScene();
        //loginController.setPreviousScene(currentScene);

        //This line gets the Stage information
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(loginViewScene);
        stage.show();
    }

    @FXML
    void initialize() {
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'SignupFrameView.fxml'.";
        assert firstNameField != null : "fx:id=\"firstNameField\" was not injected: check your FXML file 'SignupFrameView.fxml'.";
        assert lastNameField != null : "fx:id=\"lastNameField\" was not injected: check your FXML file 'SignupFrameView.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'SignupFrameView.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'SignupFrameView.fxml'.";
        assert createAccountButton != null : "fx:id=\"createAccountButton\" was not injected: check your FXML file 'SignupFrameView.fxml'.";

    }

    void setPreviousScene(Scene scene) {
        previousScene = scene;
    }
}
