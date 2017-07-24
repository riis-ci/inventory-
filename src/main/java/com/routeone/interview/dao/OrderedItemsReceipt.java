package com.routeone.interview.dao;

import java.util.List;

public class OrderedItemsReceipt {
     List<Item> orderedItemsList;
     Double totalPrice;
     
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<Item> getOrderedItemsList() {
		return orderedItemsList;
	}
	public void setOrderedItemsList(List<Item> orderedItemsList) {
		this.orderedItemsList = orderedItemsList;
	}
}
