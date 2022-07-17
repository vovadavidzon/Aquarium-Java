package aquarium3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class SaveObjectDialog extends JDialog implements ActionListener,ItemListener{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] types = {"Swimmable types","Plants types"};
	private JComboBox<String> cmb_types;  // ComboBox colors types
		
	private Iterator <Swimmable>itrAnimals;
	private Iterator <Immobile>itrPlants;

	private JPanel DialogPanel,buttonsPanel,comboPanel; // create panels to the dialog
	private JButton saveObjectButton,CancelButton;
	private AquaPanel aquapanel;
	private CareTaker carTakerMemento; // for memento design pattern
	private JPanel tablePanel1 =null,tablePanel2 =null; // create panels to the dialog
	private JTable table1,table2;
	private int idObject=0,totalEatCounter=0;
	private String typeAnimal=null,idOb,StringColor=null;
	
	public SaveObjectDialog(AquaPanel panel,CareTaker carTakerMemento){
		super();
		
		//settings SaveObjectDialog
		setTitle("Momento Design Pattern - Save Object state");
		setSize(650,300);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		this.aquapanel = panel;
		this.carTakerMemento=carTakerMemento;

	
		DialogPanel=new JPanel(new BorderLayout());
		buttonsPanel = new JPanel(new FlowLayout());
		comboPanel=new JPanel(new FlowLayout());

		comboPanel.setSize(new Dimension(300,100));
		cmb_types=new JComboBox<String>(types);

		saveObjectButton=new JButton("Save object");
		saveObjectButton.addActionListener(this);
		saveObjectButton.setBackground(Color.LIGHT_GRAY);
		saveObjectButton.setFocusable(false);
		saveObjectButton.setFont(new Font("Tahoma",Font.BOLD,20));
		CancelButton=new JButton("Cancel");
		CancelButton.addActionListener(this);
		CancelButton.setBackground(Color.LIGHT_GRAY);
		CancelButton.setFocusable(false);
		CancelButton.setFont(new Font("Tahoma",Font.BOLD,20));
		
		buttonsPanel.add(saveObjectButton);
		buttonsPanel.add(CancelButton);
		buttonsPanel.setVisible(true);
		
		comboPanel.add(cmb_types);
		DialogPanel.add(comboPanel,BorderLayout.PAGE_START);
		DialogPanel.add(buttonsPanel,BorderLayout.SOUTH);
		
		SettingSwimmableTable();
		
		tablePanel1.setVisible(true);
		DialogPanel.add(tablePanel1);
	
		add(DialogPanel); // add the panel to the JDialog dialogTable 
		setVisible(true);// show the dialog
		
		cmb_types.addItemListener(this);
	}
	
	public void SettingSwimmableTable(){
		tablePanel1=new JPanel();
       
        String[] columns=null;
        columns=new String[]{"ID","Animal","Color","Size","Hor.Speed","Ver.speed","Eat counter","Hunger status"}; // columns
       
        
        DefaultTableModel tableModel1 = new DefaultTableModel(columns,0);
        table1=new JTable (tableModel1);
        table1.setPreferredScrollableViewportSize(new Dimension(600,125));
       
	    itrAnimals= aquapanel.animalsSet.iterator();
	    while(itrAnimals.hasNext()){
		    Swimmable animal = itrAnimals.next();
		    String[] swimm=new String[]{String.valueOf(animal.getID()),animal.getAnimalName(),String.valueOf(animal.getColorAnimal()),String.valueOf(animal.getSize()),String.valueOf(animal.getHorSpeed()),String.valueOf(animal.getVerSpeed()),String.valueOf(animal.getEatCount())};
		    totalEatCounter+=animal.getEatCount(); //sum all eat count of the swimmable
		    tableModel1.addRow(swimm);
        }
  
        table1.addMouseListener(new MouseAdapter() {
        	public void mouseReleased(MouseEvent evt) {

        		int row = table1.rowAtPoint(evt.getPoint());

        		if (row >= 0) {

        			idOb=(String) table1.getValueAt(row,0); //for check
        			typeAnimal=(String) (table1.getValueAt(row,1));
        			idObject=Integer.valueOf(idOb);

        			System.out.println(typeAnimal);//check
        			System.out.println(idObject);//check
        			}
        		}
        });
        
	 JScrollPane pane1 = new JScrollPane(table1);  
     tablePanel1.add(pane1); // add the table to the panel
	}
	
	
	public void SettingImmobileTable(){
		tablePanel2=new JPanel();
       
        String[] columns=null;
        columns=new String[]{"ID","Planet","Color","Size","x_front","y_front"}; // columns
       
        
        DefaultTableModel tableModel2 = new DefaultTableModel(columns,0);
        table2=new JTable (tableModel2);
        table2.setPreferredScrollableViewportSize(new Dimension(600,125));
       
	    itrPlants= aquapanel.plantsSet.iterator();
	    while(itrPlants.hasNext()){
		    Immobile plant = itrPlants.next();
		    String[] Immob=new String[]{String.valueOf(plant.getID()),plant.getPlanetName(),plant.getColor(),String.valueOf(plant.getSize())};
		   
		    tableModel2.addRow(Immob);
        }
  
        table2.addMouseListener(new MouseAdapter() {
        	public void mouseReleased(MouseEvent evt) {

        		int row = table2.rowAtPoint(evt.getPoint());

        		if (row >= 0) {

        			idOb=(String) table2.getValueAt(row,0); //for check
        			typeAnimal=(String) (table2.getValueAt(row,1));
        			idObject=Integer.valueOf(idOb);

        			System.out.println(typeAnimal);//check
        			System.out.println(idObject);//check
        			}
        		}
        });
        
	 JScrollPane pane2=new JScrollPane(table2);  
     tablePanel2.add(pane2); // add the table to the panel
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e1) {
		
		if(e1.getSource()==saveObjectButton){
			Swimmable swimObj=null;
			Immobile immoObj=null;
			Memento memObj=null;
			
			if(typeAnimal.equals("Fish") || typeAnimal.equals("Jellyfish")){
				
				//getting the Swimmable by ID:
				itrAnimals=aquapanel.animalsSet.iterator();	
			 	while(itrAnimals.hasNext()){
		 			Swimmable sw=itrAnimals.next();
			 		if(sw.getID()==idObject){		
			 			swimObj = sw;
			 		}	
			 	}
				
				memObj=new Memento(swimObj);
				carTakerMemento.addSwimmableMemento(swimObj.getID(), memObj);
				
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,15));
				UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,swimObj.getAnimalName() + " - " + swimObj.getID()+ " state saved!");
			}
			else if(typeAnimal.equals("Laminaria") || typeAnimal.equals("Zostera")){
				
				//getting the immobile by ID:
				itrPlants=aquapanel.plantsSet.iterator();	
			 	while(itrPlants.hasNext()){
		 			Immobile im=itrPlants.next();
			 		if(im.getID()==idObject){		
			 			immoObj = im;
			 		}	
			 	}
				
				memObj=new Memento(immoObj);
				carTakerMemento.addImmobileMemento(immoObj.getID(),memObj);
				
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,15));
				UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,immoObj.getPlanetName() + " - " + immoObj.getID()+ " state saved!");

			}
			dispose();
		}
		else if(e1.getSource()==CancelButton){
			dispose();
		}
	}


	/**
	 * A function that is performed when the user presses on the comboBox
	 */
	@Override
	public void itemStateChanged(ItemEvent e2) {
		
		String types = (String) cmb_types.getSelectedItem(); 
		
		if(types.equals("Swimmable types")){
			SettingSwimmableTable();
			if(tablePanel2!=null) {
				tablePanel2.setVisible(false);
				DialogPanel.remove(tablePanel2);
			}
			tablePanel1.setVisible(true);
			DialogPanel.add(tablePanel1);
		}
		else if(types.equals("Plants types")){
			SettingImmobileTable();
			if(tablePanel1!=null) {
				tablePanel1.setVisible(false);
				DialogPanel.remove(tablePanel1);
			}
			tablePanel2.setVisible(true);
			DialogPanel.add(tablePanel2);
		}	
	}
}
