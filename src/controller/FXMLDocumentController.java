/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Textbookmodel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jonas
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button buttonCreateTextbook;

    @FXML
    private Button buttonRead;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonDelete;

    //Create operation (taken from Google doc)
    @FXML
    public void create(Textbookmodel textbook) {

        // Create operation
        try {
            // begin transaction
            manager.getTransaction().begin();

            // sanity check
            if (textbook.getIsbnnumber() != null) {

                // create textbook
                manager.persist(textbook);

                // end transaction
                manager.getTransaction().commit();

                System.out.println(textbook.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Read Operations
    public List<Textbookmodel> readTextbook() {
        Query query = manager.createNamedQuery("Textbookmodel.findAll");
        List<Textbookmodel> textbooks = query.getResultList();

        for (Textbookmodel t : textbooks) {
            System.out.println(t.getIsbnnumber() + " " + t.getTextbookname() + " " + t.getConditionofbook() + " " + t.getMaterialtype() + " " + t.getMaterialcourse());
        }

        return textbooks;
    }

    public Textbookmodel readByISBN(int isbn) {
        Query query = manager.createNamedQuery("Textbookmodel.findByIsbnnumber");

        // setting query parameter
        query.setParameter("isbnnumber", isbn);

        // execute query
        Textbookmodel textbook = (Textbookmodel) query.getSingleResult();
        if (textbook != null) {
            System.out.println(textbook.getIsbnnumber() + " " + textbook.getTextbookname() + " " + textbook.getConditionofbook() + " " + textbook.getMaterialtype() + " " + textbook.getMaterialcourse());
        }

        return textbook;
    }

    public List<Textbookmodel> readByClass(String materialcourse) {
        Query query = manager.createNamedQuery("Textbookmodel.findByMaterialcourse");

        // setting query parameter
        query.setParameter("materialcourse", materialcourse);

        // execute query
        List<Textbookmodel> textbooks = query.getResultList();
        for (Textbookmodel textbook : textbooks) {
            System.out.println(textbook.getIsbnnumber() + " " + textbook.getTextbookname() + " " + textbook.getConditionofbook() + " " + textbook.getMaterialtype() + " " + textbook.getMaterialcourse());
        }

        return textbooks;
    }

    @FXML
    void readByClass(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter Course:");
        String classname = input.nextLine();

        List<Textbookmodel> t = readByClass(classname);
        System.out.println(t.toString());
    }

    @FXML
    void readTitleContaining(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter Title:");
        String titlesearch = input.nextLine();

        List<Textbookmodel> t = readByTitleContaining(titlesearch);
        System.out.println(t.toString());
    }

    public List<Textbookmodel> readByTitleContaining(String textbookname) {
        Query query = manager.createNamedQuery("Textbookmodel.findByTextbookname");

        // setting query parameter
        query.setParameter("textbookname", textbookname);

        // execute query
        List<Textbookmodel> textbooks = query.getResultList();
        for (Textbookmodel textbook : textbooks) {
            System.out.println(textbook.getIsbnnumber() + " " + textbook.getTextbookname() + " " + textbook.getConditionofbook() + " " + textbook.getMaterialtype() + " " + textbook.getMaterialcourse());
        }

        return textbooks;
    }

    public void createTextbook(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ISBN:");
        int isbn = Integer.parseInt(input.nextLine());

        System.out.println("Enter Title:");
        String title = input.nextLine();

        System.out.println("Enter Condition:");
        String condition = input.nextLine();

        System.out.println("Enter Material Type:");
        String material = input.nextLine();

        System.out.println("Enter Course:");
        String course = input.nextLine();

        // create a textbook instance
        Textbookmodel textbook = new Textbookmodel();

        // set properties
        textbook.setIsbnnumber(isbn);
        textbook.setTextbookname(title);
        textbook.setConditionofbook(condition);
        textbook.setMaterialtype(material);
        textbook.setMaterialcourse(course);

        // save this textbook to database by calling Create operation        
        create(textbook);
    }

    // Update operation
    public void update(Textbookmodel model) {
        try {

            Textbookmodel existingTextbook = manager.find(Textbookmodel.class, model.getIsbnnumber());

            if (existingTextbook != null) {
                // begin transaction
                manager.getTransaction().begin();

                // update all atttributes
                existingTextbook.setTextbookname(model.getTextbookname());
                existingTextbook.setConditionofbook(model.getConditionofbook());
                existingTextbook.setMaterialtype(model.getMaterialtype());
                existingTextbook.setMaterialcourse(model.getMaterialcourse());

                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Delete operation
    public void delete(Textbookmodel textbook) {
        try {
            Textbookmodel existingTextbook = manager.find(Textbookmodel.class, textbook.getIsbnnumber());

            // sanity check
            if (existingTextbook != null) {

                // begin transaction
                manager.getTransaction().begin();

                //remove student
                manager.remove(existingTextbook);

                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void deleteTextbook(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ISBN:");
        int isbn = input.nextInt();

        Textbookmodel t = readByISBN(isbn);
        System.out.println("we are deleting this textbook: " + t.toString());
        delete(t);

    }

    @FXML
    void updateTextbook(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ISBN:");
        int isbn = Integer.parseInt(input.nextLine());

        System.out.println("Enter Title:");
        String title = input.nextLine();

        System.out.println("Enter Condition:");
        String condition = input.nextLine();

        System.out.println("Enter Material Type:");
        String material = input.nextLine();

        System.out.println("Enter Course:");
        String course = input.nextLine();

        // create a textbook instance
        Textbookmodel textbook = new Textbookmodel();

        // set properties
        textbook.setIsbnnumber(isbn);
        textbook.setTextbookname(title);
        textbook.setConditionofbook(condition);
        textbook.setMaterialtype(material);
        textbook.setMaterialcourse(course);

        // save this textbook to database by calling Create operation        
        update(textbook);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        Query query = manager.createNamedQuery("Textbookmodel.findAll");
        List<Textbookmodel> data = query.getResultList();

        for (Textbookmodel t : data) {
            System.out.println(t.getIsbnnumber() + " " + t.getTextbookname() + " " + t.getConditionofbook() + " " + t.getMaterialtype() + " " + t.getMaterialcourse());
        }
    }
    
        @FXML
    void clickSearch(ActionEvent event) {
            System.out.println("clicked");
    }

    // Database manager (code obtained from Google Doc)
    EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // loading data from database
        //database reference: "IntroJavaFXPU"
        manager = (EntityManager) Persistence.createEntityManagerFactory("JonasJasonFXMLPU").createEntityManager();
    }

}
