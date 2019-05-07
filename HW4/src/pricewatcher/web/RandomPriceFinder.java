/**
* @author Nusrat Sarmin
*/

package pricewatcher.web;

import pricewatcher.base.*;

/**
 * This class is used to generate a random number to simulate a price
 */
public class RandomPriceFinder extends pricefinder{

	@Override
	public float getrandomPrice() {
		float Max=(float) 110.00;
		float Min=(float) 30.00;	
		return ((float)Math.random()*(Max-Min))+Min ;
	}
}