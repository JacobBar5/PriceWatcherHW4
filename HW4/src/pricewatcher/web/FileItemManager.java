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
	
	public void start() {
		load();
	} 
	
	@Override
	public Item add(String name, String url, float price) { 
		Item item = super.add(name, url, price);
		System.out.println("Adding Item");

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
		item = super.change(item);
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
	
	// will save txt document in C:\Users\jacob\git\PriceWatcherHW4\HW4\bin\pricewatcher\web
	//							 |  not exact  |
	
	protected void save() {
		System.out.println("Save method");
		PrintWriter writer;
//		String path="C:/Users/jacob/git/PriceWatcher/data3.txt";
//		System.out.println(getClass().getResource("data4.txt").getPath());
//		File file = new File(getClass().getResource("data4.txt").getPath());
//		try {
//			//file.createNewFile();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		try {
		    writer = new PrintWriter(getClass().getResource("data4.txt").getPath());
			JSONArray array  = new JSONArray();
			for(int i=0;i< listModel.getSize();i++) {
				Item e = listModel.get(i);
				array.put(e.toJson());
			}
//			System.out.println(array.toString());
			writer.write(array.toString());
			writer.flush();
			writer.close();
			System.out.println("File Updated");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	protected void load() {
//		File file = new File ("/Users/Mildred/git/PriceWatcher/HW 3");
//		Item localItem = this;
		System.out.println("load method");
		try(BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("data4.txt").getPath()))){
			JSONTokener tokener = new JSONTokener(reader);
			JSONArray array = new JSONArray(tokener);
			for(int i = 0; i < array.length(); i++) {
				Item item = Item.fromJson(array.getJSONObject(i));
				super.Add(item);
									// more code needed?
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
}
