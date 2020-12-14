/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Posts;
import model.Users;

/**
 *
 * @author katie
 */
public class CreatePostController {

    @FXML
    private TextField materialName;
    private TextField postTitle;
    
    @FXML
    private TextField materialType;

    @FXML
    private TextField postCourse;

    @FXML
    private TextField postCondition;

    Scene previousScene;

    @FXML
    void back(ActionEvent event) {
        //get current stage from event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //get current stage from back button as long as peviousScene isn't null      
        if (previousScene != null) {
            //call previousScene
            stage.setScene(previousScene);
        }
    }
    
    public void setPreviousScene(Scene scene) {
        previousScene = scene;

    }
    
    @FXML
    void createPost(ActionEvent event) throws IOException {

        // read input from text fields
        int id;
        String name = materialName.getText();
        String type = materialType.getText();
        String course = postCourse.getText();
        String condition = postCondition.getText();

        // call named query to determine the highest post id in the table
        Query query = manager.createNamedQuery("Posts.findNewestPost");
        // set id to be the highest post id + 1
        id = (int) query.getSingleResult() + 1;

        // create a post instance
        Posts post = new Posts();

        if (name.isEmpty() || type.isEmpty() || course.isEmpty() || condition.isEmpty()) {
            System.out.println("You may not leave any fields empty!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");
            alert.setHeaderText("The following error occured:");
            alert.setContentText("One or more fields have been left empty");
            alert.showAndWait();
        } else {
            // set properties
            post.setId(id);
            post.setUserid(currentUser);
            post.setTitle(name);
            post.setType(type);
            post.setCourse(course);
            post.setCondition(condition);
        }

        // pass current scene to return
        Scene currentScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) currentScene.getWindow();

        // save this account to database by calling Create operation  
        create(post, stage);
    }

    @FXML
    public void create(Posts post, Stage stage) {

        //Check if the post already exists
        Posts existingAccount = checkPost(post.getTitle());

        if (existingAccount == null) {

            // Create operation
            try {
                

                // sanity check
                if (post.getTitle() != null && post.getType() != null && post.getCourse() != null && post.getCondition() != null) {

                    // begin transaction
                     manager.getTransaction().begin();

                    // create post
                    manager.persist(post);

                    // end transaction
                    manager.getTransaction().commit();

                    System.out.println(post.toString() + " is created");

                    // fxml loader
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoggedInView.fxml"));
                    // load the ui elements
                    Parent loggedInView = loader.load();

                    // load the scene
                    Scene loggedInViewScene = new Scene(loggedInView);

                    //access the detailedControlled and call a method
                    LoggedInViewController loggedInControlled = loader.getController();

                    loggedInControlled.initialize(post.getUserid());

                    stage.setScene(loggedInViewScene);
                    stage.show();
                } else {
                    System.out.println("One or more values are blank");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("Error happening here!");
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
    
    public Posts checkPost(String title) {
        try {
            Query query = manager.createNamedQuery("Posts.findByTitle");

            // setting query parameter
            query.setParameter("TITLE", title);

            // execute query
            Posts post = (Posts) query.getSingleResult();
            return post;
        } catch (Exception ex) {
            return null;
        }

    }
    
    /*
    @FXML
    void createPost(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoggedInView.fxml"));

                
                Parent loggedInView = loader.load();

                
                Scene loggedInViewScene = new Scene(loggedInView);

                LoggedInViewController loggedInControlled = loader.getController();

                loggedInControlled.initialize(currentUser);


                Scene currentScene = ((Node) event.getSource()).getScene();
                Stage stage = (Stage) currentScene.getWindow();

                stage.setScene(loggedInViewScene);
                stage.show();
        
    }
    */
    int currentUser;
    EntityManager manager;
    
    
    //initialize
    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize(int passedUser) {
        //set the current user to be the passed in user from the log in screen
        currentUser = passedUser;
        System.out.println(passedUser);

        // loading data from database
        manager = (EntityManager) Persistence.createEntityManagerFactory("TextbookTraderFXMLPU").createEntityManager();

        

    }
}
