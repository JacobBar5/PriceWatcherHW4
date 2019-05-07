/**
* @author Nusrat Sarmin
* @author Mildred Brito
* @author Jacob Barberena
*/

package pricewatcher.base;
/**
 * This class is used to generate a random number to simulate a price
 */
public class pricefinder {
	public float getrandomPrice() {
		float Max=(float) 110.00;
		float Min=(float) 30.00;	
		return ((float)Math.random()*(Max-Min))+Min ;
	}
}
