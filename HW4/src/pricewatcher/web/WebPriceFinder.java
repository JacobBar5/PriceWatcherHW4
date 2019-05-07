package pricewatcher.web;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;

import pricewatcher.base.*;



public class WebPriceFinder extends pricefinder{
	
	public float getPrice(String urlstr) {
		String output = "";

		HttpURLConnection con = null;
		try {
		  if (isValid(urlstr)){
			  URL url = new URL(urlstr);
		  con = (HttpURLConnection) url.openConnection();
		  con.setRequestProperty("User-Agent", "...");
		  String encoding = con.getContentEncoding();
		  if (encoding == null) { 
			  encoding = "utf-8"; 
			  }
		  InputStreamReader reader = null;
		  if ("gzip".equals(encoding)) { // gzipped document?
		     reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
		  } 
		  else {
		     reader = new InputStreamReader(con.getInputStream(), encoding);
		  }
		  BufferedReader in = new BufferedReader(reader);
		  String line;
		  Pattern bestBuy = Pattern.compile("Your price for this item is (\\$)(\u003c!-- -->)(\\d+\\.\\d+)");
		  Pattern amazon = Pattern.compile(".a-size-medium a-color-price priceBlockBuyingPriceString\">(\\$)(\\d+\\.\\d+)");
		  Pattern ebay = Pattern.compile("<span class=\"notranslate\" id=\"prcIsum\" itemprop=\"price\"\\s*style=\"\" content=\"(\\d+\\.\\d+)");
		  Matcher m; 

		  while ((line = in.readLine()) != null) {	  
			  //URLConnection urlc=url.openConnection();
			   m = bestBuy.matcher(line);
			  if (m.find()) {
			        output = m.group(3);
			        return Float.valueOf(output);
			    }
			  
			  m = amazon.matcher(line);
			  if (m.find()) {
			        output = m.group(2);
			        return Float.valueOf(output);
			  }
			  
			  m = ebay.matcher(line);
			  if (m.find()) {
			        output = m.group(1);
			        return Float.valueOf(output);
			  } 
			  
			  else {
			  }
		
		  } 
	 }
}

		catch (IOException e) { e.printStackTrace(); 
		} finally {
		  if (con != null) {  con.disconnect(); }
		}
		if(output.equals("")) {
			JOptionPane.showMessageDialog(null, "Price was not found");
			return (float) -1.00;
		}
		return Float.parseFloat(output);
	}

	private boolean isValid(String urlstr) {
		try {
			new URL(urlstr).toURI();
			return true;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid page");
			return false;
		}
		}
	
//	https://www.bestbuy.com/site/samsung-ue590-series-28-led-4k-uhd-monitor-black/5484022.p?skuId=5484022
	
	
//	public void validVer() {
//		if(isValid(Item.getURL()) != true)
//			JOptionPane.showMessageDialog(null, "Invalid page");
//		else
//			;
//	}
	}
	 

/* 		old code

package pricewatcher.web;

import java.io.IOException;
import java.net.URL;

import pricewatcher.base.pricefinder;

public class WebPriceFinder extends pricefinder {	
	public float findPrice(String url) {
		try {
			URL realURL = new URL(url);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			String encoding = 
			
			
		float Max = (float)110.00;
		float Min = (float)30.00;	
		return ((float)Math.random()*(Max-Min))+Min ;
		
		
		}catch (IOException e) {e.printStackTrace();}
		}finally {
			if(con != null) {con.dissconnect()}
		}
	}
}

*/
