/**
 * Sample Skeleton for 'LoggedInView.fxml' Controller Class
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import model.Posts;

public class LoggedInViewController {

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
    private TableColumn<Posts, Integer> materialUser; // Value injected by FXMLLoader
    
    // the observable list of textbooks that is used to insert data into the table
    private ObservableList<Posts> postData;
    
    // database manager
    EntityManager manager;
    
    //tracks who's logged in
    int currentUser;

    
    @FXML
    void clickLogout(ActionEvent event) throws IOException {
        //show a message to the user for feedback
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");// line 2
            alert.setHeaderText("Notice:");// line 3
            alert.setContentText("You are now Logged Out.");// line 4
            alert.showAndWait(); // line 5
        //code taken and refurbished from Google doc
        // fxml loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeView.fxml"));

        // load the ui elements
        Parent homeView = loader.load();

        // load the scene
        Scene tableViewScene = new Scene(homeView);

        //access the detailedControlled and call a method
        //may or may not need to actually do this?
        HomeViewController homeControlled = loader.getController();
        homeControlled.initialize();
        
        //This line gets the Stage information
        Scene currentScene = ((Node) event.getSource()).getScene();
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(tableViewScene);
        stage.show();

    }

    @FXML
    void clickTitleSearch(ActionEvent event) {
        // getting the name from input box
        String title = searchText.getText();
        // calling a db read operaiton, readByName
        List<Posts> posts = readByTitleContainingAdvanced(title);
        if (posts == null || posts.isEmpty()) {
            // show an alert to inform user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");// line 2
            alert.setHeaderText("The following error occured:");// line 3
            alert.setContentText("No posts matching that query were found");// line 4
            alert.showAndWait(); // line 5
        } else {
            // setting table data
            setTableData(posts);
        }
    }
    
    public List<Posts> readByTitleContainingAdvanced(String postName) {
        Query query = manager.createNamedQuery("Posts.findByTitleAdvanced");

        // setting query parameter
        query.setParameter("title", postName);

        // execute query
        List<Posts> posts = query.getResultList();

        return posts;
    }
    
        public void setTableData(List<Posts> postList) {

        postData = FXCollections.observableArrayList();

        postList.forEach(p -> {
            postData.add(p);
        });

        postTable.setItems(postData);
        postTable.refresh();
    }
    

    @FXML
    void createPost(ActionEvent event) {
        System.out.println("Not Supported Yet.");
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize(int passedUser) {
        //set the current user to be the passed in user from the log in screen
        currentUser = passedUser;
        System.out.println(passedUser);
        
        // loading data from database
        manager = (EntityManager) Persistence.createEntityManagerFactory("TextbookTraderFXMLPU").createEntityManager();


        //Copied and modified from Google doc
        // set the cell value factories for the TableView Columns
        materialName.setCellValueFactory(new PropertyValueFactory<>("title"));
        materialCondition.setCellValueFactory(new PropertyValueFactory<>("condition"));
        materialType.setCellValueFactory(new PropertyValueFactory<>("type"));
        materialCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        materialUser.setCellValueFactory(new PropertyValueFactory<>("userid"));
        
        postTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        //show all the data in the table by default
        List<Posts> posts = readByTitleContainingAdvanced("");
        setTableData(posts);

    }
}
