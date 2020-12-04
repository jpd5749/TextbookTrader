/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import model.Textbookmodel;
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
    private TableView<Textbookmodel> textbookTable; // Value injected by FXMLLoader

    @FXML // fx:id="materialName"
    private TableColumn<Textbookmodel, String> materialName; // Value injected by FXMLLoader

    @FXML // fx:id="materialCondition"
    private TableColumn<Textbookmodel, String> materialCondition; // Value injected by FXMLLoader

    @FXML // fx:id="materialType"
    private TableColumn<Textbookmodel, String> materialType; // Value injected by FXMLLoader

    @FXML // fx:id="materialCourse"
    private TableColumn<Textbookmodel, String> materialCourse; // Value injected by FXMLLoader

    @FXML // fx:id="materialUser"
    private TableColumn<Textbookmodel, String> materialUser; // Value injected by FXMLLoader


    //taken and modified from the Google doc
    // the observable list of textbooks that is used to insert data into the table
    private ObservableList<Textbookmodel> textbookData;
    // add the proper data to the observable list to be rendered in the table

    public void setTableData(List<Textbookmodel> textbookList) {
        // initialize the textbookData variable
        textbookData = FXCollections.observableArrayList();
        // add the textbook objects to an observable list object for use with the GUI table
        textbookList.forEach(t -> {
            textbookData.add(t);
        });
        // set the the table items to the data in textbookData; refresh the table
        textbookTable.setItems(textbookData);
        textbookTable.refresh();
    }

    public List<Textbookmodel> readByTitleContainingAdvanced(String textbookname) {
        Query query = manager.createNamedQuery("Textbookmodel.findByTitleAdvanced");

        // setting query parameter
        query.setParameter("textbookname", textbookname);

        // execute query
        List<Textbookmodel> textbooks = query.getResultList();
        for (Textbookmodel textbook : textbooks) {
            System.out.println(textbook.getIsbnnumber() + " " + textbook.getTextbookname() + " " + textbook.getConditionofbook() + " " + textbook.getMaterialtype() + " " + textbook.getMaterialcourse());
        }

        return textbooks;
    }
    //not supported yet
//    private List<Textbookmodel> readByUserContainingAdvanced(String user) {
//        Query query = manager.createNamedQuery("Textbookmodel.findByTextbookUserAdvanced");
//
//        // setting query parameter
//        query.setParameter("textbookUser", user);
//
//        // execute query
//        List<Textbookmodel> textbooks = query.getResultList();
//        for (Textbookmodel textbook : textbooks) {
//            System.out.println(textbook.getIsbnnumber() + " " + textbook.getTextbookname() + " " + textbook.getConditionofbook() + " " + textbook.getMaterialtype() + " " + textbook.getMaterialcourse());
//        }
//
//        return textbooks;
//    }
    
    
   
    @FXML
    void clickTitleSearch(ActionEvent event) {
        // getting the name from input box
        String title = searchText.getText();
        // calling a db read operaiton, readByName
        List<Textbookmodel> textbooks = readByTitleContainingAdvanced(title);
        if (textbooks == null || textbooks.isEmpty()) {
            // show an alert to inform user
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");// line 2
            alert.setHeaderText("The following error occured:");// line 3
            alert.setContentText("No textbooks matching that query were found");// line 4
            alert.showAndWait(); // line 5
        } else {
            // setting table data
            setTableData(textbooks);
        }
    }
    
    
    @FXML
    void clickUserSearch(ActionEvent event) {
        System.out.println("Not Supported Yet.");
//        // getting the name from input box
//        String user = searchText.getText();
//        // calling a db read operaiton, readByName
//        List<Textbookmodel> textbooks = readByUserContainingAdvanced(user);
//        if (textbooks == null || textbooks.isEmpty()) {
//            // show an alert to inform user
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Information Dialog Box");// line 2
//            alert.setHeaderText("The following error occured:");// line 3
//            alert.setContentText("No textbooks matching that query were found");// line 4
//            alert.showAndWait(); // line 5
//        } else {
//            // setting table data
//            setTableData(textbooks);
//        }
    }
    
        @FXML
    void clickLogin(ActionEvent event) {
            System.out.println("Not supported yet either.");
    }
    
    
    //Show details
    @FXML
    void showDetailsInPlace(ActionEvent event) throws IOException {
        //code taken and refurbished from Google doc
        // pass currently selected model
        Textbookmodel selectedTextbook = textbookTable.getSelectionModel().getSelectedItem();

        
        // fxml loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailedModelView.fxml"));

        // load the ui elements
        Parent detailedModelView = loader.load();

        // load the scene
        Scene tableViewScene = new Scene(detailedModelView);

        //access the detailedControlled and call a method
        DetailedModelViewController detailedControlled = loader.getController();


        detailedControlled.initData(selectedTextbook);

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
        manager = (EntityManager) Persistence.createEntityManagerFactory("JonasJasonFXMLPU").createEntityManager();

        //Copied and modified from Google doc
        // set the cell value factories for the TableView Columns
        materialName.setCellValueFactory(new PropertyValueFactory<>("Textbookname"));
        materialCondition.setCellValueFactory(new PropertyValueFactory<>("Conditionofbook"));
        materialType.setCellValueFactory(new PropertyValueFactory<>("Materialtype"));
        materialCourse.setCellValueFactory(new PropertyValueFactory<>("Materialcourse"));
        
        //eanble row selection
        textbookTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
