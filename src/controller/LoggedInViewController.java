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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Posts;
import model.Users;

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

    @FXML // fx:id="welcomeText"
    private Text welcomeText; // Value injected by FXMLLoader

    // the observable list of textbooks that is used to insert data into the table
    private ObservableList<Posts> postData;

    // database manager
    EntityManager manager;

    //tracks who's logged in
    int currentUser;

    //simply returns to the HomeView, which lacks the options for when a user is logged in
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

    //same as HomeViewController, called when the "search" button is clicked. calls the readByTitleContainingAdvanced,
    //passing in what's in the search textfield
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
    void clickClassSearch(ActionEvent event) {
        // getting the name from input box
        String className = searchText.getText();
        // calling a db read operaiton, readByName
        List<Posts> posts = readByClassContainingAdvanced(className);
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

    //searches through the database with a wildcard search based on what's passed in
    public List<Posts> readByClassContainingAdvanced(String postName) {
        Query query = manager.createNamedQuery("Posts.findByClassAdvanced");

        // setting query parameter
        query.setParameter("course", postName);

        // execute query
        List<Posts> posts = query.getResultList();

        return posts;
    }

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

    //switched the view to the CreatePostView. passes in the id of the currently logged in user
    @FXML
    void createPost(ActionEvent event) throws IOException {
        // fxml loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreatePostView.fxml"));

        // load the ui elements
        Parent createPostView = loader.load();

        // load the scene
        Scene loginViewScene = new Scene(createPostView);

        //access the detailedControlled and call a method
        CreatePostController createPostController = loader.getController();

        // pass current scene to return
        Scene currentScene = ((Node) event.getSource()).getScene();
        createPostController.setPreviousScene(currentScene);

        createPostController.initialize(currentUser);

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

    //called when the view is brought up, is passed in the id of the logged in user
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
        materialUser.setCellValueFactory(new PropertyValueFactory<>("username"));

        postTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //show all the data in the table by default
        List<Posts> posts = readByTitleContainingAdvanced("");
        setTableData(posts);

        //change the welcome text to welcome back the selected user
        //first, run a query to return the logged in user by searching their ID
        Query query = manager.createNamedQuery("Users.findById");
        query.setParameter("id", currentUser);
        Users u = (Users) query.getSingleResult();

        //next, change the welcome text to include their name
        welcomeText.setText("Welcome back, " + u.getFirstname());

    }
}
