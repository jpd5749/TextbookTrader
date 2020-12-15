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
import model.Users;

/**
 *
 * @author Jonas
 */
public class HomeViewController implements Initializable {

    //comment test
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="label"
    private Label label; // Value injected by FXMLLoader

    @FXML // fx:id="searchText"
    private TextField searchText; // Value injected by FXMLLoader

    @FXML // fx:id="postTable"
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
    private TableColumn<Posts, String> materialUser; // Value injected by FXMLLoader

    //taken and modified from the Google doc
    // the observable list of textbooks that is used to insert data into the table
    private ObservableList<Posts> postData;
    // add the proper data to the observable list to be rendered in the table

    //populates the view's table with the posts from the database
    public void setTableData(List<Posts> postList) {

        for (Posts p : postList) {

            Query query = manager.createNamedQuery("Users.findById");
            query.setParameter("id", p.getUserid());
            Users u = (Users) query.getSingleResult();
            p.setUsername(u.getFirstname() + " " + u.getLastname());
            p.setEmail(u.getEmail());
        }

        postData = FXCollections.observableArrayList();

        postList.forEach(p -> {
            postData.add(p);
        });

        postTable.setItems(postData);
        postTable.refresh();
    }

    //searches through the database with a wildcard search based on what's passed in
    public List<Posts> readByTitleContainingAdvanced(String postName) {
        Query query = manager.createNamedQuery("Posts.findByTitleAdvanced");

        // setting query parameter
        query.setParameter("title", postName);

        // execute query
        List<Posts> posts = query.getResultList();

        return posts;
    }

    //called when the "search" button is clicked. calls the previous method, passing in what's in the search textfield
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

    //brings up the LogInView after the "Log In/Sign Up" button is clicked
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

    //Brings up the DetailedModelView, passing in the post that is highlighted from clicking on it in the view
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

    //called when the view is created. mostly just sets up things for the table,
    //along with calling the "readByTitleContainingAdvanced" method with an empty string to populate the table with every post by default
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // loading data from database
        manager = (EntityManager) Persistence.createEntityManagerFactory("TextbookTraderFXMLPU").createEntityManager();

        //Copied and modified from Google doc
        // set the cell value factories for the TableView Columns
        materialName.setCellValueFactory(new PropertyValueFactory<>("title"));
        materialCondition.setCellValueFactory(new PropertyValueFactory<>("condition"));
        materialType.setCellValueFactory(new PropertyValueFactory<>("type"));
        materialCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        materialUser.setCellValueFactory(new PropertyValueFactory<>("username"));

        postTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //show all the data in the table by default
        List<Posts> posts = readByTitleContainingAdvanced("");
        setTableData(posts);
    }

    //no arguments for when it's called by LoggedInView
    public void initialize() {
        // loading data from database
        manager = (EntityManager) Persistence.createEntityManagerFactory("TextbookTraderFXMLPU").createEntityManager();

        //Copied and modified from Google doc
        // set the cell value factories for the TableView Columns
        materialName.setCellValueFactory(new PropertyValueFactory<>("title"));
        materialCondition.setCellValueFactory(new PropertyValueFactory<>("condition"));
        materialType.setCellValueFactory(new PropertyValueFactory<>("type"));
        materialCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        materialUser.setCellValueFactory(new PropertyValueFactory<>("username"));

        postTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //show all the data in the table by default
        List<Posts> posts = readByTitleContainingAdvanced("");
        setTableData(posts);
    }

}
