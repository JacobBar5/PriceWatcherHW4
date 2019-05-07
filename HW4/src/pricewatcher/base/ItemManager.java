/**
* @author Nusrat Sarmin
* @author Mildred Brito
* @author Jacob Barberena
*/

package pricewatcher.base;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



/**
 * Manages Item and all information related to them
 */
public class ItemManager {
	/**	Initializes a default list model for the items */
	protected DefaultListModel<Item> listModel;
	/**	Initializes a mouse adapter  */
	public MouseAdapter addMouseListener;
	/** 
	 * Sets the default list of items
	 */
	public ItemManager() {
		listModel=new DefaultListModel();
	}
	/**	Gets the list of items	*/
	public DefaultListModel<Item> getList(){
		return this.listModel;
	}
	/**	Checks if the list of items is empty	*/                                      
	public boolean isEmpty() {
		if(listModel.isEmpty())
			return true;
		else
			return false;
	}
//	public void update(){
//		pricewatcher.web.FileItemManager.load();
//	}
	
	/**	Adds an item to the list	*/
	protected void Add(Item item) {
		listModel.addElement(item);
	}
	/** Obtains information from FileItemManager and send it to the list */
	public Item add(String name, String url, float price) {
		Item item = new Item(name, url, price);
		Add(item);
		return item;
	}
	
	
	/**	Displays the info of an item	*/
	public void contains(Item item) {
		listModel.contains(item);
	}
	/**	Gets the current price of the Item	*/
	public void getCurrentPrice() {
		listModel.getClass();
	}
	/**	
	 * Sets the current price of just a specific item in the list
	 * @param the specific item number in the list
 	 */
	public void setCurrentPrice(int idx) {
		listModel.get(idx).setCurrentPrice();
	}
	/**	Sets/updates the current price for all the items 	*/
	public void setAllPrices(){
		for (int i=0; i<listModel.getSize(); i++){
			listModel.get(i).setCurrentPrice();
		}
	}
	
	/**	
	 * Removes an item from the list
	 * @return deleted item at the top of the list 
 	 */
	public Item Remove() {
		
		/* listModel.addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Hello you selected me!  "
            + dataList.getSelectedValue().toString());
    }
});
		 * 
		 */
			return listModel.remove(0);		
	}
	
	/** Changes an item in the list */
	
	public Item change(Item item) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**	
	 * Gets the size of any item in the list
	 * @param the info for that item
 	 */
	public void size(Item item) {
		listModel.getSize();	
	}

}
	

