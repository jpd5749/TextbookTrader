/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Posts;

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
    void createPost(ActionEvent event) {
        
    }
    
}
