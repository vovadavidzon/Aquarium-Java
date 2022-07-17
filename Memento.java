package aquarium3;

import java.awt.Color;
/**
 * This is the Memento class that saves the values of the SeaCreature state.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class Memento {

	private int idObj,size,x_front,y_front,horSpeed,verSpeed,x_dir,y_dir;
	private Color color;
	private String ColorAnimal,animalType;
	private Swimmable swimObj=null;
	
	/**
	 * A constructor that gets values and initializes them to the object .
	 * @param swimObj - the Swimmable object.
	 */
	public Memento(Swimmable swimObj){
		this.swimObj=swimObj;
		this.idObj=swimObj.getID();
		this.color=swimObj.getColorAnimal();
		this.size=swimObj.getSize();
		this.x_front=swimObj.getXfront();
		this.y_front=swimObj.getYfront();
		this.horSpeed=swimObj.getHorSpeed();
		this.verSpeed=swimObj.getHorSpeed();
		this.animalType=swimObj.getAnimalName();
		this.x_dir=swimObj.getX_dir();
		this.y_dir=swimObj.getX_dir();
	}
	
	/**
	 * * A constructor that gets values and initializes them to the object .
	 * @param immObj - the Immobile object.
	 */
	public Memento(Immobile immObj){
		this.idObj=immObj.getID();
		this.color=immObj.getColorPlanet();
		this.size=immObj.getSize();
		this.x_front=immObj.getXfront();
		this.y_front=immObj.getYfront();
		this.animalType=immObj.getPlanetName();
	}
	
	/**
	 * @return - the color of the seaCreature
	 */
	public Color getCol(){return color;}
	/**
	 * @return - the color of the animal in String
	 */
	public String getColor() {
		if (color== Color.BLUE)
			ColorAnimal="blue";
		else if (color==Color.RED)
			ColorAnimal="red";
		else if (color== Color.GREEN)
			ColorAnimal="green";
		else if (color== Color.BLACK)
			ColorAnimal="black";
		else if (color==Color.yellow)
			ColorAnimal="yellow";
		else if(color==Color.pink)
			ColorAnimal="pink";
		else if(color==Color.green)
			ColorAnimal="green";
		else if(color==Color.cyan)
			ColorAnimal="cyan";
		else if(color==Color.orange)
			ColorAnimal="orange";
		else if(color==Color.magenta)
			ColorAnimal="magenta";
		return ColorAnimal;	
		}
	
	
	//*********** Getters ************** //
	public int getID(){return idObj;}
	public int getSize(){return size;}
	public int getXfront(){return x_front;}
	public int getYfront(){return y_front;}
	public int getHorSpeed(){return horSpeed;}
	public int getVerSpeed(){return verSpeed;}
	public String getAnimalName(){return animalType;}
	public int getX_dir(){return x_dir;}
	public int getY_dir(){return y_dir;}
}
