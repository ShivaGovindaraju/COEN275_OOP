/**
 * 
 */
package govindaraju.assign1.q2;

import govindaraju.assign1.money.*;
import govindaraju.assign1.salebin.*;

/**
 * @author keshavgovindaraju
 *
 *This is mainly for testing SaleItem, Bin, and SmartBin from the saleitem package.
 *basic test cases were provided by the assignment document
 */
public class Q2TestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Include the following testcases and capture the output.
a) Create at least 5 instances of SaleItems with data of your choice. Make some of the items “fragile”.
b) Create an instance of a Bin.
c) Add the instances of SaleItems from a) to the Bin instance in b). Make sure that you exceed the maximum
weight of the bin.
d) Show details on the Bin instance in b)
e) Create at least 5 more instances of SaleItems with data of your choice. Make some of the items “fragile”.
f) Create an instance of a SmartBin.
g) Add the instances of SaleItems from d) to the Bin instance in b). Make sure that you exceed the maximum
weight of the bin.
h) Show details on the Bin instance in f)
i) Create an instance of a Bin as follows:
Bin bin3 = new SmartBin();
j) Can you add fragile SaleItems to bin3?
		 * */
		
		System.out.println("---Assignment 1 Required Testing---");
		
		// Part A
		//I just had fun making up items, even if they'd be ridiculous to find in a bin all grouped together
		//none of the items are actually priced or weighted accurately to the real-world, but that's alright
		//because they're just here for testing purposes
		ItemType itemA = new SaleItem("Textbook/Encyclopedia", new USMoney(48, 95), 10.0, false);
		ItemType itemB = new SaleItem("Antique Statuette", new USMoney(299, 48), 15.5, true);
		ItemType itemC = new SaleItem("Suit of Armor", new USMoney(1195, 75), 40.0, false);
		ItemType itemD = new SaleItem("Refridgerator", new USMoney(1200, 57), 125.7, false);
		ItemType itemE = new SaleItem("Guitar", new USMoney(57, 81), 3.5, true);

		// Part B
		BinType binA = new Bin();
		
		// Part C
		binA.addItem(itemA);
		binA.addItem(itemB);
		binA.addItem(itemC);
		binA.addItem(itemD);
		binA.addItem(itemE);
		
		// Part D
		System.out.println(binA.showDetails());
		
		// Part E
		//Some more nonsensical items one shouldn't really find in a salebin at a store...
		//fun-note: in D&D, the Staff of the Magi creates an explosion that rends reality and tears open dimensional rifts to the Astral plane when destroyed... *definitely* Fragile! :P
		ItemType itemF = new SaleItem("Photo Album", new USMoney(34, 57), 5.0, false);
		ItemType itemG = new SaleItem("Motorcycle", new USMoney(1500, 79), 212.8, false);
		ItemType itemH = new SaleItem("Birthday Cake", new USMoney(10, 15), 5.0, true);
		ItemType itemI = new SaleItem("Three Piece Suit", new USMoney(106, 75), 3.0, false);
		ItemType itemJ = new SaleItem("Staff of the Magi", new USMoney(100000, 00), 5.0, true);
		
		// Part F
		BinType binB = new SmartBin();
		
		// Part G
		binA.addItem(itemF);
		binA.addItem(itemG);
		binA.addItem(itemH);
		binA.addItem(itemI);
		binA.addItem(itemJ);
		
		// Part H
		System.out.println(binB.showDetails());
		
		// Part H - ii) additional check
		System.out.println(binA.showDetails());
		
		// Part I
		Bin bin3 = new SmartBin();
		
		// Part J
		bin3.addItem(itemB);
		bin3.addItem(itemF);
		bin3.addItem(itemH);
		bin3.addItem(itemJ);
		System.out.println(bin3.showDetails());
		
		System.out.println("---Additional Testing---");
		//I mostlyv just wanted to test if I could add all ten items to SmartBin binB, and what would be the result of all of them
		//though, I believe that not everything would fit, like the Refridgerator and Motorcycle
		binB.addItem(itemA);
		binB.addItem(itemB);
		binB.addItem(itemC);
		binB.addItem(itemD);
		binB.addItem(itemE);
		binB.addItem(itemF);
		binB.addItem(itemG);
		binB.addItem(itemH);
		binB.addItem(itemI);
		binB.addItem(itemJ);
		System.out.println(binA.showDetails());
		System.out.println(binB.showDetails());
		System.out.println(bin3.showDetails());
	}

}
