/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Users;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonas
 */
public class SignupFrameViewControllerTest {

    public SignupFrameViewControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of checkEmail method, of class SignupFrameViewController.
     */
    @Test
    public void testCheckEmail() {
        System.out.println("checkEmail");
        Users newUser = new Users(999, "UnitTestEmail", "Test", "Unit", "testpassword");
        String expResult = "UnitTestEmail";
        String result = newUser.getEmail();
        assertEquals(expResult, result);
    }

}
