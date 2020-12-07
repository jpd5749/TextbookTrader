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
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Accounts;

public class LoginFrameViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink signupButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signinButton;

    Scene previousScene;

    void setPreviousScene(Scene scene) {
        previousScene = scene;
    }

    @FXML
    void clickSignup(ActionEvent event) throws IOException {
        // fxml loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignupFrameView.fxml"));

        // load the ui elements
        Parent signupFrameView = loader.load();

        // load the scene
        Scene signupViewScene = new Scene(signupFrameView);

        //access the detailedControlled and call a method
        SignupFrameViewController signupController = loader.getController();

        // pass current scene to return
        Scene currentScene = ((Node) event.getSource()).getScene();
        signupController.setPreviousScene(currentScene);

        //This line gets the Stage information
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(signupViewScene);
        stage.show();
    }

    @FXML
    void clickSignin(ActionEvent event) {
        String enteredEmail = emailField.getText();
        String enteredPassword = passwordField.getText();

        Accounts user = findByEmail(enteredEmail);
        
        System.out.println(user.getFirstname());

    }
    
    EntityManager manager;

    public Accounts findByEmail(String email) {
        Query query = manager.createNamedQuery("Accounts.findByEmail");

        // setting query parameter
        query.setParameter("email", email);

        // execute query
        Accounts user = (Accounts) query.getSingleResult();
        return user;
    }

    @FXML
    void exitAction(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(previousScene);
    }

    @FXML
    void initialize() {
        manager = (EntityManager) Persistence.createEntityManagerFactory("JonasJasonFXMLPU").createEntityManager();
        
        assert signupButton != null : "fx:id=\"signupButton\" was not injected: check your FXML file 'LoginFrameView.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'LoginFrameView.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'LoginFrameView.fxml'.";
        assert signinButton != null : "fx:id=\"signinButton\" was not injected: check your FXML file 'LoginFrameView.fxml'.";

    }
}
