/**
 * 
 */
package govindaraju.assign1.salebin;

import govindaraju.assign1.money.*;

/**
 * @author keshavgovindaraju
 *
 *Interface given as described in assignment document
 *Used for ItemType objects, like SaleItem
 */
public interface ItemType {
	boolean isFragile();
	USMoney getPrice();
	double getWeight();
	String getDetails();
}
