package coe528.project;

/**
 * @author ahmad fahmy | 500913092
 * Overview Clause
 * This class contains the required fields of a bank application user. These required fields include
 * the username and password for each customer. This class is abstract and is extended by the different types
 * of users (silver, gold, platinum). The stated classes override the methods getRole() and getLevel()
 * This class is immutable as the username and password Strings are final and cannot be changed/adjusted later on.
 *
 * ABSTRACTION FUNCTION:
 * The abstraction function is as follows:
 *          user is an abstract object of type User
 *      AF(c):
 *          user.username = c.username
 *          user.password = c.password
 *
 * REP INVARIANT:
 * The rep invariant is as follows:
 *         RI(c):   IF c.username == null 'or' c.password == null 'or'
 *                  c.username is empty 'or' c.password is empty
 *                  RETURN FALSE

*/

public abstract class User {
	final private String username;
	final private String password;

        /**
        * REQUIRES: N/A
        * MODIFIES: Inputs are username and password of type String
        * EFFECTS: Initializes the username and password for the customer
        * @author jason
         */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
         * Requires: N/A
         * Modifies:N/A
         * Effects: Returns the role of the customer. In this instance returns User
        */
	abstract String getRole();

        /**
         * Requires: N/A
         * Modifies:N/A
         * Effects: Returns level of each customer (silver, gold, platinum)
        */
        abstract String getLevel();


          /**
         * Requires: N/A
         * Modifies:N/A
         * Effects: Returns the username of the customer.
        */
	public String getUsername() {
		return username;
	}

        /**
         * Requires: N/A
         * Modifies:N/A
         * Effects: Returns the password of the customer.
        */
	public String getPassword() {
		return password;
	}


        /**
         * Requires: N/A
         * Modifies:N/A
         * Effects: Returns a string containing the username and password
        */
        public String toString(){
            String s;
            s = ("USERNAME: " + getUsername() + " PASSWORD" + getPassword());
            return s;
        }

        /**
         * Requires: N/A
         * Modifies:N/A
         * Effects: Returns false if the conditions stated in the rep invariant occur. Else return true
        */
        public boolean repOk(){
            if(username.isEmpty() || password.isEmpty() || username.equals(null) || password.equals(null)){
                return false;
            }
            return true;
        }


}
