package govindaraju.assign1.q1;

import govindaraju.assign1.money.*;

public class USMoneyTester {

	public static void main(String[] args) {
		//INITIAL TESTING of USMoney.java
		/*
		USMoney m1 = new USMoney (15,80);
		System.out.println (m1); 	// Should print $15.80
		m1.addTo(25,100);
		System.out.println (m1);    // Should print $41.80
		USMoney m2 = m1.add( new USMoney (2.90));
		System.out.println (m2);    // Should print $44.70	
		System.out.println (m1);    // Should print $41.80
		*/
		
		System.out.println("---Assignment 1 Required Testing ---");
		//Desired testing of USMoneyTester
		//copied directly from the Assignment document
		//comments below were self-inserted for checking outputs
		USMoney amt1 = new USMoney ();
		System.out.println (amt1); //should print $0.00
		amt1.setCents (250);
		System.out.println (amt1); //should print $2.50
		amt1.setDollars (10);
		System.out.println (amt1); //should print $10.50
		System.out.println (amt1.getCents()); //should print 50
		USMoney amt2 = amt1.add( new USMoney (2,90));
		System.out.println (amt1); //should print $10.50
		System.out.println (amt2); //should print $13.40
		amt2.addTo(amt1.getDollars(), amt1.getCents());
		System.out.println (amt2); //should print $26.80
		USMoney amt3 = new USMoney (99,120);
		amt3.addTo(99,120);
		System.out.println (amt3); //should print $200.40
		
		System.out.println("---Additional Testing---");
		USMoney amt4 = new USMoney(-100, -50);
		System.out.println(amt4); //should print $0.00
		USMoney amt5 = new USMoney(100, -50);
		System.out.println(amt5); //should print $99.50
		amt5.addTo(-50, -25);
		System.out.println(amt5); //should print $49.25
		amt5.addTo(0, -575);
		System.out.println(amt5); //should print $43.50

	}

}
