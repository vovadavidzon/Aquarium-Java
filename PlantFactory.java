package aquarium3;

import java.awt.Color;
/**
 * This is the PlantFactory class which implements the AbstractSeaFactory.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class PlantFactory implements AbstractSeaFactory {
	
	private AquaPanel panel;
	private int size , x , y;
	private Color col;

	/**
	 * A constructor that gets values and initializes them to the object .
	 * 
	 * @param panel - the AquaPanel object.
	 * @param size - the size of the Plant.
	 * @param x - the x of the Plant.
	 * @param y - the y of the Plant.
	 */
	public PlantFactory(AquaPanel panel,int size ,int x ,int y ) {
		
		this.panel=panel;
		this.size=size;
		this.x=x;
		this.y=y;
	}

	/**
	 * A fucntion that produces the plant according to the type.
	 */
	@Override
	public SeaCreature produceSeaCreature(String type) {
		if(type.equalsIgnoreCase("Laminaria"))
			return new Laminaria (panel,  size, x, y);
		else if(type.equalsIgnoreCase("Zostera"))
			return new  Zostera (panel, size, x, y);
	
		return null;
	}
}
