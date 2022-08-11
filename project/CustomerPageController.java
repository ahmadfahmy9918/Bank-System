/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ahmad fahmy
 */

public class CustomerPageController implements Initializable {
    User customer;
    BankAcc bank;
            @FXML
        private Button logoutBTN;
      @FXML
      private TextArea textArea;
      @FXML
      private TextField depositAmount;
      @FXML
      private TextField withdrawAmount;
      @FXML
      private TextField purchaseAmount;




    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene homeScene = new Scene(home);
        Stage hStage = (Stage)(logoutBTN).getScene().getWindow();
        hStage.setScene(homeScene);
        hStage.show();
    }

    public void receiveData(User custom){
       customer = custom;
       bank = new BankAcc(customer, 100);
    }

    public void getBalance() throws IOException{
        textArea.clear();
        double balance = bank.getBalance();
        System.out.println(balance);
        String state = bank.checkState();
        System.out.println("THE CURRENT STATE IS " + state);

        String s = ("USER: " + customer.getUsername() + " LEVEL: " + state);

       textArea.appendText(s);
        String textAreaString = ("\n BALANCE: $"+ balance);

       textArea.appendText(textAreaString);

    }

    public void deposit() throws IOException{
        String dAmount = depositAmount.getText();
        double deposit = Double.parseDouble(dAmount);
        depositAmount.clear();
        if(deposit<0){
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "INVALID AMOUNT: Canot deposit less than 0");
            System.out.println("CAUGHT NO FILE");
        }
        else{

        bank.deposit(deposit);
        getBalance();
        }

    }

    public void withdraw() throws IOException{
        String withdraw = withdrawAmount.getText();
        double dwithdraw = Double.parseDouble(withdraw);
        if(dwithdraw <= bank.getBalance() && dwithdraw > 0){
             withdrawAmount.clear();
             bank.withdraw(dwithdraw);
             getBalance();
        }
        else{
               Component frame = null;
                JOptionPane.showMessageDialog(frame, "INVALID AMOUNT: CHECK FUNDS");
                System.out.println("CAUGHT NO FILE");
        }

    }

    public void makePurchae() throws IOException{

        String pAmount = purchaseAmount.getText();
        double purchase = Double.parseDouble(pAmount);
        if(bank.getBalance() < 10000 && purchase + 20 >  bank.getBalance()){
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "INVALID AMOUNT: INSUFICIENT FUNDS. TRY AGAIN");
        }
        else if(bank.getBalance() > 10000 && bank.getBalance() <20000 && purchase + 10 >  bank.getBalance()){
             Component frame = null;
            JOptionPane.showMessageDialog(frame, "INVALID AMOUNT: INSUFICIENT FUNDS. TRY AGAIN");
        }
        else if(purchase > 50){
        purchaseAmount.clear();
                System.out.println("PURCHASE AMOUNT: " + purchase);
        bank.onlinePurchae(purchase);
        getBalance();
        }
        else{
                 Component frame = null;
                JOptionPane.showMessageDialog(frame, "INVALID AMOUNT: THE PURCHASE AMOUNT MUST BE GREATER THAN 50. TRY AGAIN");
                System.out.println("CAUGHT NO FILE");
        }


    }

}
