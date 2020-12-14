/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Posts;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joe
 */
public class CreatePostControllerTest {
    
    public CreatePostControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of checkPost method, of class CreatePostController.
     */
    @Test
    public void testCheckPost() {
        System.out.println("checkPost");
        Posts newPost = new Posts(9999, "titleTest", "conditionTest", "typeTest", 999);
        String expResult = "titleTest";
        String result = newPost.getTitle();
        assertEquals(expResult, result);
    }

    
}
