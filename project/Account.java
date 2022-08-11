package coe528.project;

import java.io.*;
import java.util.Scanner;


import coe528.project.BankAcc;
import coe528.project.BankAcc;
import java.awt.BorderLayout;


public class Account {
	//Manager can add or delete customer
	//to add a customer to a file => Uername:____  pass:_____ job: _____ BankAccount:______

	public boolean checkLogin(String job, String user, String pass) throws FileNotFoundException{

                 if(job == "manager") {
			if(user == "admin" && pass == "admin") {
				return true;
			}
		}

		if(job == "customer") {

			File file = new File(user + ".txt");

                       boolean check = file.exists();

			if(check = false) {

				return false;
			}
			Scanner scan = new Scanner(file);



			String [] component;
			component = scan.nextLine().split(" ");
                            System.out.println(component[0] + " " + component[1] + " " + component[2]);
                        System.out.println(component[2]);
                        System.out.println(pass);
                        String p = component[2];
                        if(pass == p){
                            System.out.println("IT WILL RETURN FALSE");
                            return false;
                        }

			if(component[0].equals(job) && component[1].equals(user)) {
                            System.out.println("CAUGHT HERE");
                            if(component[2].equals(pass)){
                                System.out.println("CAUGHT TWO");
                                return true;
                            }

				return false;
			}



		}

		return false;
	}


	public void addCustomer(String job, String user, String pass) throws IOException{
		if(job == "customer") {
			try {
			File f = new File(user + ".txt");

			User cust = new Silver(user, pass);
			BankAcc newAcc = new BankAcc(cust, 100);

                            String information = (job + " " + user + " " + pass + " " + "100");
                            BufferedWriter writer = new BufferedWriter(new FileWriter(user + ".txt"));
                                writer.write(information);
                                writer.close();

			}catch(FileNotFoundException e) {
				System.out.println("error");
			}
		}
	}



	public void deleteCustomer(String user) {
            System.out.println(user + "dasdsa");
                File f = new File(user + ".txt");
                System.out.println(f.getAbsolutePath());
                f.delete();

	}




}
