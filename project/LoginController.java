/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;


import coe528.project.CustomerPageController;
import coe528.project.CustomerPageController;
import coe528.project.CustomerPageController;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ahmad fahmy
 * Overview Clause
 *  This class handles the login functionality of the code. It takes in the customers username and password credentials and verifies if it is correct
 * This class is mutable ass you can change the state of the account object
 *
 * Abstraction FUnction
 * Af(c) = customer
 *      c.if = customer.user, c.pass = customer.pass
 *
 * Rep Invariant: (Username and Password cannot be null)
 * RI(c) = true if c.user != null and c.pass != null
 *
 */
public class LoginController implements Initializable {
    private Account acc = new Account();

    @FXML
    private Button loginBTN;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }

    /**
     * REQUIRES: Inputed username and password must be a string
     * Modifies: N/A
     * EFFECTS: Takes in input for username and password, sends the inputed credentials to the checkLogin method in the Account class which returns true or false
     * @param event
     * @throws IOException
     */
    @FXML
    private void login(ActionEvent event) throws IOException {

        String username = user.getText();
        String password = pass.getText();
        if(username.equals("admin") && password.equals("admin")){
            boolean checkCred = acc.checkLogin("manager", username, password);
            if(checkCred = true){
                Parent managerPage = FXMLLoader.load(getClass().getResource("Manager.fxml"));
                Scene managerScene = new Scene(managerPage);
                Stage mStage = (Stage)((Node)event.getSource()).getScene().getWindow();
                mStage.setScene(managerScene);
                mStage.show();
            }
        }
        else{
            try{
            boolean checkCred = acc.checkLogin("customer", username, password);
                System.out.println("IT IS RETURNING " + checkCred);

            if(checkCred == true){


                System.out.println("LOGGEDINCUSTOMER");
                File file = new File(username + ".txt");
                Scanner scan = new Scanner(file);
                String[] comp;
                comp = scan.nextLine().split(" ");
                double balance = Double.parseDouble(comp[3]);
                System.out.println(" \n THE BALANCE IS :" + balance);


                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CustomerPage.fxml"));
                Parent cPage = loader.load();
                Scene cScene = new Scene(cPage);
                CustomerPageController controller = loader.getController();
                if(balance < 10000){
                    controller.receiveData(new Silver(username, password));
                }
                if(balance>10000 && balance<20000){
                    controller.receiveData(new Gold(username, password));
                }
                if(balance>20000){
                    controller.receiveData(new Plat(username, password));
                }

                Stage cStage = (Stage)(loginBTN).getScene().getWindow();
                cStage.setScene(cScene);
                cStage.show();




            }else if (checkCred == false){
                                Component frame = null;
                JOptionPane.showMessageDialog(frame, "INVALID LOGIN. TRY AGAIN");


                System.out.println("CAUGHT NO FILE");

                System.out.println("WRONG LOGIN");
            }
            }catch(FileNotFoundException e){

                Component frame = null;
                JOptionPane.showMessageDialog(frame, "INVALID LOGIN. TRY AGAIN");


                System.out.println("CAUGHT NO FILE");

            }
        }

    }
    /**
     * REQUIRES: N/A
     * MODIFIES: N/A
     * EFFECTS: Checks if the rep invariant holds true. Returns true if the inputed username and password do not equal null, else returns true
     * @return
     */
    public boolean repOk(){
        if(user != null && pass !=null){
            return true;
        }
        return false;
    }
    /**
     * REQUIRES: N/A
     * MODIFIES: N/A
     * EFFECTS: returns a string containg the inputed username and password
     * @return
     */
    public String toString(){
        String s;
        s=  ("Username" + user.getText() + " Password" + pass.getText());
        return s;

    }

}
