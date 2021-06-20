/**
 * 
 */
package assign2;

/**
 * @author keshavgovindaraju
 *
 */
public class BankAccount {
	private String accntId;
	private String name;
	private double balance;
	private String password;
	
	/**
	 * Default Constructor
	 */
	public BankAccount() {
		this.accntId = "";
		this.name = "";
		this.balance = 0.0;
		this.password = "";
	}
	
	/**
	 * Class Constructor with additional parameters for BankAccount initialization
	 * 
	 * @param accntId	BankAccount's ID
	 * @param name		the BankAccount holder's name
	 * @param balance	the balance the BankAccount should be initialized with, must be >= 0.0
	 * @param password	encrypted password for the BankAccount
	 */
	public BankAccount(String accntId, String name, double balance, String password) {
		this.accntId = accntId;
		this.name = name;
		this.balance = balance;
		this.password = password;
		if(this.balance < 0) {
			System.out.println("[BankAccount] Error: You cannot create a BankAccount with negative balance. Setting balance to 0.");
			this.balance = 0.0;
		}
	}
	
	/**
	 * Method for withdrawing money from the BankAccount. The input amount must be valid; 
	 * if it is, then money is removed from the BankAccount's balance and the value is returned to the caller.
	 * 
	 * @param amount	the amount of money to be withdrawn, must be 0 < amount <= balance
	 * @return			the amount of money removed from the account's balance.
	 */
	public double withdraw(double amount) {
		if (amount > 0.0) {
			if (amount <= this.getBalance()) {
				this.balance = this.balance - amount;
				return amount;
			} else {
				System.out.println("[BankAccount] Error: Attempted to draw $" + 
									amount + " amount from BankAccount with only $" + this.getBalance() + " balance.");
			}
		} else {
			System.out.println("[BankAccount] Error: You cannot withdraw negative amounts from a BankAccount.");
		}
		return 0.0;
	}
	
	/**
	 * Method for putting more money into the BankAccount's balance.
	 * 
	 * @param amount	the amount of money being added to the BankAccount's balance.
	 */
	public void deposit(double amount) {
		if (amount > 0.0) {
			this.balance += amount;
		} else {
			System.out.println("[BankAccount] Warning: You cannot despoit non-positive amounts into a BankAccount.");
		}
	}
	
	/**
	 * Method for printing BankAccount information (sans password) to stdout.
	 * Used for testing purposes, not a requirement for this assignment.
	 */
	public void printAccountInfo() {
		System.out.println("[BankAccount] Account Info: Account ID: " + this.getAccntId() + ", Name: " + this.getName() + ", Balance: $" + this.getBalance());
	}

	/**
	 * @return the accntId
	 */
	public String getAccntId() {
		return accntId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

}
