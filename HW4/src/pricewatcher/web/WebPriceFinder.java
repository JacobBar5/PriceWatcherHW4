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
		  Pattern utep = Pattern.compile("<span style=\"color: blue\">(\\$)(\\d+\\.+\\d+)</span>"); // </span>
		  Matcher m; 

		  while ((line = in.readLine()) != null) {	  
			  //URLConnection urlc=url.openConnection();
			  m = utep.matcher(line);
			  if (m.find()) {
			        output = m.group(4);
			        return Float.valueOf(output);
			    }
			  
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
	
//	 Samsung Monitor:	https://www.bestbuy.com/site/samsung-ue590-series-28-led-4k-uhd-monitor-black/5484022.p?skuId=5484022
// - Security Lights:	https://www.amazon.com/dp/B078P8LKTX/?coliid=I3KZEVBEU26Y7H&colid=7FH4WLYA9ET4&psc=0&ref_=lv_ov_lig_dp_it
//   Google Home Hub: 	https://www.ebay.com/itm/Google-Home-Hub-with-Google-Assistant-GA00516-US-w-Nest-Wi-Fi-Video-Doorbell/323561077867?_trkparms=5373%3A5000006400%7C5374%3ATech%7C5079%3A5000006400
//	 Heated Blanket:	https://www.amazon.com/dp/B0088AHJ76/ref=cm_gf_aQU_i6_d_c0_qd0_______________________ONArFiuxAeBGVF6YpPsi
//	 Utep Processor:	http://www.cs.utep.edu/cheon/cs3331/homework/hw4/
	
	
//	public void validVer() {
//		if(isValid(Item.getURL()) != true)
//			JOptionPane.showMessageDialog(null, "Invalid page");
//		else
//			;
//	}
	}
	 

/* 		old code.

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
