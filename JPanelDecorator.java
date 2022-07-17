package aquarium3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
/**
 * This is the JPanelDecorator class that extends JPanel and  implements ActionListener.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class JPanelDecorator extends JPanel implements ActionListener{
	
	private JButton changeColorButton,CancelButton;// for JPanelDecorator - Decorator Design Pattern
	private JPanel buttonsPanel;
	private JDialog changeColorDialog;
	private AquaPanel aquapanel;
	private Iterator <Swimmable>itrAnimals;
	private JPanel tablePanel; // create panels to the dialog
	private JTable table;
	private int idObject=0,totalEatCounter=0;
	private String typeAnimal=null,idOb,StringColor=null;
	Color color = Color.black; //default color
	
	/**
	 * A constructor that creates the JPanel .
	 * 
	 * @param panel - the AquaPanel object
	 * @param changeColorDialog - the JDialog object
	 */
	public JPanelDecorator(AquaPanel panel,JDialog changeColorDialog ){
		super();

		setLayout(new BorderLayout());
		
		this.changeColorDialog=changeColorDialog;
		this.aquapanel = panel;
	
		buttonsPanel = new JPanel(new FlowLayout());
		changeColorButton=new JButton("Change Color");
		changeColorButton.setBackground(Color.LIGHT_GRAY);
		changeColorButton.setFocusable(false);
		changeColorButton.setFont(new Font("Tahoma",Font.BOLD,20));
		CancelButton=new JButton("Cancel");
		CancelButton.setBackground(Color.LIGHT_GRAY);
		CancelButton.setFocusable(false);
		CancelButton.setFont(new Font("Tahoma",Font.BOLD,20));
		
		buttonsPanel.add(changeColorButton);
		buttonsPanel.add(CancelButton);
		buttonsPanel.setVisible(true);
		
		SettingTable();

		add(buttonsPanel,BorderLayout.SOUTH);
		add(tablePanel,BorderLayout.NORTH);
		
		
		//--------- create events ----------//
		changeColorButton.addActionListener(this);
		CancelButton.addActionListener(this);
		
		
		setVisible(true);
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
		    String[] swimm=new String[]{"",String.valueOf(animal.getID()),animal.getAnimalName(),String.valueOf(animal.getColorAnimal()),String.valueOf(animal.getSize()),String.valueOf(animal.getHorSpeed()),String.valueOf(animal.getVerSpeed()),String.valueOf(animal.getEatCount())};
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
     * The function which responsible for the actions of the buttons
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==changeColorButton){ //if user pressed changeColorButton
			if(typeAnimal!=null) {
				
				JColorChooser colorChosser = new JColorChooser();
				
				color = JColorChooser.showDialog(this, "Pick a color", Color.black);
				
				StringColor = color.toString();
				
				if(color!=null) {
					//find the swimmable object that was selected
					itrAnimals= aquapanel.animalsSet.iterator(); 
					MarineAnimal marineanimal = null;
					while(itrAnimals.hasNext()){
						Swimmable swimmable=itrAnimals.next();
						if(swimmable.getID()==idObject){
							if(typeAnimal.equals("Fish"))
								marineanimal = (MarineAnimal) swimmable;
							if(typeAnimal.equals("Jellyfish")){
								marineanimal = (MarineAnimal) swimmable;
							}
						}
					}
					MarineAnimal changeObject = new MarineAnimalDecorator(marineanimal); 

					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,15));
					UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"the color of animal number: "+((Swimmable)marineanimal).getID()+" changed to "+StringColor );
					changeObject.PaintFish(color);

					changeColorDialog.dispose();
					repaint();
				}
			}else {
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
				UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"Please select the animal from the Table");
			}
		}
		else if(e.getSource()==CancelButton){//if user pressed CancelButton
			changeColorDialog.dispose();
		}
	}
}
