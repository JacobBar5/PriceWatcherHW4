/**
* @author Nusrat Sarmin
* @author Mildred Brito
* @author Jacob Barberena
*/

package pricewatcher.base;
import java.util.*;

import org.json.JSONObject;

import pricewatcher.web.WebPriceFinder;

import java.text.*;

public class Item {
	/** Initializes a String for the name of the item	 */
	private String Name;
	/** Initializes a String for the url	 */
	private static String URL;						// changed to static, see if that makes further problems
	/** Initializes a Date type to get when the item was added	*/
	private Date date;
	/** Initializes a float of the items's initial price	 */
	private float initialPrice;
	/** Initializes a float of the current price of the item	 */
	private float currentPrice;
	/** Initializes a float of the change percentage of the price	 */
	private float change;
	/** Sets the format of the date added	 */
	private DateFormat day=new SimpleDateFormat("MM/dd/yy");
	/** Initializes a String of when the item was added */
	private String day1;
	/** Calls pricefinder to get a simulated price that changes	 */
	private WebPriceFinder webValue=new WebPriceFinder();
	/**
	 * Sets the given name and url of the item and sets other needed fields like the date added
	 *  and the price change
	 * @param Name the name of the item
	 * @param URL the URL link of the item
	 */
	public Item(String Name, String URL, float Price){
		
	    this.Name=Name;
	    this.URL=URL;
	    this.currentPrice = webValue.getPrice(getURL());
	    initialPrice = webValue.getPrice(getURL());
	    currentPrice=initialPrice;
	    change=getChange();
	    date=new Date();
	    day1=day.format(date);
	    
	}
	/**
	 * Gets the item's name in a String type
	 * @return a String of the Item's name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * Gets the URL of an item in a String form
	 * @return a String of the item's URL
	 */
	public String getURL() {
		return URL;
	}
	/**
	 * Gets the initial price of an item
	 * @return a float of the item's initial price
	 */
	public float getInitialPrice() {
		return initialPrice;
	}
	/**
	 * Gets the current price of an item
	 * @return a float of the current item's price
	 */
	public float getCurrentPrice() {
		return currentPrice;
	}
	/**
	 * Gets the percent change of an item
	 * @return float of the percent change an item had
	 */
	public float getChange() {
		change=(currentPrice*100)/initialPrice-100;
		return change;
	}
	/**
	 * Gets the date of when an item was added
	 * @return the String of when the item was added
	 */
	public String getDate() {
		return day1;
	}	
	/**
	 * Gets the new simulated price for an item
	 * @return a randomly simulated price to simulate a price change
	 */
	public void setCurrentPrice() {
		currentPrice=webValue.getPrice(getURL());
	}
	
	public static String urlS2NS() {
		return URL;
	}
	
	public JSONObject toJson() {
    	Map<String, Object> map = new HashMap<>();
    	map.put("name", Name);
    	map.put("url", URL);
    	map.put("currentPrice", currentPrice);
    	
    	return new JSONObject(map);
    }
    // item.toJson().toString() will give a JSON string of the form:
    // { "name": "LED Monitor", "currentPrice": 30.99, ... }

    public static Item fromJson(JSONObject obj) {
        String name = obj.getString("name");
        String url = obj.getString("url");
        float currentPrice = (float) obj.getDouble("currentPrice");
        
        Item item = new Item(name, url, currentPrice);
        
        return item;
    }
}
