/**
* @author Nusrat Sarmin
* @author Mildred Brito
* @author Jacob Barberena
*/

package pricewatcher.base;
//Name:Nusrat Sarmin
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/** A special panel to display the detail of an item. */
@SuppressWarnings("serial")
public class ItemView extends JPanel {
    
	/** Interface to notify a click on the view page icon. */
	public interface ClickListener {
		
		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
	}
	
	/** Directory for image files: src/image in Eclipse. */
	private final static String IMAGE_DIR = "/image/";
	/**	Initializes the Color class to be used later when setting the color of an item	*/
    public Color color;   
	/** View-page clicking listener. */
    private ClickListener listener;
    
    /** Create a new instance. */
    private Item item;
    
    /** Sets the newest instance of Item to one called/set by another method */
    public void setItem(Item item) {	
    	this.item=item; 
    }
    
    /**	Creates an initial instance of Item View	*/
    public ItemView() {
    	//this(new Item("LED security light","\"https://www.amazon.com/dp/B078P8LKTX/?coliid=I3KZEVBEU26Y7H&colid=7FH4WLYA9ET4&psc=0&ref_=lv_ov_lig_dp_it", (float)300.54));
    	this(null);
    }
    
    /**
     * Sets the view of a preset item the user provides
     * @param item for when the user provides a pre-set item
     */
	public ItemView(Item item) {
    	this.item=item;
    	setPreferredSize(new Dimension(100, 160));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if (isViewPageClickedG(e.getX(), e.getY()) && listener != null) {
            		listener.clicked();
            	}
            }
        });
    }
		
    /** Set the view-page click listener. 
     * @param listner listens for a click from the mouse the user does
     */
    public void setClickListener(ClickListener listener) {
    	this.listener = listener;
    }
   
    /** Overridden here to display the details of the item. 
     * @param g a default variable of the Graphics type
     */
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        //Dimension dim = getSize();
        int x = 20, y = 50;
        g.drawImage(getImage("icon1.png"),20,20,this);
        
        g.setFont (new Font(this.item.getName(), Font.PLAIN, 12));
        g.drawString("Name: ",x,y);
        
        x+=50;
        g.setFont (new Font(this.item.getName(), Font.BOLD, 12));
        g.drawString(this.item.getName(),x,y);
        x=x-50; y+=20;
        
        g.setFont (new Font(this.item.getName(), Font.PLAIN, 12));
        g.drawString("URL: ",x,y);
        x+=50;
        g.drawString( this.item.getURL(),x,y);
        x=x-50; y += 20;
        
        g.setColor(Color.black);
        g.drawString("Price: ",x,y);
        x+=50;
        g.setColor(Color.blue);
        g.drawString(String.format("$%.2f \n", this.item.getCurrentPrice()),x, y);
        x=x-50; y += 20;
        
        g.setColor(Color.black);
        g.drawString("Change:   ",x,y);
        
        if(this.item.getChange()<0) {
        	g.setColor(Color.green);
        }else {
        	g.setColor(Color.red);
        }
        x += 50;
        g.drawString(String.format("%.2f%%\n", item.getChange()),x, y);
        x =x-50; y+=20;
        g.setColor(Color.black);
        
        g.drawString(String.format("Added:		%s($%.2f)\n", item.getDate(),  item.getInitialPrice()),x,y) ;
       }
       
    /** Return true if the given screen coordinate is inside the viewPage icon. 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if within the boundaries 
     */
    private boolean isViewPageClickedG(int x, int y) {
    	if (x <0 || y <0 ) {
    		return false;
    	}	
    		return true;   	
    	// 	return new Rectangle(20, 20, 20, 20).contains(x,  y);
    }  
    
    /** Return the image stored in the given file.
     * @param file String of the image file directory 
     * @return the image that has been requested
     */
    public Image getImage(String file) {
        try {
        	URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
