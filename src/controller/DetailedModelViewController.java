package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Textbookmodel;

public class DetailedModelViewController {

    @FXML
    private Button backButton;
    
    @FXML
    private Text bookTitle;

    @FXML
    private Text bookISBN;

    @FXML
    private Text bookCondition;

    @FXML
    private Text bookMaterial;

    @FXML
    private Text bookCourse;

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

    Textbookmodel selectedModel;
    Scene previousScene;

    public void setPreviousScene(Scene scene) {
        previousScene = scene;
        backButton.setDisable(false);

    }

    public void initData(Textbookmodel model) {
        selectedModel = model;
        bookTitle.setText(model.getTextbookname());
        bookISBN.setText(model.getIsbnnumber().toString());
        bookCondition.setText(model.getConditionofbook());
        bookMaterial.setText(model.getMaterialtype());
        bookCourse.setText(model.getMaterialcourse());

        try {
            // path points to /resource/images/
            String imagename = "/resource/images/" + model.getIsbnnumber().toString() + ".jpg";
            Image cover = new Image(getClass().getResourceAsStream(imagename));
            bookCoverImage.setImage(cover);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert bookTitle != null : "fx:id=\"bookTitle\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert bookISBN != null : "fx:id=\"bookISBN\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert bookCondition != null : "fx:id=\"bookCondition\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert bookMaterial != null : "fx:id=\"bookMaterial\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert bookCourse != null : "fx:id=\"bookCourse\" was not injected: check your FXML file 'DetailedModelView.fxml'.";
        assert bookCoverImage != null : "fx:id=\"bookCoverImage\" was not injected: check your FXML file 'DetailedModelView.fxml'.";

        backButton.setDisable(true);

    }

}
