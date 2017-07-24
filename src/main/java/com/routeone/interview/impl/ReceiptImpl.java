package com.routeone.interview.impl;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


import com.routeone.interview.Receipt;
import com.routeone.interview.dao.Item;


public class ReceiptImpl implements Receipt {
    private List<Item> checkedOutItems;
    private List<String> orderedCheckoutItems = new ArrayList<String>();
    Double formattedPrice = 0.0d;
	
    public ReceiptImpl(List<Item> checkedOutItems) {
		this.checkedOutItems = checkedOutItems;
	}
	
    /**
    * Sorts the checked out items in descending order by price
    */
	public void getSortedItems()
	{
		Collections.sort(checkedOutItems, (Comparator.comparing(Item::getPrice).reversed()).thenComparing(Item::getName));

		Iterator<Item> itrList = checkedOutItems.iterator();
		
		while(itrList.hasNext())
		{
			Item item =new Item();
			item = itrList.next();
		}

	}
	
	/**
	* @return Currency formatted total ($X,XXX.XX) of all items
	*/
	@Override
	public String getFormattedTotal() {
		Double price = 0d;
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		DecimalFormat df = new DecimalFormat("$#,###.00", symbols);

		for (Item item : this.checkedOutItems) {
			price += item.getPrice();
		}
		
		return (String) df.format(price);
	}

	/**
	* @return List of all items in descending order by amount
	*/
	@Override
	public List<String> getOrderedItems() {
		getSortedItems();
	    getSortedItemsAsString();
	    return orderedCheckoutItems;
	}
	
	/**
	 * Saves list of item names for display
	 */
	public void getSortedItemsAsString()
	{

		Iterator<Item> iterator = checkedOutItems.iterator();
		
		while (iterator.hasNext()) {
			
		Item currentItem =iterator.next();
	
		String strItemName = new String();
		strItemName = currentItem.getName() ;
	    orderedCheckoutItems.add(strItemName);
		}
	}

}
