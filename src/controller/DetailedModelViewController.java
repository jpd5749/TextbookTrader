package controller;

import static java.lang.String.valueOf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.Query;
import model.Posts;
import model.Users;

public class DetailedModelViewController {

    @FXML
    private Button backButton;

    @FXML // fx:id="bookTitle"
    private Text postTitle; // Value injected by FXMLLoader

    @FXML // fx:id="postCondition"
    private Text postCondition; // Value injected by FXMLLoader

    @FXML // fx:id="postMaterial"
    private Text postMaterial; // Value injected by FXMLLoader

    @FXML // fx:id="postCourse"
    private Text postCourse; // Value injected by FXMLLoader

    @FXML // fx:id="postUser"
    private Text postUser; // Value injected by FXMLLoader

    @FXML
    private ImageView bookCoverImage;

    //Taken and reformatted from Google doc
    // going back to previous scene    
    @FXML
    void clickBack(ActionEvent event) {
        // option 1: get current stage -- from event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //  option 2: get current stage -- from backbutton        
        // Stage stage = (Stage)backButton.getScene().getWindow();
        if (previousScene != null) {
            stage.setScene(previousScene);
        }

    }

    Posts selectedModel;
    Scene previousScene;

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);

    }

    public void initData(Posts postModel) {

        selectedModel = postModel;
        postTitle.setText(postModel.getTitle());
        postCondition.setText(postModel.getCondition());
        postMaterial.setText(postModel.getType());
        postCourse.setText(postModel.getCourse());
        postUser.setText(postModel.getUsername());

        try {
            // path points to /resource/images/
            String imagename = "/resource/images/" + postModel.getId().toString() + ".jpg";
            Image cover = new Image(getClass().getResourceAsStream(imagename));
            bookCoverImage.setImage(cover);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL url, ResourceBundle rb) {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert postTitle != null : "fx:id=\"bookTitle\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert postCondition != null : "fx:id=\"bookCondition\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert postMaterial != null : "fx:id=\"bookMaterial\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert postCourse != null : "fx:id=\"bookCourse\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert bookCoverImage != null : "fx:id=\"bookCoverImage\" was not injected: check your FXML file 'DetailedModelView.fxml'.";

        backButton.setDisable(true);

    }

}
