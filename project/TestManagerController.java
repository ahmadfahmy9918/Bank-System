/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ahmad fahmy
 */
public class TestManagerController implements Initializable {
    Account account = new Account();
    @FXML
    private TextField createUser;
    @FXML
    private PasswordField passUser;
    @FXML
    private TextArea textArea;
    @FXML
    private TextArea textArea1;
    @FXML
    private TextField dUser;
    @FXML
    private Button logout;




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void addCustomer() throws IOException{


        String user = createUser.getText();
        String pass = passUser.getText();
          File file = new File(user + ".txt") ;

        if(createUser.equals(null) || passUser.equals(null) || user.equals("") || pass.equals("")){
            Component frame = null;
                JOptionPane.showMessageDialog(frame, "INVALID: Username or password not entered. TRY AGAIN");
        }
        else if(file.exists()){
                Component frame = null;
                JOptionPane.showMessageDialog(frame, "INVALID: USER ALLREADY EXISTS. TRY AGAIN");
        }
        else if(user.equals("admin")){
                Component frame = null;
                JOptionPane.showMessageDialog(frame, "INVALID: USER cannot be admin. TRY AGAIN");
                 createUser.setText("");
                 passUser.setText("");

        }
        else{
        createUser.setText("");
        passUser.setText("");
        account.addCustomer("customer", user, pass);
        }
        print();
    }
    public void deleteCustomer() throws FileNotFoundException{

        System.out.println("woprkkeqw");
        String deleteUser = dUser.getText();
        File f = new File(deleteUser + ".txt");
        if(!f.exists()){
                Component frame = null;
                JOptionPane.showMessageDialog(frame, "INVALID USER DOES NOT EXIST. TRY AGAIN");


                System.out.println("CAUGHT NO FILE");
        }
        dUser.setText("");

        account.deleteCustomer(deleteUser);
        print();
    }
    public void refresh() throws FileNotFoundException{
        print();
    }


    public void print() throws FileNotFoundException{
        textArea.clear();
        File f = new File(".");
        File[] listFiles = f.listFiles();
        for(int i =0; i<listFiles.length; i++){
            if(listFiles[i].isFile()){
                if(listFiles[i].getName().endsWith(".txt")){
                    System.out.println("file" + listFiles[i].getName());
                    Scanner scan = new Scanner(listFiles[i]);
                    String [] comp;
                    comp = scan.nextLine().split(" ");
                    String form = String.format("USERNAME: %2s  PASSWORD: %5s", comp[1], comp[2]);
                    textArea.appendText(form);

                    textArea.appendText("\n -------------------------------------------------------------------- \n");

                    scan.close();



                }

            }
        }


    }

    public void logout() throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene homeScene = new Scene(home);
        Stage hStage = (Stage)(logout).getScene().getWindow();
        hStage.setScene(homeScene);
        hStage.show();

    }

}
