/**
 * 
 */
package govindaraju.assign1.salebin;

import govindaraju.assign1.money.USMoney;

/**
 * @author keshavgovindaraju
 *
 *SmartBin class as described in the assignment document, extends Bin and thus is a BinType object.
 */
public class SmartBin extends Bin {
	//unique variable to the SmartBin class
	private String label;
	
	/**
	 * Default Constructor
	 * inherits the same constructor from the superclass, Buin, but also sets SmartBin's Label
	 */
	public SmartBin() {
		super();
		this.setLabel("");
		this.setBinNumber("SM" + this.getBinNumber().substring(1)); //changes the binNumber to have the 
										//correct pre-fix letters "SM" for SmartBin, instead of "B" for Bin
	}

	//Getter and Setter for SmartBin's label variable
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	//override the addItem() method from Bin, as described in the assignment document
	//allows for the addition of Fragile objects to SmartBin
	//changes the label of SmartBin if item is Fragile
	@Override
	public void addItem(ItemType item) {
		//use the same error checks as from Bin, but no isFragile error
		if (item.getWeight() + this.getWeight() > maxWeight){
			System.out.println("Error: Could not add Item " + item.getDetails()
					+ " to Bin " + this.getBinNumber()
					+ " as the Item is too heavy.");
		} else if (this.getItemsArrayIndex() >= this.getItems().length) {
			System.out.println("Error: Could not add Item " + item.getDetails()
					+ " to Bin " + this.getBinNumber() 
					+ " as the Bin is full.");
		} else {
			//item can be placed in SmartBin, operate like in Bin
			this.items[this.getItemsArrayIndex()] = item;
			this.setItemsArrayIndex(this.getItemsArrayIndex() + 1);
			if (this.isHasItems() == false) {
				this.setHasItems(true);
			}
			//unique aspect of SmartBin
			if (item.isFragile()) { //if the item is fragile, change the label
				this.setLabel("Fragile - Handle with Care");
			}
		}
	}

	//overrides the calculatePrice() method from Bin, as described in assignment document
	//adds a $10 surcharge for each fragile item
	@Override
	public USMoney calculatePrice() {
		USMoney cost = new USMoney(100, 0); //SmartBin costs 100 dollars, even empty
		if (this.isHasItems()) {
			for (int i = 0; i < this.getItemsArrayIndex(); i++) {
				cost = cost.add(this.getSpecificItem(i).getPrice()); //just like in Bin
				if(this.getSpecificItem(i).isFragile()) {
					cost.addTo(10,  0); //if an item is frgile, add a $10 surcharge
				}
				//System.out.println("Adding "+this.getSpecificItem(i).getDetails() + "to Bin# " + this.getBinNumber());
			}
		}
		return cost;
	}

	//overrides the showDeatils() method from Bin, as described in assignment document
	@Override
	public String showDetails() {
		//System.out.println("Bin #: " + this.getBinNumber() + " " + this.isHasItems());
		return "Bin #: " + this.getBinNumber() + " " + this.getLabel() + 
				", Total Weight: " + this.getWeight() + " kg, Total Price: " + 
				this.calculatePrice();
	}

}
