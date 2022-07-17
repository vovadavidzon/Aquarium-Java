package aquarium3;

/**
 * This is the Hungry class that implements the HungerState interface.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class Hungry implements HungerState {

	/**
	 * A function that returns the String "Hungry"
	 */
	@Override
	public String toString(){
		return "Hungry";
	}

	/**
	 * A function that sets the state of the object
	 */
	@Override
	public void doAction(Swimmable objSwim,HungerState hungerState) {
		objSwim.setHungeryState(hungerState);
	}

}
