package aquarium3;
/**
 * This is the Satiated class that implements HungerState.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class Satiated implements HungerState {

	/**
	 * A fucntion that returns the String "Satiated".
	 */
	@Override
	public String toString(){
		return "Satiated";
	}
	/**
	 * A function that sets the HungeryState of the Swimmable.
	 */
	@Override
	public void doAction(Swimmable objSwim,HungerState hungerState) {
		objSwim.setHungeryState(hungerState);
	}
}
