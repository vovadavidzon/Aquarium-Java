package aquarium3;

import java.awt.Color;
/**
 * This is the MarineAnimalDecorator class that implements the MarineAnimal interface.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class MarineAnimalDecorator implements MarineAnimal {

	MarineAnimal marineAnimal;

	/**
	 * A constructor that gets value and saves it to the MarineAnimal.
	 * @param marineAnimal - MarineAnimal object.
	 */
	public MarineAnimalDecorator(MarineAnimal marineAnimal){
		this.marineAnimal=marineAnimal;
	}
	
	/**
	 * A function that activates the PaintFish in MarineAnimal.
	 */
	@Override
	public void PaintFish(Color col) {
		
		marineAnimal.PaintFish(col);
	}

}
