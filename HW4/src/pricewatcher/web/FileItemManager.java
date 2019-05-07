package pricewatcher.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.*;
import pricewatcher.base.*;

public class FileItemManager extends ItemManager{ // has to called when remove button clicked too
	
	private static FileItemManager theInstance;
	
	public FileItemManager() {
	}
	
	public static FileItemManager getInstance() {
		if(theInstance == null) {
			theInstance = new FileItemManager();
		}
		return theInstance;
		
	}
	
//	@Override
	public void start() {
		load();
	}
	// needs to add, remove, change, save, update, load/restore 
	
	@Override
	public Item add(String name, String url, float price) {   // have to add in string group at a later date
		Item item = super.add(name, url, price); 		  	  // Wrote add method that accepts group string and float price then sends to private Add method in ItemManager class
		System.out.println("add method");

		if(item != null) {
			save();
		}
		return item;
	}
	
	public Item remove() {
		Item item = super.Remove(); // professor code has url as a parameter for the super.remove()
		if(item != null) {
			save();
		}
		return item;
	}
	
	public Item change(Item item) {
		item = super.change(item);	// have to add this method to ItemManager
		if(item != null) {
			save();
		}
		return item;
	}
	

	
//	public Item updatePrice(float price, String url) {
//		Item item = super.updatePrice(url, price); // prof had this in his code
//		if(item != null) {
//			save();
//		}
//		return item;
//	}
	
	protected void save() {
		System.out.println("Save method");
//		File file = new File ("/Users/Mildred/git/PriceWatcher/HW 3");
		PrintWriter writer;
		//String path="C:/Users/jacob/git/PriceWatcher/data3.txt";
		//System.out.println(getClass().getResource("data4.txt").getPath());
		//File file = new File(getClass().getResource("data4.txt").getPath());
//		try {
//			//file.createNewFile();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		System.out.println("File has be made");
		try {
		    writer = new PrintWriter(getClass().getResource("data4.txt").getPath());
			JSONArray array  = new JSONArray();
			for(int i=0;i< listModel.getSize();i++) {
				Item e = listModel.get(i); // would this work?
				array.put(e.toJson());
			}
			System.out.println("Test ");
			System.out.println(array.toString());
			writer.write(array.toString());
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	

	protected void load() {
//		File file = new File ("/Users/Mildred/git/PriceWatcher/HW 3");
//		Item localItem = this;
		try(BufferedReader reader = new BufferedReader(new FileReader("data.txt"))){
			JSONTokener tokener = new JSONTokener(reader);
			JSONArray array = new JSONArray(tokener);
			for(int i = 0; i < array.length(); i++) {
				Item item = Item.fromJson(array.getJSONObject(i));
				super.Add(item);
									// more code needed
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
}
