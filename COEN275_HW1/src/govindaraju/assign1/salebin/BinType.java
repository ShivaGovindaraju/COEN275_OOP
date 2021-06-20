/**
 * 
 */
package govindaraju.assign1.salebin;

import govindaraju.assign1.money.*;

/**
 * @author keshavgovindaraju
 *
 *Interface given as described in assignment document
 *Used for BinType objects, like Bin and SmartBin
 */
public interface BinType {
	void addItem(ItemType item);
	USMoney calculatePrice();
	double getWeight();
	int getNoOfItems();
	String showDetails();
}
