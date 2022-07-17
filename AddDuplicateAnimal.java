package aquarium3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 * The AddDuplicateAnimal class which extends the JDialog class and implements ActionListener.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class AddDuplicateAnimal extends JDialog implements ActionListener{

	private int size=0,hor=0,ver=0;

	private int idObject=0,totalEatCounter=0;
	private String typeAnimal=null,idOb,StringColor=null;
	private Color ColorType = null;
	private String[] colors = {"Black","Red","Blue","Green","Cyan" 
            ,"Orange","Yellow","Magenta","Pink"};
	private JComboBox<String> cmb_ColorTypes;  // ComboBox colors types

	private AquaPanel aquapanel;
	private Iterator <Swimmable>itrAnimals;
	private JPanel DialogPanel,buttonsPanel,tablePanel; // create panels to the dialog
	private JButton duplicateButton,cancelButton;
	private Swimmable duplicateSwimmable;
	private JTable table;
	
	/**
	 * constructor which creates the main AddDuplicateAnimal Dialog
	 *
	 * @param panel - the panel of the dialog
	 */
	public AddDuplicateAnimal(AquaPanel panel){
		super();
		
		//settings AddDuplicateAnimal
		setTitle("Add Duplicate Dialog");
		setSize(620,400);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		this.aquapanel=panel;
		
		this.typeAnimal=null;
		
		DialogPanel=new JPanel(new BorderLayout());
		buttonsPanel = new JPanel(new FlowLayout());

		duplicateButton=new JButton("Duplicate");
		duplicateButton.setBackground(Color.LIGHT_GRAY);
		duplicateButton.setFocusable(false);
		duplicateButton.setFont(new Font("Tahoma",Font.BOLD,20));
		cancelButton=new JButton("Cancel");
		cancelButton.setBackground(Color.LIGHT_GRAY);
		cancelButton.setFocusable(false);
		cancelButton.setFont(new Font("Tahoma",Font.BOLD,20));
		
		duplicateButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		buttonsPanel.add(duplicateButton);
		buttonsPanel.add(cancelButton);
		buttonsPanel.setVisible(true);

		SettingTable();
		
		DialogPanel.add(buttonsPanel,BorderLayout.SOUTH);
		DialogPanel.add(tablePanel,BorderLayout.NORTH);
		
		
		add(DialogPanel); // add the panel to the JDialog dialogTable 
		
		setVisible(true);// show the dialog
	}

	/**
	 * the function that creates the table with the animals
	 */
	public void SettingTable(){
		tablePanel=new JPanel();
       
        String[] columns=null;
        columns=new String[]{"","ID","Animal","Color","Size","Hor.Speed","Ver.speed","Eat counter","Hunger status"}; // columns
       
        
        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        table=new JTable (tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600,125));
       
	    itrAnimals= aquapanel.animalsSet.iterator();
	    while(itrAnimals.hasNext()){
		    Swimmable animal = itrAnimals.next();
		    String[] swimm=new String[]{"",String.valueOf(animal.getID()),animal.getAnimalName(),animal.getColor(),String.valueOf(animal.getSize()),String.valueOf(animal.getHorSpeed()),String.valueOf(animal.getVerSpeed()),String.valueOf(animal.getEatCount())};
		    totalEatCounter+=animal.getEatCount(); //sum all eat count of the swimmable
		    tableModel.addRow(swimm);
        }
	    // adding mouse Listener to the table:
        table.addMouseListener(new MouseAdapter() {
        	public void mouseReleased(MouseEvent evt) {

        		int row = table.rowAtPoint(evt.getPoint());

        		if (row >= 0) {

        			idOb=(String) table.getValueAt(row,1); //for check
        			typeAnimal=(String) (table.getValueAt(row,2));
        			idObject=Integer.valueOf(idOb);

        			System.out.println(typeAnimal);//check
        			System.out.println(idObject);//check
        			}
        		}
        });
        
	 JScrollPane pane=new JScrollPane(table);  
     tablePanel.add(pane); // add the table to the panel
	}
	
	/**
	 * The function that duplicates the object of the chosen animal
	 * 
	 * @param idObject - the ID (int) of the animal that was chosen
	 * @param typeAnimal - the type of the animal that was chosen
	 * @throws CloneNotSupportedException
	 */
	public void duplicateAnimalObject(int idObject,String typeAnimal) throws CloneNotSupportedException{//duplicate the swimmable by get id and type
		itrAnimals= aquapanel.animalsSet.iterator(); 
		while(itrAnimals.hasNext()){
			Swimmable animal=itrAnimals.next();
			if(animal.getID() == idObject){
				if(typeAnimal.equals("Fish")) {
					duplicateSwimmable = (Swimmable)animal.clone();//activation of clone function
				}
				else if(typeAnimal.equals("Jellyfish")) {
					duplicateSwimmable = (Swimmable)animal.clone();//activation of clone function
				}
			}
		}				
	}
	
	/**
	 * The function that allows to edit the animals Duplicate Object.
	 * @param editSwimmable - the animal object that was duplicated.
	 */
	public void editDuplicateAnimal (Swimmable editSwimmable){
		//setting edit panel
		JPanel editPanel=new JPanel();
		editPanel.setSize(800,300);
		editPanel.setLayout(new GridLayout(5,3,1,2));
		
		//intialize variable
		cmb_ColorTypes=new JComboBox<String>(colors);
		JLabel lb_Size=new JLabel("  Enter size (from 20 to 320):");
		JLabel lb_horSpeed=new JLabel("  Horizontal speed (from 1 to 10):");
		JLabel lb_verSpeed=new JLabel("  Vertical speed (from 1 to 10):");
		JLabel lb_color=new JLabel("  Enter color:");
		
		//intialize Buttons
		JButton EnterButton=new JButton("Enter");
		EnterButton.setFocusable(false);
		EnterButton.setFont(new Font("Tahoma",Font.BOLD,30));
		JButton CancelButton=new JButton("Cancel");
		CancelButton.setFocusable(false);
		CancelButton.setFont(new Font("Tahoma",Font.BOLD,30));
		
		//intialize JTextField
		JTextField txt_size=new JTextField();	
		JTextField txt_verSpeed=new JTextField();
		JTextField txt_horSpeed=new JTextField();
		
		txt_size.setText(String.valueOf(editSwimmable.getSize()));
		txt_verSpeed.setText(String.valueOf(editSwimmable.getVerSpeed()));
		txt_horSpeed.setText(String.valueOf(editSwimmable.getVerSpeed()));
	
		editPanel.add(lb_Size);
		editPanel.add(txt_size);
		editPanel.add(lb_horSpeed);
		editPanel.add(txt_horSpeed);
		editPanel.add(lb_verSpeed);
		editPanel.add(txt_verSpeed);
		editPanel.add(lb_color);
		editPanel.add(cmb_ColorTypes);
		editPanel.add(EnterButton);
		editPanel.add(CancelButton);
		
		// Enter Button Listener
		EnterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg){
				size=Integer.parseInt(txt_size.getText()); //get the size from textField
				hor=Integer.parseInt(txt_horSpeed.getText()); //get the horizontal from textField
				ver=Integer.parseInt(txt_verSpeed.getText());	// get the vertical from textField
				StringColor=cmb_ColorTypes.getItemAt(cmb_ColorTypes.getSelectedIndex()); //get the selectedItem

				try{
					// check if the terms exists
					if(size<20 || size>320)
						throw new Exception("size have to be between 20 to 320");
					if(hor<1 || hor>10)
						throw new Exception("horizontal speed have to be between 1 to 10");
					if(ver<1 || ver>10)
						throw new Exception("vertical speed have to be between 1 to 10");
					
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
					
					editSwimmable.editSwimmable(size,hor,ver,ColorType);//Setting the edited values to the animal
					
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
					UIManager.put("OptionPane.minimumSize",new Dimension(380,90)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"The duplicate swimmable "+editSwimmable.getAnimalName() +" is edited\n and has been added to the Aquarium");
					
					aquapanel.animalsSet.add(editSwimmable); //adding the animals to the animalsSet
					aquapanel.repaint();// call the paintCompoment function
					new Thread(duplicateSwimmable).start(); // start the thread
					dispose(); //close the window
			}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}	
		});

		// Cancel Button Listener
		CancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg){
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
				UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"The swimmable has not been added to the aquarium !");
				
				dispose(); //close the window
			}
		});
		DialogPanel.add(editPanel);
		editPanel.setVisible(true);
	}

    /**
     * The function which responsible for the actions of the buttons
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==duplicateButton){
			if(typeAnimal!=null) {
				try {
					duplicateAnimalObject(idObject,typeAnimal);
				}
				catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				
	            if (JOptionPane.showConfirmDialog(null, "Swimmable "+duplicateSwimmable.getAnimalName()+ " duplicated,\ndo you want to edit the object?", "Edit Message",
	                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { //if the user want to edit the duplicate objet
	            	 buttonsPanel.setVisible(false);
	            	 editDuplicateAnimal(duplicateSwimmable);
	            }
	            else {
					aquapanel.animalsSet.add(duplicateSwimmable);//adding duplicate Swimmable object to the hashset of the AquaPanel class
					aquapanel.repaint();// call the paintCompoment function
					new Thread(duplicateSwimmable).start(); // start the thread

					JOptionPane.showMessageDialog(null,"Duplicate object added to set");
					this.dispose();
	            }
			}else {
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
				UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"Please select the animal from the Table");
			}
		}
		else if(e.getSource()==cancelButton){
			dispose();
		}
	}
	
}