package aquarium3;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 * The aquarium panel class which extends the JPanel class and implements ActionListener.
 * 
 * @version 1.0  26/3/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */

public class AquaPanel extends JPanel implements ActionListener{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addAnimal,sleep,wakeup,reset,food,info,exit,addplant,Duplicate_Animal,decorator;
	protected JPanel buttonsPanel; //The panel for the main buttons in the bottom.
	protected HashSet<Swimmable> animalsSet =new HashSet<Swimmable>(); //hash set for swimmable
	protected HashSet<Immobile> plantsSet=new HashSet<Immobile>();  //hash set for immotible
	protected static BufferedImage image;// Image for background image.
	private Iterator<Swimmable> iterator_animals;
	private Iterator<Immobile> iterator_plants;
	protected JScrollPane ScrollPane;
	protected boolean InfoFlag = false,WormFlag=false;
	private Singleton WormInstance=null; //instance of the worm
	private JDialog dialogTable,changeColorDialog,colorChooser;
	
	/**
	 * constructor which creates the main Panel and sets the buttons Panel.
	 */
	public AquaPanel(){
		
		//setting main Panel
		super();
		setLayout(new BorderLayout());

		//setting buttons Panel 
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1,10,0,0));
		
		addAnimal =new JButton("Add Animal");
		addAnimal.setBackground(Color.LIGHT_GRAY);
		addAnimal.setFocusable(false);
		addAnimal.setFont(new Font("Tahoma",Font.BOLD,12));
		addplant =new JButton("Add Plant");
		addplant.setBackground(Color.LIGHT_GRAY);
		addplant.setFocusable(false);
		addplant.setFont(new Font("Tahoma",Font.BOLD,12));
		Duplicate_Animal =new JButton("Duplicate Animal");
		Duplicate_Animal.setBackground(Color.LIGHT_GRAY);
		Duplicate_Animal.setFocusable(false);
		Duplicate_Animal.setFont(new Font("Tahoma",Font.BOLD,12));
		decorator =new JButton("Decorator");
		decorator.setBackground(Color.LIGHT_GRAY);
		decorator.setFocusable(false);
		decorator.setFont(new Font("Tahoma",Font.BOLD,12));
		sleep =new JButton("Sleep");
		sleep.setBackground(Color.LIGHT_GRAY);
		sleep.setFocusable(false);
		sleep.setFont(new Font("Tahoma",Font.BOLD,12));
		wakeup =new JButton("Wake up");
		wakeup.setBackground(Color.LIGHT_GRAY);
		wakeup.setFocusable(false);
		wakeup.setFont(new Font("Tahoma",Font.BOLD,12));
		reset =new JButton("Reset");
		reset.setBackground(Color.LIGHT_GRAY);
		reset.setFocusable(false);
		reset.setFont(new Font("Tahoma",Font.BOLD,12));
		food =new JButton("Food");
		food.setBackground(Color.LIGHT_GRAY);
		food.setFocusable(false);
		food.setFont(new Font("Tahoma",Font.BOLD,12));
		info =new JButton("Info");
		info.setBackground(Color.LIGHT_GRAY);
		info.setFocusable(false);
		info.setFont(new Font("Tahoma",Font.BOLD,12));
		exit =new JButton("Exit");
		exit.setBackground(Color.LIGHT_GRAY);
		exit.setFocusable(false);
		exit.setFont(new Font("Tahoma",Font.BOLD,12));
	
		//---- adding the buttons to the buttonsPanel ----//
		buttonsPanel.add(addAnimal);
		buttonsPanel.add(addplant);
		buttonsPanel.add(Duplicate_Animal);
		buttonsPanel.add(decorator);
		buttonsPanel.add(sleep);
		buttonsPanel.add(wakeup);
		buttonsPanel.add(reset);
		buttonsPanel.add(food);
		buttonsPanel.add(info);
		buttonsPanel.add(exit);
	
		//--------- create events ----------//
		addAnimal.addActionListener(this);
		addplant.addActionListener(this);
		Duplicate_Animal.addActionListener(this);
		decorator.addActionListener(this);
		sleep.addActionListener(this);
		wakeup.addActionListener(this);
		reset.addActionListener(this);
		food.addActionListener(this);
		info.addActionListener(this);
		exit.addActionListener(this);
	}
	
	/**
	 * A function that paints the main panel.
	 */
	public void paintComponent(Graphics g){	
	 	super.paintComponent(g);
	 	if(this.image != null) { // If user pressed on image button to set the background image.
            Graphics2D g1 = (Graphics2D) g;
            g1.drawImage(this.image,0,0,getWidth(),getHeight(),this);
            repaint();
	 	}	
	 	
	    // Get the iterator
	    iterator_animals = animalsSet.iterator();
	    while(iterator_animals.hasNext()){
		    //draw the animals
		    iterator_animals.next().drawCreature(g);
	    }
	    
	    // Get the iterator
	    iterator_plants = plantsSet.iterator();
	 	while(iterator_plants.hasNext()){
	 		//draw the plants
	 		iterator_plants.next().drawCreature(g);
	 	}
	    
	 	//draw the worm
		if(WormFlag != false){ // check if user press on food button to create the worm
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.red);
			g2.drawArc(getWidth()/2, getHeight()/2-5, 10, 10, 30, 210);
			g2.drawArc(getWidth()/2, getHeight()/2+5, 10, 10, 180, 270);
			g2.setStroke(new BasicStroke(1));
		}
	}
	
    /**
     * The function which responsible for the actions of the buttons
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == addAnimal){//if user pessed on addAnimal
			try{
				if(animalsSet.size()>=5) {
					throw new Exception("The Aquarium can contain only 5 animals!");// the aquarium limits to only 5 swimmable
				}
				else{
					AddAnimalDialog Animaldialog=new AddAnimalDialog(this); // open a dialog to fill the details of the swimmable
				} 
			}
			catch(Exception e1){
				JOptionPane.showMessageDialog(null, e1.getMessage(),"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == addplant) {
			try{
				if(plantsSet.size()>=5) 
					throw new Exception("The Aquarium can contains only 5 plants!"); // the aquarium limits to only 5 swimmable
				else{
					AddPlantDialog Planetdialog=new AddPlantDialog(this); // open to dialog to fill the details of the swimmable
				}
				
			}
			catch(Exception e1){
				 JOptionPane.showMessageDialog(null,e1.getMessage());
			}
			
		}
		else if(e.getSource() == Duplicate_Animal) {
			
			try{
				if(animalsSet.size()>=5)
					throw new Exception("You can't duplicate animals, The Aquarium already contains 5 animals!"); // the aquarium limits to only 5 swimmable
				else if(animalsSet.size()==0)
					throw new Exception("no animals in aquarium to duplicate"); // the aquarium limits to only 5 swimmable
				else{
					AddDuplicateAnimal duplicateDialog=new AddDuplicateAnimal(this);		
				}
			}
			catch(Exception e1){
				 JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
		else if(e.getSource()==decorator){
			try{
				if(animalsSet.size()==0)
					throw new Exception("There are no animals in the aquarium"); //no animals in the aquarium
				else{
					changeColorDialog = new JDialog();
					changeColorDialog.setSize(650,300);
					changeColorDialog.setLayout(new BorderLayout());
					changeColorDialog.setLocationRelativeTo(null);
					changeColorDialog.setTitle("Decorator Design Pattern - Change Color");
					JPanelDecorator cp=new JPanelDecorator(this,changeColorDialog);
					changeColorDialog.add(cp);
					changeColorDialog.setVisible(true);
				}
			}
				catch(Exception e1){
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,15));
					UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
		}
		else if (e.getSource() == sleep){//if user pessed on sleep
			
			iterator_animals= animalsSet.iterator(); 
		 	while(iterator_animals.hasNext()){
		 		iterator_animals.next().setSuspend(); // suspend all the swimmable in the aquarium 
		 	
		 	}
		}
		else if (e.getSource() == wakeup){//if user pessed on wakeup
			iterator_animals= animalsSet.iterator(); 
		 	while(iterator_animals.hasNext()){
		 		iterator_animals.next().setResume(); // cancel the suspend to all swimmable
		 	}
		}
		else if (e.getSource() == reset){//if user pessed on reset
			iterator_animals= animalsSet.iterator(); 
		 	while(iterator_animals.hasNext()){
		 		iterator_animals.next().SetThreadLives(); // cancel the Threads to all swimmable
		 	}
			animalsSet.clear(); //clearing the animals Set
			plantsSet.clear();  //clearing the plants Set
		}
		else if (e.getSource() == food){//if user pessed on food
			
			if(animalsSet.size()!=0) {//Checking that there are animals to feed
				
				Singleton.getInstance();
				WormFlag = true; //Setting the wormflag to true to draw the worm.
				//--- Refresh main panel ---//
				invalidate();
				validate(); 
				repaint();
				
				//Creating CyclicBarrier to the objects.
				CyclicBarrier Barrier = new CyclicBarrier(animalsSet.size());
				
				iterator_animals= animalsSet.iterator(); 
			 	while(iterator_animals.hasNext()){
			 		iterator_animals.next().setBarrier(Barrier); 
			 	}
			}
			
		}
		else if (e.getSource() == info){//if user pessed on info
			
			if(!InfoFlag) {//if user pressed an odd number of times
				
				InfoFlag=true;
				//-----------  Creating the Info table  -------------//
				String column[]={"Animal","Color","Size","Hor.speed","Ver.speed","Eat counter"}; 
				String[][] data = new String[animalsSet.size()+1][column.length];
				
				iterator_animals= animalsSet.iterator(); 
				Swimmable[] animals = animalsSet.toArray(new Swimmable[animalsSet.size()]);
			 		
				for(int i=0; i < column.length;i++) {
					for(int j=0;j<animalsSet.size();j++) {
						
						if (i==0)
							data[j][i] = animals[j].getAnimalName();
						else if(i==1)
							data[j][i] = String.valueOf(animals[j].getColorAnimal());
						else if (i==2)
							data[j][i] = String.valueOf(animals[j].getSize());
						else if (i==3)
							data[j][i] = String.valueOf(animals[j].getHorSpeed());
						else if (i==4)
							data[j][i] = String.valueOf(animals[j].getVerSpeed());
						else if (i==5)
							data[j][i] = String.valueOf(animals[j].getEatCount());
					}
				}
				//calculating the Total of the EatCount;
				int total=0;
			 	while(iterator_animals.hasNext()){
			 		total+=iterator_animals.next().getEatCount(); // cancel the Threads to all swimmable
			 	}

				data[animalsSet.size()][0]= "Total" ;
				data[animalsSet.size()][column.length-1]= String.valueOf(total);
				
			    JTable jt=new JTable(data,column);             
			    ScrollPane=new JScrollPane(jt);    
			    add(ScrollPane);   
			    //--- Refresh the main panel ---//
				invalidate();
				validate();  
			    repaint(); 
			}   
			else {//if user pressed An even number of times
				InfoFlag = false;
				remove(ScrollPane);
				//--- Refresh the main panel ---//
				invalidate();
				validate();  
			    repaint(); 
			}    	
		}
		else if (e.getSource() == exit){//if user pessed on exit
			JFrame ExitFrame = new JFrame ("Exit");
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
			if(JOptionPane.showConfirmDialog(ExitFrame, " You want to Exit  ? ","Exit",JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}			
	    }
	}

	/**
	 * The function which announces that the animal is hungry.
	 * 
	 * @param swimmable - the animal object.
	 */
	public void update(Swimmable swimmable) {

		Thread t = new Thread(new Runnable(){
	        public void run(){
	        	JOptionPane.showMessageDialog(null,swimmable.getAnimalName()+"  ID :  "+swimmable.getID() +" wants to eat!","Notify Food",JOptionPane.PLAIN_MESSAGE);
	        }
	    });
		t.start();
	}
}
