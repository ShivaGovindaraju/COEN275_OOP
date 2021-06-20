package govindaraju.assign1.money;

/*
 * Class for USMoney objects
 * parameters given in the assignment handout
 * models currency in a rudimentary fashion
 */
public class USMoney {
	//Private Variables
	private int dollars; //US currency is $XXXX.YY
	private int cents; //where XXXX is dollars, and YY are cents
	
	//Constructors
	
	//Default contstructor
	//no clarifications were given, but assumption is that undeclared
	//initializations start at $0.00
	public USMoney() {
		dollars = 0;
		cents = 0;
	}
	
	//Overloading the default constructor
	//Constructor takes in two integers, sets private variables
	public USMoney(int dollars, int cents) {
		this.dollars = dollars;
		this.cents = cents;
		this.checkCents();	//In order to account for problematic inputs
	}
	
	//overloadinbg the default constructor again
	//Constructor takes in a double, splits into private variables
	public USMoney(double value) {
		this.dollars = (int)value;
		this.cents = (int)(value * 100) % 100; //this makes it so only the first two
						//decimal points form the cents field
						//does not account for rounding the hundreth's
						//place of the double
		this.checkCents();	//In order to account for problematic inputs
	}
	
	//Getters and Setters
	//These return the dollars/cents values or set them
	//they also checek to make sure the input values for setters are valid
	public int getDollars() {
		return dollars;
	}
	public void setDollars(int dollars) {
		this.dollars = dollars;
		this.checkDollars(); //in order to account for problematic inputs
	}
	public int getCents() {
		return cents;
	}
	public void setCents(int cents) {
		this.cents = cents;
		this.checkCents();	//In order to account for problematic inputs
	}
	
	//Methods required by assignment
	
	//Take in two integers and add them to the object's variables
	public void addTo(int dollars, int cents) {
		this.dollars = this.dollars + dollars;
		this.cents = this.cents + cents;
		this.checkCents();	//In order to account for problematic inputs
	}
	
	//create new USMoney object which contains the added values of this object
	//and another USMoney object passed as a parameter
	public USMoney add(USMoney money) {
		USMoney NewMoney = new USMoney(this.getDollars() + money.getDollars(),
						this.getCents() + money.getCents());
		return NewMoney;
	}
	
	//Overriding toString to supply proper return-string
	@Override
	public String toString() {
		return "$" + dollars + "." + cents;
	}
	
	//Personally created Methods
	
	//To account for cents > 99, and overflow the extra cents into dollars
	//or if cents < 0, to reduce dollars appropriately
	//Personal Assumption: neither Cents nor Dollars should be negative.
	//Assumption 2: if Cents is negative, reduce Dollars until Cents is non-negative
	//Assumption 3: Dollars should never be less than $0.
	private void checkCents() {
		if (this.cents > 99) {
			//if cents > 99, take the 2 least significant digits and put them in cents
			//and add cents/100 to dollars
			this.dollars = this.dollars + (this.cents / 100);
			this.cents = this.cents % 100;
		} else if (this.cents < 0) {
			//an erroneous condition where one has negative cents
			//send out error message and reduce dollars to make cents non-negative.
			//follow standard method of creating change using US currency
			System.out.println("Error: Cents should not be less than $0.00 cents.");
			this.dollars = this.dollars + (this.cents / 100) - 1;
			this.cents = 100 + (this.cents % 100);
			if (this.dollars < 0) {
				this.cents = 0; //if dollars is negative, cents should be 0.
			}
		}
		this.checkDollars();
	}
	
	//to account for dollars < 0, and stop that underflow, it floors dollars to 0
	//see assumption 3 noted above.
	private void checkDollars() {
		if (this.dollars < 0) {
			System.out.println("Error: Dollars cannot be less than $0. dollars.");
			this.dollars = 0;
		}
	}
}
