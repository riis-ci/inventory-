package com.routeone.interview;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.routeone.interview.StoreRegister;
import com.routeone.interview.exceptions.InvalidFileException;
import com.routeone.interview.exceptions.InvalidInputException;


public class InventoryApp {
	private static final String  INV_FILE_PATH = "sample-inventory.csv";
	public static void main(String[] args) throws  InvalidFileException, InvalidInputException
	{ 
		//Input items from command prompt
		if(args.length==0)
		{
			throw new InvalidInputException("No input item found");
		}
		List<String> inputItemsOrdered = new ArrayList<String>();
		 for (String inputItem : args) {
	         inputItemsOrdered.add(inputItem);
		 }
		 
		//Loading Inventory File
		StoreRegister storeRegister = new StoreRegister();
		File inventoryFile = null;
		if(INV_FILE_PATH == null )
		 throw new InvalidFileException("File path is empty");
		else
			inventoryFile = storeRegister.getInvFilePath(INV_FILE_PATH);
		if(inventoryFile.exists())
			storeRegister.loadInventory(inventoryFile);
		else
			 throw new InvalidFileException("File does not exist");
		
		//Checkout the items ordered
		storeRegister.checkoutOrder(inputItemsOrdered);
		
	}//end main
}
