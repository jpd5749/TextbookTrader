/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
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
     * Test of clickSignin method, of class SignupFrameViewController.
     */
    @Test
    public void testClickSignin() throws Exception {
        System.out.println("clickSignin");
        ActionEvent event = null;
        SignupFrameViewController instance = new SignupFrameViewController();
        instance.clickSignin(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clickCreate method, of class SignupFrameViewController.
     */
    @Test
    public void testClickCreate() throws Exception {
        System.out.println("clickCreate");
        ActionEvent event = null;
        SignupFrameViewController instance = new SignupFrameViewController();
        instance.clickCreate(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class SignupFrameViewController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Users user = null;
        Stage stage = null;
        SignupFrameViewController instance = new SignupFrameViewController();
        instance.create(user, stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkEmail method, of class SignupFrameViewController.
     */
    @Test
    public void testCheckEmail() {
        System.out.println("checkEmail");
        Users newUser = new Users(999, "UnitTestEmail", "Test", "Unit", "testpassword");
        SignupFrameViewController instance = new SignupFrameViewController();
        String expResult = "UnitTestEmail";
        String result = newUser.getEmail();
        assertEquals(expResult, result);
    }
    /**
     * Test of exitAction method, of class SignupFrameViewController.
     */
    @Test
    public void testExitAction() {
        System.out.println("exitAction");
        ActionEvent event = null;
        SignupFrameViewController instance = new SignupFrameViewController();
        instance.exitAction(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class SignupFrameViewController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        SignupFrameViewController instance = new SignupFrameViewController();
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviousScene method, of class SignupFrameViewController.
     */
    @Test
    public void testSetPreviousScene() {
        System.out.println("setPreviousScene");
        Scene scene = null;
        SignupFrameViewController instance = new SignupFrameViewController();
        instance.setPreviousScene(scene);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
