package aquarium3;

import java.awt.Color;

/**
 * This is the Immobile abstract class that implements the SeaCreature interface.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public abstract class Immobile implements SeaCreature {
	String name;
	protected Color colorr;
	protected int x, y,size;
	private AquaPanel panel;
	private static int counter=0;
	public final int objectID;
	
	/**
	 * constructor which saves the Immobile values
	 * 
	 * @param panel - the AquaPanel object
	 * @param name - the name of the Immobile
	 * @param size - the size of the Immobile
	 * @param x the - the x of the Immobile
	 * @param y - the y of tthe Immobile
	 */
	public Immobile(AquaPanel panel,String name, int size,int x,int y) {
		this.name=name;
		this.size=size;
		colorr=Color.green;
		this.x=x;
		this.y=y;
		this.panel=panel;
		this.objectID=++counter;
	}
	
	//********************* Getters *********************
	public int getID(){return objectID;}
	public String getPlanetName(){return name;}
	public String getColor(){return "green";}
	public int getSize(){return this.size;}
	public int getXfront(){return this.x;}
	public int getYfront(){return this.y;}
	public Color getColorPlanet(){return this.colorr;}
	
	/**
	 * A function that sets the state of the Immobile
	 * @param colorr
	 * @param size
	 * @param x
	 * @param y
	 */
	public  void setState(Color colorr,int size,int x,int y){
		this.colorr=colorr;
		this.size=size;
		this.x=x;
		this.y=y;
	}
}
