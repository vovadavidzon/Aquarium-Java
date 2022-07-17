package aquarium3;


/**
 * this is the HungerState interface which has 2 functions.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public interface HungerState {

	public void doAction(Swimmable objSwimmable,HungerState hungerState);
	public String toString();
}
