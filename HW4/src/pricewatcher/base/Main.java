/**
* A dialog for tracking the price of an item.
*
* @author Nusrat Sarmin
* @author Mildred Brito
* @author Jacob Barberena
*/

package pricewatcher.base;

import java.awt.BorderLayout;

import pricewatcher.web.*;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pricewatcher.base.Item;

/**
 * Main method of program that initializes the UI
 */
@SuppressWarnings("serial")
public class Main extends JFrame {

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(500, 800);
      
    /** Special panel to display the watched item. */
    private ItemView itemView;
      
    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");
    /**	Item manages that sets new item information	*/
    private FileItemManager items;
    /**	Initializes the Item types	*/
    private Item item ;
    /**	Sets the list of items into a JList type	*/
    private JList<Item> list; 
    /**	Scroll pane to scroll the item list when user adds a lot of items	*/
    private JScrollPane scroll;
    
    /**	Initializes the 2 different menu bars	*/
    private JMenuBar mb;
    private JMenuBar rc;
    
    /**	Initializes the different menu headers	*/
    private JMenu menu;
    private JMenu menu1;
    private JMenu menu2;
	private JMenu rcmenu;
	
	/** Initializes the different menu items under each heading	*/
    private JMenuItem i1;
    private JMenuItem i2;
    private JMenuItem i3;
    private JMenuItem i4;
    private JMenuItem i5;
    private JMenuItem i6;
    private JMenuItem i7;
    private JMenuItem i8;
    private JMenuItem i9;
    
    /**	Initializes the different buttons that will execute certain functions in the application	*/
    private JButton CheckPrice;
    private JButton AddItem;
    private JButton search;
    private JButton skip;
    private JButton forward;
    private JButton RemoveItem;
    private JButton Launch;
    
    /**	Initializes the panel of items to be displayed and set in the application	*/
    private JPanel jPanel;
//    ==============================
//    public String url2 = Item.urlS2NS();
//    ==============================
    
	public void createJList() {
		items = new FileItemManager();
		list = new JList<Item>(items.getList());
        list.setCellRenderer(new ItemRenderer());
        scroll= new JScrollPane(list);
        add(scroll, BorderLayout.CENTER);
	}
    
    
    /**	Sets a default for images	*/
    private final static String IMAGE_DIR = "/image/" ;
    /** Create a new dialog. */
    public Main() {
    	this(DEFAULT_SIZE);
    }
    /** Creates a new dialog of the given screen dimension. 
     * @param dim this parameter helps set the size of the app window */
    public Main(Dimension dim) { 	
        super("PriceWatcher");
        setSize(dim);
        configureUI();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }     
    /** Callback to be invoked when the refresh button is clicked. 
     * Find the current price of the watched item and display it 
     * along with a percentage price change. 
     * @param ActionEvent event parameter that */ 
    private void refreshButtonClicked(ActionEvent event) {
    	item.setCurrentPrice();
    	itemView.repaint();
    	showMessage(String.format("Current price:$%.2f \n", item.getCurrentPrice()));
    }
    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. 
     * @throws URISyntaxException 
     * @throws IOException */
    private void viewPageClicked() { 
    	String urlT = item.getURL();	
    	
    	if (urlValid(urlT)) { // Desktop.isDesktopSupported()
            // Windows
            try {
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(new URI(urlT));
			} catch (Exception e) {
				e.printStackTrace();
			} 
//            catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
        } else {
            ;
        	Runtime runtime = Runtime.getRuntime();
        	
           String[] args = { "osascript", "-e", "open location \"" +item.getURL()+ "\"" };
            try {
                Process process = runtime.exec(args);
            }
            catch (IOException e) {
            }
        }
    	showMessage("View clicked!");
    }
    
    /* 
    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item.
     
    private void viewPageClicked(){
    	showMessage("Item Page Clicked");
    	try {
    	Desktop desktop = Desktop.getDesktop();
    	desktop.browse(new URI(this.itemURL()));
    	}catch(Exception e) {;}
    }
    */
    
    private boolean urlValid(String urlstr) {
    		try {
    			new URL(urlstr).toURI();
    			return true;
    		}
    		catch(Exception e) {
    			return false;
    			}
    		}

	/** Configures the UI for the Price Watcher App */
    private void configureUI() {
        setLayout(new BorderLayout());
        Jmenubar();
//       RClickmenubar();
//        JPanel board = new JPanel();
//        board.setBorder(BorderFactory.createCompoundBorder(
//        	BorderFactory.createEmptyBorder(10,16,0,16),
//        	BorderFactory.createLineBorder(Color.GRAY)));
//        board.setLayout(new GridLayout(1,1));
//      items.add("LED security light","https://www.amazon.com/dp/B078P8LKTX/?coliid=I3KZEVBEU26Y7H&colid=7FH4WLYA9ET4&psc=0&ref_=lv_ov_lig_dp_it", (float)35.99 );
//       items.add("Samsung VHD LED monitor","https://www.bestbuy.com/site/samsung-ue590-series-28-led-4k-uhd-monitor-black/5484022.p?skuId=5484022", (float)100.55 );
        createJList();
        addButtons();

        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
    }
    /**
     * Sets all the different menu bars for the different menu categories and initializes the
     *  menu categories into the menu bar and contains action listners for specific menu headings
     */
    public void Jmenubar(){
	mb=new JMenuBar(); 		
        menu=new JMenu("App"); 
        menu1=new JMenu("Item");
        menu2=new JMenu("Sort");
	    
        i1=new JMenuItem("About", new ImageIcon("src/image/help-icon.png"));    
        i2=new JMenuItem("Check prices", new ImageIcon("src/image/ok.png")); 
        i3=new JMenuItem("Add item", new ImageIcon("src/image/plus.png"));  
        i4=new JMenuItem("Search", new ImageIcon("src/image/search.png"));  
        i5=new JMenuItem("Select first", new ImageIcon("src/image/skip.png"));  
        i6=new JMenuItem("Select last", new ImageIcon("src/image/forward.png"));  
        i7=new JMenuItem("Selected", new ImageIcon("src/image/play.png"));  
       
        menu.add(i1); menu.addSeparator(); menu.add("Exit");
        menu1.add(i2);menu1.add(i3); menu1.addSeparator();
        menu1.add(i4);menu1.add(i5); menu1.add(i6);
        menu1.addSeparator();menu1.add(i7);
	menu2.add("Added oldest");menu2.add("Added newest"); menu2.addSeparator();
	menu2.add("Name ascending");menu2.add("Name descending"); menu2.addSeparator();
	menu2.add("Price change (%)"); menu2.add("Price low ($)"); menu2.add("Price high ($)");
       
        this.setJMenuBar(mb);
        mb.add(menu);
        mb.add(menu1);
        mb.add(menu2);
        
        i3.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			JTextField itemName = new JTextField();
    			JTextField itemUrl = new JTextField();
    			JTextField itemPrice = new JTextField();
    			
    			String options[] = {"Computer", "Monitor", "Television", "Other"};
    			
    			JComboBox<String> itemGroup = new JComboBox<String>(options);  
    			
    			Object[] itemInformation = {
    				"Name:", itemName,
    				"URL:", itemUrl,
    				"Price", itemPrice,
    				"Group", itemGroup,
    			};
    			
    			JOptionPane.showConfirmDialog(null, itemInformation);

    				String name = itemName.getText();
    				String url = itemUrl.getText();
    				String price = itemPrice.getText();
    				try {
    					float priceNum=Float.parseFloat(price);
    					items.add(name,url,priceNum);
    				}catch (NumberFormatException e1) {
    					e1.printStackTrace();
    					items.add(name,url,(float)0);
    				}
        	}
    	});
        /*
        // "Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException"
        i2.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			item.setCurrentPrice();
    	    	itemView.repaint();
    	    	showMessage(String.format("Current price:$%.2f \n", item.getCurrentPrice()));
        	}
    	});
    	*/
        i1.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			JOptionPane.showMessageDialog(null, "By: Nusrat Sarmin, Midred Brito, and Jacob Barberena");
        	}
    	});
    }
    /**
     * Sets the menu bar that should appear when an item is right clicked
     */
    /*
	public void RClickmenubar(){ 
		rc = new JMenuBar();
        rcmenu=new JMenu(""); 
	       
        i2=new JMenuItem("Check prices", new ImageIcon("src/image/ok.png"));
        i8 = new JMenuItem("View", new ImageIcon("src/image/web.png"));
        i3=new JMenuItem("Add item", new ImageIcon("src/image/plus.png"));
        i9=new JMenuItem("Remove", new ImageIcon("src/image/minus.png"));
        
        rcmenu.add(i2); rcmenu.add(i8); rcmenu.add(i3); rcmenu.add(i9);
        
        this.setJMenuBar(rc);
        rc.add(rcmenu);
    }
    */
	/**
	 * Initializes all the buttons for the tool bar, images for each of the buttons, and
	 *  houses event handles for some buttons, specifically for the add and remove buttons
	 */
    private void addButtons() {
    	JToolBar toolBar = new JToolBar();
    	JButton button=new JButton("CheckPrice");
    	button=new JButton("AddItem");
    	button=new JButton("search");
    	button=new JButton("skip");
    	button=new JButton("forward");
    	button=new JButton("RemoveItem");
    	button=new JButton("Launch");
    	
    	button.addActionListener(action->{
    		
    	}); 
        CheckPrice = new JButton(new ImageIcon(getImage("check.png")));
        CheckPrice.addActionListener(e -> CheckPriceClick());
        
        AddItem = new JButton(new ImageIcon(getImage("plus.png")));

        search=new JButton(new ImageIcon(getImage("search.png")));
        skip=new JButton(new ImageIcon(getImage("left-chevron.png")));
        forward=new JButton(new ImageIcon(getImage("right-chevron.png")));
        
        RemoveItem = new JButton(new ImageIcon(getImage("minus.png")));
        
        Launch = new JButton(new ImageIcon(getImage("web.png")));
        Launch.addActionListener(e -> viewPageClicked());
        
        toolBar.add(CheckPrice);
        toolBar.add(AddItem);
        toolBar.add(search);
        toolBar.add(skip);
        toolBar.add(forward);
        toolBar.add(RemoveItem);
        toolBar.add(Launch);
        toolBar.addSeparator();
        this.add(toolBar,BorderLayout.NORTH);
	    
        //Added Action Listener for AddItem
    	AddItem.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			JTextField itemName = new JTextField();
    			JTextField itemUrl = new JTextField();
    			JTextField itemPrice = new JTextField();
    			
    			String options[] = {"Computer", "Monitor", "Television", "Other"};
    			
    			JComboBox<String> itemGroup = new JComboBox<String>(options);  
    			
    			Object[] itemInformation = {
    				"Name:", itemName,
    				"URL:", itemUrl,
    				"Price", itemPrice,
    				"Group", itemGroup,
    			};
    			
    			JOptionPane.showConfirmDialog(null, itemInformation);

				String name = itemName.getText();
				String url = itemUrl.getText();
				String price = itemPrice.getText();
			
			items.add(name,url,Float.valueOf(price));	
			
    		}
    	});
    	
        //Temporary Action Listener for RemoveItem
    	RemoveItem.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			// needs to be later fixed to remove the SELECTED Item
    			items.Remove();
    		}
    	});
    	
    	// needs to be further fixed
    	items.addMouseListener = (new MouseAdapter() {
        	public void mouseClicked(MouseEvent mouseEvent) {
        		if(mouseEvent.getButton() == MouseEvent.BUTTON1){ ; }
        		if(mouseEvent.getButton() == MouseEvent.BUTTON2){ ; }
        		if(mouseEvent.getButton() == MouseEvent.BUTTON3){
//        			RClickmenubar(); 
        			}
        	}
    	});
    	
//		items.addMouseListener(event  -> {
//			RClickmenubar();
//	       });
    }
    /**
     * Adds an item to the list and repaints is to display nely added item
     */
	public void AddItem(){
	     items.Add(item);
    	 list.repaint();
    }
	/**
	 * Searches set directory of a saved image that will be used
	 * @param file gets file name of the where image is stored
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
	
    /** Show briefly the given string in the message bar. */
    private void showMessage(String msg) {
        msgBar.setText(msg);
        new Thread(() -> {
        	try {
				Thread.sleep(3 * 1000); // 3 seconds
			} catch (InterruptedException e) {
			}
        	if (msg.equals(msgBar.getText())) {
        		SwingUtilities.invokeLater(() -> msgBar.setText(" "));
        	}
        }).start();
    }
    /**
     * Updates the prices of all the items in the list and repaints 
     * the list to show the updated item prices
     */
	public void CheckPriceClick() {
		items.setAllPrices();
		list.repaint();
}
	// later use to get the url of each individual item
//    public String getURL() {
////    	Item url = new Item;
//		return Item.getURL();
//    }
	   
	/**
	 * Excecutes the Main() constructor
	 * @param args Standard Java parameters
	 */
    public static void main(String[] args) {
        new Main();
    }
}
