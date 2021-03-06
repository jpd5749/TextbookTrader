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
import javafx.scene.control.Alert;
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

    //takes the data from the text fields and uses it to create a new user variable. includes sanity checking
    @FXML
    void clickCreate(ActionEvent event) throws IOException {

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

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("You may not leave any fields empty!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");
            alert.setHeaderText("The following error occured:");
            alert.setContentText("One or more fields have been left empty");
            alert.showAndWait();
        } else {
            // set properties
            user.setId(id);
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
            user.setPassword(password);
        }

        // pass current scene to return
        Scene currentScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) currentScene.getWindow();

        // save this account to database by calling Create operation  
        create(user, stage);
    }

    //takes in the passed in user, adds it to the database, and then opens up the LoggedInView, passing in the ID of the new user
    @FXML
    public void create(Users user, Stage stage) {

        //Check if the user already exists
        Users existingAccount = checkEmail(user.getEmail());

        if (existingAccount == null) {

            // Create operation
            try {
                // begin transaction
                manager.getTransaction().begin();

                // sanity check
                if (user.getEmail() != null && user.getFirstname() != null && user.getLastname() != null && user.getPassword() != null) {

                    // create account
                    manager.persist(user);

                    // end transaction
                    manager.getTransaction().commit();

                    System.out.println(user.toString() + " is created");

                    // fxml loader
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoggedInView.fxml"));
                    // load the ui elements
                    Parent loggedInView = loader.load();

                    // load the scene
                    Scene loggedInViewScene = new Scene(loggedInView);

                    //access the detailedControlled and call a method
                    LoggedInViewController loggedInControlled = loader.getController();

                    loggedInControlled.initialize(user.getId());

                    stage.setScene(loggedInViewScene);
                    stage.show();
                } else {
                    System.out.println("One or more values are blank");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Already exists!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");
            alert.setHeaderText("The following error occured:");
            alert.setContentText("This email is already in use.");
            alert.showAndWait();
        }

    }

    //uses a named query to search for a specific user by email
    public Users checkEmail(String email) {
        try {
            Query query = manager.createNamedQuery("Users.findByEmail");

            // setting query parameter
            query.setParameter("email", email);

            // execute query
            Users user = (Users) query.getSingleResult();
            return user;
        } catch (Exception ex) {
            return null;
        }

    }

    //returns to the previous scene, HomeView
    @FXML
    void exitAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(previousScene);
    }

    // Database manager
    EntityManager manager;

    //is called when the view is first brought up
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

    //takes the passed in scene and sets it to a local variable
    void setPreviousScene(Scene scene) {
        previousScene = scene;
    }
}
