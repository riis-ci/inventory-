package com.routeone.interview;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.routeone.interview.dao.Item;
import com.routeone.interview.exceptions.InvalidFileException;
import com.routeone.interview.exceptions.InvalidInputException;
import com.routeone.interview.impl.ReceiptImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StoreRegister {
	Receipt receipt = null;
	List<Item> inventoryList = new ArrayList<Item>();
	List<Item> checkedOutItems = new ArrayList<Item>();
	
	public StoreRegister() {
	}

	/**
	 * Read and loads the Inventory File to List
	 * @param inventoryFile
	 * @throws InvalidFileException
	 * @throws InvalidInputException
	 */
public void loadInventory(File inventoryFile) throws InvalidFileException, InvalidInputException{
	
	if(inventoryFile==null){
		throw new InvalidFileException("Input file does not exist");
	}
		
	try {
			FileReader fr = null;
	        BufferedReader br = null;
			
			try {
				fr = new FileReader(inventoryFile);
				br = new BufferedReader(fr);
				
				String currentItem;

				while ((currentItem = br.readLine()) != null) {
					Item item = new Item();
					 String[] itemColumn = currentItem.split(",");

					 if( itemColumn[0].isEmpty() || itemColumn[1].isEmpty() || itemColumn[2].isEmpty() )
					 {
						throw new InvalidFileException("Field Value missing :: Name or Price not found for item");
					 }

					 item.setName(itemColumn[0]);
					 
					 item.setPrice(new Double(itemColumn[1]));
					 item.setCategory(itemColumn[2]);
					 inventoryList.add(item);
				}
                
			} catch (IOException e) {
				throw new InvalidFileException("Input file has problems during reading");
				

			} finally {

				try {

					if (br != null)
						br.close();

					if (fr != null)
						fr.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}

			}
	}
	catch(Exception e)
	{
		throw new InvalidFileException("Error in reading input file");
	}
}

/**
 * Calls method to create receipt with formatted price
 * Prints the receipt and price value
 * @param items
 * @return Receipt
 * @throws InvalidInputException
 */
public Receipt checkoutOrder(List<String> items) throws InvalidInputException{
	 checkedOutItems = getCheckedOutItems(items);
	 
	 receipt = new ReceiptImpl(checkedOutItems);
	 	 
	 System.out.println("Receipt.getFormattedTotal() ="+receipt.getFormattedTotal());	 
	 System.out.println("Receipt.getOrderedItems() = "+receipt.getOrderedItems());
	
	
	 return receipt;
}

/**
 * Returns list of checked out items
 * @param items
 * @return List
 * @throws InvalidInputException
 */
private List<Item> getCheckedOutItems(List<String> items) throws InvalidInputException {
	Iterator<String> iterator = items.iterator();
	 List<Item> checkoutList = new ArrayList<Item>();
	 List <String> itemNames =  new ArrayList<String>();;
	 
	while (iterator.hasNext()) {
		
		String currentItem =iterator.next();
	
			itemNames.add(currentItem);
			
	}
             if(itemNames.size() ==0 )
             {
            	 throw new InvalidInputException("Check out item is not valid");
             }
             else
             {
                        	
       			for(String itemName : itemNames)
            	{
       				Iterator<Item> itrList = inventoryList.iterator();
       				int itemPresent =0;
       				
            		 while(itrList.hasNext())
            	  	{
            	  		Item inventoryItem = itrList.next();
            	  		if(itemName.equalsIgnoreCase(inventoryItem.getName()))
            			{
            					Item item =new Item();
            					item = inventoryItem;
            					checkoutList.add(item);
            					itemPresent =1;
            			}	
            		}
            		 if(itemPresent ==0)
         	  		{
         	  			throw new InvalidInputException("Item not present in the inventory :: "+itemName);
         	  		}
            		
            		}
            		System.out.println("After getting items from inventory");
			 
			 
             }
	    return checkoutList;
	}
/**
 * Loads Inventory File from file path
 * @param invFilePath
 * @return
 * @throws InvalidFileException
 */
public File  getInvFilePath(String invFilePath) throws InvalidFileException {
	File invFile= null;
	try{
		 invFile= new File(getClass().getClassLoader().getResource(invFilePath).getFile());
		// System.out.println("Inventory File is ready");
	}
    catch(NullPointerException exception)
	{
    	throw new InvalidFileException("Input file does not exist");
	}
	
	return invFile;
}


}// end class