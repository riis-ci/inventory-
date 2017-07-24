package com.routeone.interview.dao;


public class Item implements Comparable<Item>{
	
	private String name;
	private Double price;
	private String category;
	
	public Item(String name, double price, String category) {
		this.name=name;
		this.price=price;
		this.category=category;
	}
	
	public Item() {
	
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


   @Override
   public String toString() {
       return "Item{" + "name=" + name + ", price=" + price + ", category=" + category + '}';
   }

   @Override
   public int compareTo(Item o) {
         return new Double(this.price).compareTo(new Double(o.price));
   }
}