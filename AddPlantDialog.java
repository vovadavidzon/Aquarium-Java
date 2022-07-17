package aquarium3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The AddPlantDialog class which extends the JDialog class and implements ActionListener.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class AddPlantDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JComboBox<String> cmb_AnimalsTypes; //ComboBox animals types 
	private String[] names = {"Laminaria","Zostera"};  
	private JLabel lb_Animal,lb_Size;
	private JTextField txt_size;
	private JButton b_submit,b_cancel;

	private AquaPanel objAquaPanel;
	private AbstractSeaFactory seaFactory;
	private SeaCreature plantObj;
	

	
	/**
	 * 
	 * @param the consturctor get the pen(AquaPanel instance)
	 */
	public AddPlantDialog(AquaPanel objAquaPanel){
		
		//settings AddPlantDialog
		setTitle("Add Planet Dialog");
		setSize(350,250);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		this.objAquaPanel=objAquaPanel;
		
		//set panel
		setPanel();
		
		b_submit.addActionListener(this);
		b_cancel.addActionListener(this);	

		setVisible(true);
	}
	
	
	/**set the panel of the JDialog **/
	public void setPanel(){
	
		//settings of panel frame
		panel=new JPanel();
		panel.setLayout(new GridLayout(3,2));
		
		//Initialize labels
		lb_Animal=new JLabel("Enter type:");
		lb_Size=new JLabel("Enter size (from 20 to 320):");
		
		//Initialize Button
		b_submit=new JButton("Submit");
		b_cancel=new JButton("Cancel");

		//Initialize textFields
		txt_size=new JTextField();
		
		//Initialize comboBox
		cmb_AnimalsTypes=new JComboBox<String>(names);
		
		//add components to the panel
		panel.add(lb_Animal);
		panel.add(cmb_AnimalsTypes);
		panel.add(lb_Size);
		panel.add(txt_size);
		panel.add(b_submit);
		panel.add(b_cancel);
	
		add(panel);
	}
	
	 /**
     * The function which responsible for the actions of the buttons
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b_submit){
			// fill animals details
			String animalType;
			int size=0;
			int x,y = objAquaPanel.getHeight(); //The place of the plants
			
			Random r = new Random();
			int low = 50;
			int high = 1350;
			x = r.nextInt(high-low) + low;
	
		    
		    System.out.println(x+ "  " + y);
			try{
				animalType=cmb_AnimalsTypes.getItemAt(cmb_AnimalsTypes.getSelectedIndex()); // get the selectedItem 
				
				size=Integer.parseInt(txt_size.getText()); //get the size from textField
				
				// check if the terms exists
				if(size<20 || size>320)
					throw new Exception("size have to be between 20 to 320");
		
				if(animalType=="Laminaria"){
					seaFactory=new PlantFactory(objAquaPanel,size,x,y);
					plantObj=seaFactory.produceSeaCreature("Laminaria");
					objAquaPanel.plantsSet.add((Immobile) plantObj);
					objAquaPanel.repaint();// call the paintCompoment function
					}
				else if(animalType=="Zostera"){
					seaFactory=new PlantFactory(objAquaPanel,size,x,y);
					plantObj=seaFactory.produceSeaCreature("Zostera");
					objAquaPanel.plantsSet.add((Immobile)plantObj);
					objAquaPanel.repaint();// call the paintCompoment function
				}
					
				dispose(); // dispose the window after creating
			}
			catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "text fields have to be a number");
			}
			catch(Exception e1){
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
		else if(e.getSource()==b_cancel) {
			dispose(); //close the instance dialog
		}
	}
}
