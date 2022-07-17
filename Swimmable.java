package aquarium3;
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;

/**
 * An abstract class of Swimmable - sea creatures in the aquarium that implements the Comparable interface.
 * 
 * @version 1.0  26/3/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public abstract class Swimmable extends Thread implements SeaCreature,Cloneable{
	protected int horSpeed;
	protected int verSpeed;
	private static int counter=0;
	public final int objectID;
	int foodFreq;
	
	/**
	 * A defaoult constructor that initializes the horSpeed and verSpeed to 0.
	 */
	public Swimmable( ) {
		this.objectID= ++counter;
		horSpeed=0;
		verSpeed=0;
	}
	/**
	 * A copy constructor that copyes the horSpeed and verSpeed of other object to the current object .
	 * 
	 * @param other - other Swimmable object.
	 */
	public Swimmable(Swimmable other) {
		this();
		
		if(other != null) {
			horSpeed=other.horSpeed;
			verSpeed = other.verSpeed;
			foodFreq = other.foodFreq;
		}
	}
	/**
	 * Implementation of the Comparable interface function that compares the sizes of the sea creatures.
	 * 
	 * @param other - other Swimmable object .
	 * @return return 1 if the current sea creature is bigger than the other else :-1.
	 */
	public int compareTo(Swimmable other) {
		if (getSize()> other.getSize())
			return 1;
		else return -1;
	}
	
	/**
	 * A constructor that gets a horSpeed,verSpeed and initializes them to the object .
	 * 
	 * @param horSpeed - the horSpeed of the sea creature.
	 * @param verSpeed - the verSpeed of the sea creature.
	 */
	public Swimmable(int horSpeed , int verSpeed,int foodFreq) {
		this.objectID= ++counter;
		this.horSpeed = horSpeed;
		this.verSpeed = verSpeed;
		this.foodFreq=foodFreq;
	}
	/**
	 * @return A function that returns the horSpeed of the sea creature.
	 */
	public int getHorSpeed() { return horSpeed; }
	
	/**
	 * @return A function that returns the verSpeed of the sea creature.
	 */
	public int getVerSpeed() { return verSpeed; }
	
	/**
	 * A function that sets the horizontal Speed of the sea creature.
	 * @param hor - The horizontal speed of the see creature.
	 */
	public void setHorSpeed(int hor) { horSpeed  = hor; }
	/**
	 * A function that sets the vertical Speed of the sea creature.
	 * @param ver - The vertical speed of the see creature.
	 */
	public void setVerSpeed(int ver) { verSpeed  = ver; }
	
	/**
	 * A boolean function that checks if 2 objects are eqaual
	 * 
	 * @return true if the objects are eqaual else : false
	 */
	public boolean equals(Object other) {
		boolean ans = false;
		if(other instanceof Swimmable) {
			ans =( horSpeed == ((Swimmable)other ).horSpeed &&  verSpeed ==((Swimmable)other).  verSpeed)  ;
		}
		return ans;
	}
	/**
	 * A string function that returns a string with the horSpeed and verSpeed. 
	 */
	public String toString() {
		return   "the horSpeed is:  "+horSpeed+ " and the verSpeed :" +verSpeed;
	}
	
	/**
	 * @return - The id of the Swimmable
	 */
	public int getID(){return objectID;}
	
	//----------------- Abstract functions ----------------//
	abstract public void run();
	abstract public void SetThreadLives();
	abstract public String getAnimalName();
	abstract public void setSuspend();
	abstract public void setResume();
	abstract public void setBarrier(CyclicBarrier b);
	abstract public int getSize();
	abstract public void eatInc();
	abstract public int getEatCount();
	abstract public String getColor();
	abstract public Color getColorAnimal();
	abstract public Object clone() throws CloneNotSupportedException;
	abstract public void editSwimmable(int size,int horSpeed,int verSpeed,Color col);
	abstract public int getX_dir();
	abstract public int getY_dir();
	abstract public int getXfront();
	abstract public int getYfront();
	abstract public void setState(Color col,int size,int x_front,int y_front,int horSpeed,int verSpeed,int x_dir,int y_dir);
	abstract public String getHungerState();
	abstract public void setHungeryState(HungerState state);
}
