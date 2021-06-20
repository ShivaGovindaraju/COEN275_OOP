/**
 * 
 */
package govindaraju.assign1.salebin;

import govindaraju.assign1.money.USMoney;

/**
 * @author keshavgovindaraju
 *
 *Bin class implements the BinType interface, parent to SmartBin
 *operates as described in assignment document
 */
public class Bin implements BinType {
	//class variables required by assignment document
	private String binNumber;
	protected ItemType [] items;
	static final double maxWeight = 100.0; //Defined by author to be any value we decided was appropriate
											//set to 100.0 kg as that seemed fitting for a salebin
	
	//class variables defined by the author to assist in method operations
	static final int maxBinItems = 50; // an assumption that the maximum number of items (of any ItemType) cannot exceed 50
										//which is reasonable as the Bin can only hold 100kg, and 50 is already a large quantity
										//for a store's sale bin
	private boolean hasItems;
	static int counter = 1;
	private int itemsArrayIndex;
	
	/**
	 * Default Constructor
	 * initializes variables as described by the assignment document
	 */
	public Bin() {
		this.binNumber = "B" + this.generateBinNumber();
		this.items = new ItemType[maxBinItems];
		this.itemsArrayIndex = 0;
		this.setHasItems(false);
	}

	//generates a binNumber using the counter variable
	//this number is unique
	public int generateBinNumber() {
		int num = this.getCounter();
		Bin.setCounter(counter + 1);
		return num;
	}

	/**
	 * @param hasItems the hasItems to set
	 */
	public void setHasItems(boolean hasItems) {
		this.hasItems = hasItems;
	}

	/**
	 * @param counter the counter to set
	 */
	public static void setCounter(int counter) {
		Bin.counter = counter;
	}

	//implements the addItem() method as described by the assignment document
	//has some extra conditions to account for potential issues
	@Override
	public void addItem(ItemType item) {
		if (item.getWeight() + this.getWeight() > maxWeight){
			//item cannot fit in the bin as it would make the contents too heavy for the bin
			System.out.println("Error: Could not add Item " + item.getDetails()
					+ " to Bin " + this.getBinNumber()
					+ " as the Item is too heavy.");
		} else if (item.isFragile()) {
			//items in Bin cannot be Fragile
			System.out.println("Error: Could not add Item " + item.getDetails()
					+  " to Bin " + this.getBinNumber()
					+ " as the Item is fragile.");
		} else if (this.itemsArrayIndex >= this.items.length) {
			//the bin is already full and cannot take any more items
			//note: this is an author-designed constraint for ease of implementation
			System.out.println("Error: Could not add Item " + item.getDetails()
					+ " to Bin " + this.getBinNumber() 
					+ " as the Bin is full.");
		} else {
			//item is allowed to go into the bin
			this.items[this.itemsArrayIndex] = item;
			this.itemsArrayIndex = this.itemsArrayIndex + 1;
			if (this.hasItems == false) {
				this.setHasItems(true); //this is book-keeping, to show that the bin has items in it and isn't empty
			}
		}
	}

	//implements the calculatePrice() method as described in the assignment document
	@Override
	public USMoney calculatePrice() {
		USMoney cost = new USMoney(100, 0); //all bins cost $100 for the bin itself, regardless of it if has items
		if (this.isHasItems()) { //don't bother with calculating content prices if the bin is empty
			for (int i = 0; i < this.getItemsArrayIndex(); i++) { //loop through items and add all the prices
				cost = cost.add(this.getSpecificItem(i).getPrice());
				//System.out.println("Adding "+this.getSpecificItem(i).getDetails() + "to Bin# " + this.getBinNumber());
			}
		}
		return cost;
	}

	//implements the getWeight() method as described in the assignment document
	//...well, the document doesn't quite describe it, but hints at it
	//the method returns the total weight of all contents within the bin
	//it does not account for the weight of the bin itself (treat as if weightless?)
	@Override
	public double getWeight() {
		double weight = 0.0;
		if (this.hasItems) { //if the bin is empty, there's nothing to return
			for (int i = 0; i < this.itemsArrayIndex; i++) {
				weight = weight + this.items[i].getWeight(); //add the weight of each item to the sum
			}
		}
		return weight;
	}

	//returns the number of items in the bin
	@Override
	public int getNoOfItems() {
		if(this.hasItems) {
			return this.itemsArrayIndex;
		}
		return 0;
	}

	//implements the showDetails() method described in the assignment document
	@Override
	public String showDetails() {
		//System.out.println("Bin #: " + this.getBinNumber() + " " + this.isHasItems());
		return "Bin #: " + this.getBinNumber() + ", Total Weight: " 
				+ this.getWeight() + " kg, Total Price: " 
				+ this.calculatePrice();
	}

	/**
	 * @return the binNumber
	 */
	public String getBinNumber() {
		return binNumber;
	}

	/**
	 * @return the items
	 */
	public ItemType[] getItems() {
		return items;
	}
	
	//return a specific item in the items array
	public ItemType getSpecificItem(int index) {
		return this.items[index];
	}

	//various Getters and Setters
	
	/**
	 * @return the maxweight
	 */
	public static double getMaxweight() {
		return maxWeight;
	}

	/**
	 * @return the hasItems
	 */
	public boolean isHasItems() {
		return hasItems;
	}

	/**
	 * @return the itemsArrayIndex
	 */
	public int getItemsArrayIndex() {
		return itemsArrayIndex;
	}

	/**
	 * @param itemsArrayIndex the itemsArrayIndex to set
	 */
	public void setItemsArrayIndex(int itemsArrayIndex) {
		this.itemsArrayIndex = itemsArrayIndex;
	}

	/**
	 * @param binNumber the binNumber to set
	 */
	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ItemType[] items) {
		this.items = items;
	}

	/**
	 * @return the counter
	 */
	public static int getCounter() {
		return counter;
	}

}
