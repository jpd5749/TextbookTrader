/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.Posts;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jonas
 */
public class FXMLDocumentController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="label"
    private Label label; // Value injected by FXMLLoader

    @FXML // fx:id="searchText"
    private TextField searchText; // Value injected by FXMLLoader

    @FXML // fx:id="textbookTable"
    private TableView<Posts> postTable; // Value injected by FXMLLoader

    @FXML // fx:id="materialName"
    private TableColumn<Posts, String> materialName; // Value injected by FXMLLoader

    @FXML // fx:id="materialCondition"
    private TableColumn<Posts, String> materialCondition; // Value injected by FXMLLoader

    @FXML // fx:id="materialType"
    private TableColumn<Posts, String> materialType; // Value injected by FXMLLoader

    @FXML // fx:id="materialCourse"
    private TableColumn<Posts, String> materialCourse; // Value injected by FXMLLoader

    @FXML // fx:id="materialUser"
    private TableColumn<Posts, Integer> materialUser; // Value injected by FXMLLoader

    //taken and modified from the Google doc
    // the observable list of textbooks that is used to insert data into the table
    private ObservableList<Posts> postData;
    // add the proper data to the observable list to be rendered in the table

    public void setTableData(List<Posts> postList) {
        // initialize the textbookData variable
        postData = FXCollections.observableArrayList();
        // add the textbook objects to an observable list object for use with the GUI table
        postList.forEach(t -> {
            postData.add(t);
        });
        // set the the table items to the data in textbookData; refresh the table
        postTable.setItems(postData);
        postTable.refresh();
    }

    public List<Posts> readByTitleContainingAdvanced(String postName) {
        Query query = manager.createNamedQuery("Posts.findByTitleAdvanced");

        // setting query parameter
        query.setParameter("title", postName);

        // execute query
        List<Posts> posts = query.getResultList();

        return posts;
    }
    
    private List<Posts> readByUserContainingAdvanced(String user) {
        Query query = manager.createNamedQuery("Posts.findByUseridAdvanced");

        // setting query parameter
        query.setParameter("userid", user);

        // execute query
        List<Posts> posts = query.getResultList();

        return posts;
    }

    @FXML
    void clickTitleSearch(ActionEvent event) {
        // getting the name from input box
        String title = searchText.getText();
        // calling a db read operaiton, readByName
        List<Posts> posts = readByTitleContainingAdvanced(title);
        if (posts == null || posts.isEmpty()) {
            // show an alert to inform user
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");// line 2
            alert.setHeaderText("The following error occured:");// line 3
            alert.setContentText("No posts matching that query were found");// line 4
            alert.showAndWait(); // line 5
        } else {
            // setting table data
            setTableData(posts);
        }
    }

    @FXML
    void clickUserSearch(ActionEvent event) {
        // getting the name from input box
        String user = searchText.getText();
        // calling a db read operaiton, readByName
        List<Posts> posts = readByUserContainingAdvanced(user);
        if (posts == null || posts.isEmpty()) {
            // show an alert to inform user
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");// line 2
            alert.setHeaderText("The following error occured:");// line 3
            alert.setContentText("No posts matching that query were found");// line 4
            alert.showAndWait(); // line 5
        } else {
            // setting table data
            setTableData(posts);
        }
    }

    @FXML
    void clickLogin(ActionEvent event) throws IOException {

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
        loginController.setPreviousScene(currentScene);

        //This line gets the Stage information
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(loginViewScene);
        stage.show();
    }

    //Show details
    @FXML
    void showDetailsInPlace(ActionEvent event) throws IOException {
        //code taken and refurbished from Google doc
        // pass currently selected model
        Posts selectedPost = postTable.getSelectionModel().getSelectedItem();

        // fxml loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailedModelView.fxml"));

        // load the ui elements
        Parent detailedModelView = loader.load();

        // load the scene
        Scene tableViewScene = new Scene(detailedModelView);

        //access the detailedControlled and call a method
        DetailedModelViewController detailedControlled = loader.getController();

        detailedControlled.initData(selectedPost);

        // pass current scene to return
        Scene currentScene = ((Node) event.getSource()).getScene();
        detailedControlled.setPreviousScene(currentScene);

        //This line gets the Stage information
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(tableViewScene);
        stage.show();
    }

    // Database manager (code obtained from Google Doc)
    EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // loading data from database
        manager = (EntityManager) Persistence.createEntityManagerFactory("TextbookTraderFXMLPU").createEntityManager();


        //Copied and modified from Google doc
        // set the cell value factories for the TableView Columns
        materialName.setCellValueFactory(new PropertyValueFactory<>("materialName"));
        materialCondition.setCellValueFactory(new PropertyValueFactory<>("materialCondition"));
        materialType.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        materialCourse.setCellValueFactory(new PropertyValueFactory<>("materialCourse"));
        materialUser.setCellValueFactory(new PropertyValueFactory<>("materialUser"));
        
        postTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
