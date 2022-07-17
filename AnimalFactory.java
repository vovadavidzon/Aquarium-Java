package aquarium3;

import java.awt.Color;

/**
 * The AnimalFactory class which implements the AbstractSeaFactory interface.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class AnimalFactory implements AbstractSeaFactory {
	private AquaPanel panel;
	private int size , x_front , y_front , horSpeed , verSpeed,foodFreq;
	private Color col;

	/**
	 * constructor which saves the animal values
	 * 
	 * @param panel - the AquaPanel object.
	 * @param size - the size of the animal.
	 * @param x_front - the x_front of the animal. 
	 * @param y_front - the y_front of the animal.
	 * @param horSpeed -the horizontal Speed of the animal.
	 * @param verSpeed - the vertical Speed of the animal.
	 * @param col - the color of the animal.
	 * @param foodFreq - the foodFreq of the animal.
	 */
	public AnimalFactory(AquaPanel panel,int size ,int x_front ,int y_front ,int horSpeed ,int verSpeed ,Color col,int foodFreq) {
		
		this.panel=panel;
		this.size=size;
		this.x_front=x_front;
		this.y_front=y_front;
		this.col = col;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.foodFreq=foodFreq;
	}

	/**
	 * The function that produces the animal according to the type 
	 * 
	 * @param type- the type of the animal
	 */
	@Override
	public SeaCreature produceSeaCreature(String type) {
		if(type.equalsIgnoreCase("Fish"))
			return new Fish( panel, size , x_front , y_front , horSpeed , verSpeed , col,foodFreq);
		else if(type.equalsIgnoreCase("Jellyfish"))
			return new Jellyfish( panel, size , x_front , y_front , horSpeed , verSpeed , col,foodFreq);
	
		return null;
	}

}
