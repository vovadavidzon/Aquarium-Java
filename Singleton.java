package aquarium3;
/**
 * This is the Singleton class which allows to create only one object
 * and represents the worm in the program.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class Singleton {
	private static Singleton instance = null;
	/* A private Constructor prevents any other
	 * class from instantiating.*/
	private Singleton(){ }
	/* Static 'instance' method */
	public static Singleton getInstance( ){
		if(instance == null)
			instance = new Singleton();
		return instance;
	}

	//Set the instance to null after the thread ate the Worm instance 
	public static void set(){
		if(instance!=null){
			instance=null;
		}
	}
	/**
	 * @return - the instance of Worm.
	 */
	public static Singleton get() {
		return Singleton.instance;
	}
	
}
