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
    private PasswordField passwordField;

    @FXML
    private Button signinButton;

    Scene previousScene;

    //sets the passed in scene to a local variable
    void setPreviousScene(Scene scene) {
        previousScene = scene;
    }

    //opens the SignUpFrameView
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

    //takes the data from the text fields, checks the database to see if the username and password is correct, and then opens the LoggedInView,
    //passing in the id of the currently logged in user
    @FXML
    void clickSignin(ActionEvent event) throws IOException {
        String enteredEmail = emailField.getText();
        String enteredPassword = passwordField.getText();

        Users user = findByEmail(enteredEmail);

        if (user != null) {

            if (user.getPassword().equals(enteredPassword)) {
                System.out.println("Welcome back, " + user.getFirstname() + "!");

                //do stuff to bring up LoggedInView
                //code taken and refurbished from Google doc
                // fxml loader
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoggedInView.fxml"));

                // load the ui elements
                Parent loggedInView = loader.load();

                // load the scene
                Scene loggedInViewScene = new Scene(loggedInView);

                //access the detailedControlled and call a method
                LoggedInViewController loggedInControlled = loader.getController();

                loggedInControlled.initialize(user.getId());

                // pass current scene to return
                Scene currentScene = ((Node) event.getSource()).getScene();
                Stage stage = (Stage) currentScene.getWindow();

                stage.setScene(loggedInViewScene);
                stage.show();
            } else {
                System.out.println("Wrong password!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog Box");// line 2
                alert.setHeaderText("The following error occured:");// line 3
                alert.setContentText("The Password is Incorrect.");// line 4
                alert.showAndWait(); // line 5
            }

        } else {
            System.out.println("Wrong email!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");// line 2
            alert.setHeaderText("The following error occured:");// line 3
            alert.setContentText("The Email Address is Incorrect.");// line 4
            alert.showAndWait();
        }

    }

    EntityManager manager;

    //uses a named query to find a specific user by their email
    public Users findByEmail(String email) {
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

    //when the back button is clicked, returns to the HomeView
    @FXML
    void exitAction(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(previousScene);
    }

    //called when the view is first brought up
    @FXML
    void initialize() {
        manager = (EntityManager) Persistence.createEntityManagerFactory("TextbookTraderFXMLPU").createEntityManager();

        assert signupButton != null : "fx:id=\"signupButton\" was not injected: check your FXML file 'LoginFrameView.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'LoginFrameView.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'LoginFrameView.fxml'.";
        assert signinButton != null : "fx:id=\"signinButton\" was not injected: check your FXML file 'LoginFrameView.fxml'.";

    }
}
