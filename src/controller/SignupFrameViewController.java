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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Users;

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
    private PasswordField passwordField;

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
    void clickCreate(ActionEvent event) {

        // read input from text fields
        int id = 0;
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        
        // call named query to determine the highest user id in the table
        Query query = manager.createNamedQuery("Users.findNewestUser");
        // set id to be the highest user id + 1
        id = (int) query.getSingleResult() + 1;

        // create a user instance
        Users user = new Users();

        // set properties
        user.setId(id);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setPassword(password);

        // save this account to database by calling Create operation        
        create(user);
    }

    @FXML
    public void create(Users user) {

        // Create operation
        try {
            // begin transaction
            manager.getTransaction().begin();

            // sanity check
            if (user.getEmail()!= null && user.getFirstname()!= null && user.getLastname()!= null && user.getPassword()!= null) {

                // create account
                manager.persist(user);

                // end transaction
                manager.getTransaction().commit();

                System.out.println(user.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        @FXML
    void exitAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(previousScene);
    }

    // Database manager
    EntityManager manager;

    @FXML
    void initialize() {

        // loading data from database
        manager = (EntityManager) Persistence.createEntityManagerFactory("TextbookTraderFXMLPU").createEntityManager();

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
