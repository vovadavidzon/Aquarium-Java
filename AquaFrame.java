package aquarium3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * The aquarium Frame class which extends the JFrame class and implements ActionListener.
 * 
 * @version 1.0  26/3/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class AquaFrame extends JFrame implements ActionListener{
	private JMenuItem Image,Blue,None,Exit,Help,momento,saveObjectState,restoreObjectState;
	private JMenu file,edit,help;
	private JFrame frame;
	private AquaPanel panel; 
	private CareTaker carTakerMemento; // for memento design pattern
	
	/**
	 * constructor which creates the main frame and sets the menu bar
	 */
	AquaFrame() { 
		frame = new JFrame("my Aquarium"); //Set title
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1400,800); //size
		frame.setVisible(true); 
		frame.setLocationRelativeTo(null);//open the window in the center of the screen
		
		panel = new AquaPanel();
		
		//-------- Setting the Menu bar --------//
		JMenuBar menu = new JMenuBar();      
		 
		file=new JMenu("File");    
		edit=new JMenu("Background");  
		momento=new JMenu("Momento");
		help=new JMenu("Help");
		
		Image = new JMenuItem("Image");    
		Blue = new JMenuItem("Blue");    
		None = new JMenuItem("None");    
		
		Exit = new JMenuItem("Exit");  
		Help = new JMenuItem("Help");
		
		carTakerMemento = new CareTaker ();
		saveObjectState=new JMenuItem("Save Object State");
		restoreObjectState=new JMenuItem("Restore Object State");
		
		menu.add(file);menu.add(edit);menu.add(momento);menu.add(help);
		edit.add(Image);edit.add(Blue);edit.add(None); 
		momento.add(saveObjectState);momento.add(restoreObjectState);
		file.add(Exit);
		help.add(Help);

		//----- Adding to the Frame -------//
		frame.add(menu);  
		frame.setJMenuBar(menu);  
		frame.add(panel.buttonsPanel,BorderLayout.SOUTH);
		frame.add(panel,BorderLayout.CENTER);
		//frame.pack();
		
		//------ refresh ------//
		frame.invalidate();
		frame.validate();
		frame.repaint();
		
		//--------- create events ----------//
		Exit.addActionListener(this);
		Help.addActionListener(this);
		None.addActionListener(this);
		Blue.addActionListener(this);
		Image.addActionListener(this);
		saveObjectState.addActionListener(this);
		restoreObjectState.addActionListener(this);
	}
	
    /**
     * The function which responsible for the actions of the buttons
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == Exit)   //if user pessed on Exit
			System.exit(0);
		
		else if(e.getSource() == Image) {  //if user pessed on Image
			
			if (panel.ScrollPane != null) {
				panel.remove(panel.ScrollPane);
			}
		    try{    
		    	String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
		    	
		    	//you can choose between 3 images : aquarium1/aquarium2/aquarium3.
		    	panel.image = ImageIO.read(new File(CurrentPath +"\\src\\aquarium3\\aquariumIMAGES\\aquarium2.jpg"));
		    }
		    catch (IOException e1){
		    	UIManager.put("OptionPane.minimumSize",new Dimension(400,50)); //setting the preferred size of JOptionPane
		    	JOptionPane.showMessageDialog(null,"There is a problem with finding the background image"
		    			,"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
		    }
			
		}
		else if(e.getSource() == Blue) {//if user pessed on Blue
			if (panel.ScrollPane != null) {
				panel.remove(panel.ScrollPane);
			}
			
			panel.image=null;
		    panel.setBackground(Color.blue);
		    //--- Refresh ---//
			panel.revalidate();
			panel.repaint();
		}	
		else if(e.getSource()==None) { //if user pessed on None
			if (panel.ScrollPane != null) {
				panel.remove(panel.ScrollPane);
			}
			
			panel.image=null;
			Color color = UIManager.getColor ( "Panel.background" );//getting default color
			panel.setBackground(color);
			//--- Refresh ---//
			panel.revalidate();
			panel.repaint();

		}
		else if(e.getSource()==Help) { //if user pessed on Help
		    // create a jframe
		    JFrame frame = new JFrame("JOptionPane showMessageDialog example");

		    // show a joptionpane dialog using showMessageDialog
		    JOptionPane.showMessageDialog(frame,
		        "Home Work 3 \nGUI @ Threads",
		        "Message",
		        JOptionPane.INFORMATION_MESSAGE);
		    System.exit(0);
		  }
		else if(e.getSource() == saveObjectState) {
			
			if(panel.animalsSet.size() > 0 || panel.plantsSet.size() > 0) {
				SaveObjectDialog saveDialog=new SaveObjectDialog(panel,carTakerMemento);
			}
			else {
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"There are no plants or animals in the aquarium");
			}
		}
		else if(e.getSource()==restoreObjectState){
			
			if(panel.animalsSet.size() > 0 || panel.plantsSet.size() > 0) {
				RestoreObjectState restoreDialog = new RestoreObjectState(panel,carTakerMemento);
			}
			else {
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"There are no plants or animals in the aquarium");
			}
		}
	}

	/**
	 * The Main function 
	 * @param args
	 */
	public static void main(String[] args) {    
		AquaFrame frame = new AquaFrame();
	}
}
