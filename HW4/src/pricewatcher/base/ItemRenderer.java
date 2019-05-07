/**
* @author Nusrat Sarmin
* @author Mildred Brito
* @author Jacob Barberena
*/

package pricewatcher.base;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;


@SuppressWarnings("serial")

/**
 * Initializes a renderer that extends ItemView so that its able to display multiple items at once  
 * @return the changed color background depending if the item is selected 
 */
public class ItemRenderer extends ItemView implements ListCellRenderer<Item> {
	public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected, boolean cellHasFocus) { 
		
		setItem(item);
		
		if(isSelected) {
			setBackground(Color.white);
			setBackground(Color.LIGHT_GRAY);
		}
		else {
			setBackground(Color.white);
			setBackground(Color.white);
		}
		return this;
	}
}

