/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author ahmad fahmy
 */
public class Silver extends User {

    public Silver(String user, String pass) {
        super(user, pass);
    }
   
    String getRole() {
		String role = "customer";
		return role;
	}
  
        public String getLevel(){
        return "Silver";

    }

}
