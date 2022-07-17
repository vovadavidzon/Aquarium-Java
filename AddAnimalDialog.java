package aquarium3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * The Animal Dialog class which extends the JDialog class and implements ActionListener.
 * 
 * @version 1.0  26/3/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class AddAnimalDialog extends JDialog implements ActionListener{
	private String[] colors = {"Black","Red","Blue","Green","Cyan" 
			                  ,"Orange","Yellow","Magenta","Pink"};
	private String[] names = {"Fish","Jelly Fish"}; 
	private JComboBox<String> CmbAnimalsTypes; // ComboBox Animal types
	private JComboBox<String> CmbColorTypes;  // ComboBox color types
	private JPanel panel;
	private JTextField txtSize,txtVerSpeed,txtHorSpeed,txtFoodFreq;
	private JLabel lbAnimalType,lbAnimalSize,lbHorSpeed,lbVerSpeed,lbColor,lbFoodFreq;
	private JButton submit,cancel; //Buttons
	private AquaPanel objAquaPanel;
	private AbstractSeaFactory seaFactory;
	private SeaCreature animalObj;
	
	/**
	 * constructor which creates the Dialog that creates the Swimmable.
	 */
    AddAnimalDialog(AquaPanel AquaPanel) {  
		//settings AddAnimalDialog
		setTitle("Add Animal Dialog");//Set title
		setSize(450,300); //setting size
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);//Creates the window in the center of the panel
	
		
		objAquaPanel=AquaPanel;

		setPanel() ;//setting the panel of the Dialog 
		add(panel);
		setVisible(true);
    }
    
    /**
     * A function that sets the Panel of the Dialog window.
     */
    public void setPanel() {
    	panel =new JPanel();
    	panel.setLayout(new GridLayout(7,2));
    	
    	lbAnimalType = new JLabel("Enter type:");
    	lbAnimalSize = new JLabel("Enter size (from 20 to 320):");
    	lbHorSpeed = new JLabel("Horizontal speed (from 1 to 10):");
    	lbVerSpeed = new JLabel("Vertical speed (from 1 to 10):");
    	lbColor = new JLabel("Enter color:");
    	lbFoodFreq = new JLabel("food frequency (from 1000 to 3000)ms:");
    	
    	//Initialize ButtonFields
		submit=new JButton("Submit");
		submit.setFocusable(false);
		cancel=new JButton("Cancel"); 	
		cancel.setFocusable(false);
    	
    	//Initialize textFields
    	txtSize = new JTextField();
    	txtHorSpeed = new JTextField();
    	txtVerSpeed = new JTextField();
    	txtFoodFreq = new JTextField();
    	
		//Initialize comboBox
		CmbAnimalsTypes = new JComboBox<String>(names);
		CmbColorTypes = new JComboBox<String>(colors);
		
		//-------- adding to the panel -------//
		panel.add(lbAnimalType);
		panel.add(CmbAnimalsTypes);
		panel.add(lbAnimalSize);
		panel.add(txtSize);
		panel.add(lbHorSpeed);
		panel.add(txtHorSpeed);
		panel.add(lbVerSpeed);
		panel.add(txtVerSpeed);
		panel.add(lbColor);
		panel.add(CmbColorTypes);
		panel.add(lbFoodFreq);
		panel.add(txtFoodFreq);
		panel.add(submit);
		panel.add(cancel);
		
		// creating button events
		submit.addActionListener(this);
		cancel.addActionListener(this);
    }
    
    /**
     * The function which responsible for the actions of the buttons
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == submit){//if user pessed on submit
			String TypeOfAnimal,StringColor;
			int Size,HorSpeed,VerSpeed,x_front=0,y_front=0,foodFreq=0;;
			Color ColorType = null;
			
			try {
				//--------- Saving the user input ---------//
				Size = Integer.parseInt(txtSize.getText());
				HorSpeed = Integer.parseInt(txtHorSpeed.getText());
				VerSpeed = Integer.parseInt(txtVerSpeed.getText());
				StringColor = (String) CmbColorTypes.getSelectedItem(); 
				TypeOfAnimal = (String) CmbAnimalsTypes.getSelectedItem(); 
				foodFreq=Integer.parseInt(txtFoodFreq.getText());	
			
				// check if the terms are fine
				if(Size<20 || Size>320)
					throw new Exception("size have to be between 20 to 320");
				if(HorSpeed<1 || HorSpeed>10)
					throw new Exception("horizontal speed have to be between 1 to 10");
				if(VerSpeed<1 || VerSpeed>10)
					throw new Exception("vertical speed have to be between 1 to 10");
				if(foodFreq<1000 || foodFreq>3000) // food frequency of the animal
					throw new Exception("food Frequency have to be between 300 to 1000 ms");
				
				// set color by selctedItem from combobox
				switch(StringColor){
					case "Black":
						ColorType=Color.BLACK;
						break;
					case "Red":
						ColorType=Color.RED;
						break;
					case "Blue":
						ColorType=Color.BLUE;
						break;
					case "Green":
						ColorType=Color.GREEN;
						break;
					case "Cyan":
						ColorType=Color.CYAN;
						break;
					case "Orange":
						ColorType=Color.ORANGE;
						break;
					case "Yellow":
						ColorType=Color.YELLOW;
						break;
					case "Magenta":
						ColorType=Color.MAGENTA;
						break;
					case "Pink":
						ColorType=Color.PINK;
						break;
				}
	
				if(TypeOfAnimal == "Fish") {//Checking if the type of the animal is Fish
					seaFactory=new AnimalFactory(objAquaPanel,Size,0,0,HorSpeed,VerSpeed,ColorType,foodFreq);
					animalObj=seaFactory.produceSeaCreature("Fish");
					
					objAquaPanel.animalsSet.add((Swimmable)animalObj);//adding new fish object to the hashset of the AquaPanel class
					objAquaPanel.repaint();// call the paintCompoment function
					new Thread((Swimmable)animalObj).start(); // start the thread
					
					
				}
				if(TypeOfAnimal == "Jelly Fish") {//Checking if the type of the animal is Jelly Fish
					seaFactory=new AnimalFactory(objAquaPanel,Size,0,0,HorSpeed,VerSpeed,ColorType,foodFreq);
					animalObj=seaFactory.produceSeaCreature("Jellyfish");
					
					objAquaPanel.animalsSet.add((Swimmable)animalObj);//adding new Jellyfish object to the hashset of the AquaPanel class
					objAquaPanel.repaint();// call the paintCompoment function
					new Thread((Swimmable)animalObj).start(); // start the thread
				}
		


			}
			catch(NumberFormatException e2) {
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null, "Text fields have to be a number","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);	
			}
			catch(Exception e1){
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null, e1.getMessage(),"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
			dispose();//close the instance dialog
		}
		
		else if (e.getSource() == cancel)
			dispose(); //close the instance dialog
	} 
	
}

