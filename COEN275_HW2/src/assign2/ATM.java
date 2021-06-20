/**
 * 
 */
package assign2;

import java.util.*;

/**
 * Class to simulate ATM objects. Contains collection of BankAccounts and password encryption objects.
 * Also contains Main method for testing ATM and BankAccount classes.
 * Shiva K Govindaraju / COEN 275 / Assignment 2 / Submitted 11 Feb 2020
 * @author keshavgovindaraju
 *
 */
public class ATM {
	//private data members of ATM class
	//used an ArrayList as the collection for accounts to provide no limit to account number
	private ArrayList<BankAccount> accounts;
	private Encryptable passwordEncoder;
	
	/**
	 * Default Constructor
	 */
	public ATM() {
		this.accounts = new ArrayList<BankAccount>();
		this.passwordEncoder = new Encryptor();
	}
	
	/**
	 * Method for adding BankAccounts to the ATM's collection of accounts.
	 * Takes in the parameters and creates a BankAccount using those parameters, and adds it to the collection.
	 * If a BankAccount is to be created which shares an accntId with an existing BankAccount within accounts,
	 * the addition of the new Account fails and returns an error message.
	 * 
	 * @param accntId	the ID of the new account
	 * @param name		the name of the new account holder
	 * @param balance	the balance of the new account
	 * @param password	the (non-encrypted) password of the new account
	 */
	public void addAccount(String accntId, String name, double balance, String password) {
		//We do not want to add an account which already exists to the ATM.
		//So we'll check if a BankAccount is already in accounts with the same accntId
		for (int i = 0; i < this.accounts.size(); i++) {
			if (this.accounts.get(i).getAccntId().equals(accntId)) {
				System.out.println("[ATM] Error: Account " + this.accounts.get(i).getAccntId() + " already exists within the ATM.");
				return;
			}
		}
		//the new account doesn't have the same accntId as an existing account
		String encryptedPassword = this.passwordEncoder.encrypt(password);
		BankAccount newAccount = new BankAccount(accntId, name, balance, encryptedPassword);
		this.accounts.add(newAccount);
		return;
	}
	
	/**
	 * A method to withdraw money from a given account based on ID and pasword.
	 * The method searches the collection of BankAccounts in accounts for a BankAccount which matches
	 * both the accntID given as a parameters, as well whose decrypted password matches the parameter's password.
	 * Should the BankAccount be found with both the correct ID and password, the parameter for amount is
	 * withdrawn from the BankAccount. The amount taken from the BankAccount is returned to the caller.
	 * 
	 * @param accntId	ID of the account to be withdrawn from
	 * @param password	plaintext password of the account to be withdrawn from
	 * @param amount	the amount to be withdrawn from the account
	 * @return			the amount of money actually withdrawn from the account
	 */
	public double getMoney(String accntId, String password, double amount) {
		//these data members aren't entirely necessary (it would be simple to modify app-logic to remove them)
		//but I'm using them to make the code more readable and to make it easier to look at for myself.
		BankAccount current;
		String decrypted;
		for(int i = 0; i < this.accounts.size(); i++) {
			//check each BankAccount in the acounts collection
			current = this.accounts.get(i);
			if(current.getAccntId().equals(accntId)) {
				//confirm that the account current has the same accntId as the desired account to be withdrawn from
				//decrypted has the plaintext of the password stored in the account
				decrypted = this.passwordEncoder.decrypt(current.getPassword());
				//confirm that the two passwords are the same
				if(decrypted.equals(password.toUpperCase())) {
					//passwords match, call withdraw
					return current.withdraw(amount);
				} else {
					System.out.println("[ATM] Error: Incorrect Password for Account " + accntId + ".");
					return 0.0;
				}
			}
		}
		System.out.println("[ATM] Error: Could not locate Account " + accntId + " within ATM accounts.");
		return 0.0;
	}

	/**
	 * Main method used for testing the ATM and BankAccount classes.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("----- Assignment 1 Questions 3 and 4 Tester -----\n");
		System.out.println("--- Required Testing Steps ---\n");
		//See the assignment document for the exact details regarding the following testing steps
		
		//Step 1
		ATM teller = new ATM();
		
		//Step 2
		System.out.println("[ATM] Creating new Account.");
		teller.addAccount("A123", "M.Jones", 2000.00, "fall-quarter");
		teller.accounts.get(0).printAccountInfo();
		System.out.println();
		
		//Step 3
		System.out.println("[ATM] Creating new Account.");
		teller.addAccount("B654", "B.Smith", 100.00, "renaissance");
		teller.accounts.get(1).printAccountInfo();
		System.out.println();
		
		//Step 4 - correct password, should be fine
		System.out.println("[ATM] Getting $" + teller.getMoney("A123", "fall-quarter", 1000.0) + " from Account A123.\n");
		
		//Step 5 - incorrect password, should print error
		System.out.println("[ATM] Getting $" + teller.getMoney("B654", "essence", 100.0) + " from Account B654.\n");
		
		//Step 6
		System.out.println("[ATM] Getting $" + teller.getMoney("B654", "renaissance", 50.0) + " from Account B654.\n");
		
		//Step 7 - this should empty out account B654
		System.out.println("[ATM] Getting $" + teller.getMoney("B654", "renaissance", 50.0) + " from Account B654.\n");
		
		//Step 8 - trying to remove money that doesn't exist in the account, should return error
		System.out.println("[ATM] Getting $" + teller.getMoney("B654", "renaissance", 50.0) + " from Account B654.\n");
		
		//Step 9 - trying to create duplicate A123 account. Should fail.
		System.out.println("[ATM] Creating new Account.");
		teller.addAccount("A123", "M.Jones", 1000.00, "fall-quarter");
		System.out.println();

		System.out.println("--- Required Testing Complete ---\n");
	}

}
