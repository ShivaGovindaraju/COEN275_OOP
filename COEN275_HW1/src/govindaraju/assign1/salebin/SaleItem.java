package govindaraju.assign1.salebin;

import govindaraju.assign1.money.USMoney;

/*
 * SaleItem class implements the ItemType interface as described in the assignment document
 */

public class SaleItem implements ItemType {
	//private variables as described in assignment document
	private String itemName;
	private USMoney price;
	private double weight;
	private boolean fragile;
	
	// Default Constructor
	public SaleItem() {
		itemName = "";
		price = new USMoney();
		weight = 0.0;
		fragile = false;
	}
	
	//Overloading the Default Constructor
	//as described in the assignment document
	public SaleItem(String itemName, USMoney price, double weight, boolean fragile) {
		this.itemName = itemName;
		this.price = price;
		this.weight = weight;
		this.fragile = fragile;
	}

	//getters and setters
	@Override
	public boolean isFragile() {
		return this.fragile;
	}

	@Override
	public USMoney getPrice() {
		return this.price;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setPrice(USMoney price) {
		this.price = price;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setFragile(boolean fragile) {
		this.fragile = fragile;
	}

	@Override
	public String getDetails() {
		return this.itemName + ", " + this.price;
	}

}
