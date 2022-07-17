package aquarium3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * A class of a Jelly fish that extends the Swimmable class.
 * 
 * @version 1.0  26/3/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class Jellyfish extends Swimmable implements MarineAnimal {
	private final int EAT_DISTANCE =5;//A constant that describes the amount of food a Jellyfish eats to grow.
	private int size; //The size of the Jellyfish.
	private Color col; //The Color of the Jellyfish.
	private int eatCount; //The amount of food the Jellyfish eat.
	private int x_front, y_front, x_dir, y_dir;
	private AquaPanel panel;
	private String animal_color; 
	private boolean isSuspended=false,ThreadLives = true ;
	private CyclicBarrier Barrier;
	private double newHorSpeed,newVerSpeed;
	private HungerState currentState;
	private int foodCounter=0;
	
	/**
	 * A defaoult constructor that initializes the values of the Jellyfish to 0 or 1.
	 */
	public Jellyfish() {
		super();
		eatCount=0; x_dir=1; y_dir=1;
		this.size=0;
		this.col=Color.BLACK;
		this.x_front=0;
		this.y_front=0;
	}
	/**
	 * A copy constructor that copyes all Jellyfish fields of other object to the current object .
	 * 
	 * @param other -other Jellyfish object.
	 */
	public Jellyfish(Jellyfish other) {
		super(other);
		if(other != null) {
			panel = other.panel;
			size=other.size;
			col = other.col;
			x_front=other.x_front;
			y_front=other.y_front;
			eatCount=other.eatCount;
			x_dir=other.x_dir;
			y_dir=other.y_dir;
			currentState=new Satiated();
			}
		}
	/**
	 * A constructor that gets values and initializes them to the object .
	 * 
	 * @param size - The size of the fish.
	 * @param x_front - the x_front of the fish.
	 * @param y_front - the y_front of the fish.
	 * @param horSpeed - the horSpeed of the fish.
	 * @param verSpeed - the verSpeed of the fish.
	 * @param col - the color of the fish.
	 */
	public Jellyfish(AquaPanel panel,int size ,int x_front ,int y_front ,int horSpeed ,int verSpeed ,Color col,int foodFreq) {
		super(horSpeed,verSpeed,foodFreq);
		this.panel=panel;
		eatCount=0; x_dir=1; y_dir=1;
		this.size=size;
		this.col=col;
		this.x_front=x_front;
		this.y_front=y_front;
		currentState=new Satiated();
	}
	/**
	 * A string function that returns a string with the animal name ,the color of the Jellyfish,
	 * the size of the Jellyfish and the amount of food he ate.
	 */
	public String toString() {
		String str = String.format("%-20s%-10s%-5d%-5d",getAnimalName(), getColor(), getSize(),eatCount);  
		return  str;
	}

	/**
	 * A function that returns the name of the animal
	*/
	@Override
	public String getAnimalName() {
		String className = this.getClass().getSimpleName();
		return className;
	}
	/**
	 * @return - returning the amount of food the Jellyfish ate.
	 */
	@Override
	public int getEatCount() {
		return eatCount;
	}
	/**
	 * @return - returning the size of the Jellyfish.
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * A void function that changes the Jellyfish by 1.
	 */
	public void changeJellyfish() {
		this.size +=1;
	}
	/**
	 * A function that feeds the Jellyfish and 
	 * checks if the fish has grown or not.
	 */
	@Override
	public void eatInc() {
		this.eatCount +=1;
		changeJellyfish();
		
	}
	/**
	 * A boolean function that checks if 2 objects are eqaual
	 * 
	 * @return true if the objects are eqaual else : false
	 */
	public boolean equals(Object other) {
		boolean ans = false;
		if(other instanceof Jellyfish) {
			ans =( size == ((Jellyfish)other ).size &&  col ==((Jellyfish)other).  col &&   eatCount ==((Jellyfish)other).  eatCount&&
			x_front ==((Jellyfish)other).  x_front && y_front ==((Jellyfish)other).  y_front && x_dir ==((Jellyfish)other).  x_dir && 
			y_dir ==((Jellyfish)other).  y_dir);
		}
		return ans;
	}
	
	/**
	 * A function Which draws the jellyfish in the main panel
	 */
	@Override
	public void drawCreature(Graphics g) {
		   int numLegs;
		   if(size<40)
		    	numLegs = 5;
		   else if(size<80)
		    	numLegs = 9;
		   else
		    	numLegs = 12;

		   g.setColor(col);
		   g.fillArc(x_front - size/2, y_front - size/4, size, size/2, 0, 180);
				
		   for(int i=0; i<numLegs; i++)
		   g.drawLine(x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front, x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front+size/3);

		
	}
	
	/**A function Which sets the isSuspended flag to true and it causes to suspend the Jellyfish object **/
	@Override
	public void setSuspend() {
		isSuspended = true;
		
	}
	/**set the flag to false and cancel the suspend by notifying the object **/
	@Override
	public void setResume() {
		synchronized(this){
			isSuspended=false;
			notify();
		}
	}
	
	/**set the barrier that sent by AquaPanel class **/
	@Override
	public void setBarrier(CyclicBarrier b) {
		this.Barrier = b;
	}
	
	/**
	 * A fucntion that returns the animal_color (String).
	 * @return animal_color - The color of the Jellyfish.
	 */
	@Override
	public String getColor() {
		if (col== Color.BLUE)
			animal_color="blue";
		else if (col==Color.RED)
			animal_color="red";
		else if (col== Color.GREEN)
			animal_color="green";
		else if (col== Color.BLACK)
			animal_color="black";
		else if (col==Color.yellow)
			animal_color="yellow";
		else if(col==Color.pink)
			animal_color="pink";
		else if(col==Color.green)
			animal_color="green";
		else if(col==Color.cyan)
			animal_color="cyan";
		else if(col==Color.orange)
			animal_color="orange";
		else if(col==Color.magenta)
			animal_color="magenta";
		return animal_color;
	}
	
	/**
	 * A flag the sets the tread live flag to false and ends the thread.
	 */
	public void SetThreadLives() {
		ThreadLives = false;
	}
	
	/**
	 * A function which causes the Jellyfish to try and eat the worm .
	 */
	public void tryToEatTheWorm() {
		// changing the drection and speed of the Jellyfish to the center of the panel

		double speed =Math.sqrt(horSpeed*horSpeed+verSpeed*verSpeed);
		double distance = Math.hypot(((double)(panel.getWidth())/2)-(double)x_front,((double)(panel.getHeight())/2)-(double)y_front);
		//new velocity
		newHorSpeed = Math.abs((speed/distance)*(((double)(panel.getWidth())/2)-(double)x_front)); 
		newVerSpeed = Math.abs((speed/distance)*(((double)(panel.getHeight())/2)-(double)y_front)); 
		

		if(x_front > panel.getWidth()/2)
			x_dir = -1;
		else
			x_dir=1;
		if(y_front > panel.getHeight()/2)
			y_dir = -1;
		else
			y_dir=1;
			
		this.x_front+=newHorSpeed*x_dir;
		this.y_front+=newVerSpeed*y_dir;
			
			synchronized(this){
			
				//Checking if the object is close to the worm minimum 5 pixels
				if((Math.abs(panel.getWidth()/2-x_front)<=5) && (Math.abs(panel.getHeight()/2-y_front)<=5)){ 
					panel.WormFlag = false;//Removing the worm
					currentState.doAction(this,new Satiated());
					foodCounter =0;
					panel.repaint(); //repainting the main panel
					this.eatInc();//the fish is eating the worm and grows
			}
		}
	}
	
	/**
	 * A function which is responsible for the run of the Thread
	 */
	public void run() {
		
		while(ThreadLives) { //while objects thread is still alive
	        
	        //System.out.println("x_front:"+ x_front);
	        Thread.yield();
	        
			try {
				Thread.sleep(10); //sleep for 10 milliseconds
				
				while(isSuspended){ // if the user press on sleep button

					synchronized(this){
						wait(); // put the object on wait 
					}
				}
				
				if(this.Barrier!=null) {
					
					//System.out.println(Thread.currentThread().getName() + getColor()+ " waiting for others to complete...");
					int await = Barrier.await(); //wait for all the threads.
				}
				Barrier=null; // start going from here after await().
				
				if(panel.WormFlag!=false) { // if user pressed on food button.
					if(currentState instanceof Hungry){ 
						tryToEatTheWorm();	 //making the fish to try and eat the worm.
					}		
				}
				if(currentState instanceof Satiated) {
					
					foodCounter++;
					System.out.println(foodCounter);//checking the foodCounter increment
					if(foodCounter == foodFreq) {
									
						currentState.doAction(this,new Hungry());
						
						if(panel.WormFlag==false) {
							panel.update(this);
						}
					}
				}
				//Movement of the Fish without a worm
				this.x_front+=horSpeed*x_dir;
				this.y_front+=verSpeed*y_dir;

				if(x_front > panel.getWidth()) //if the Fish arrive to the end of the screen (x_axis)
					x_dir = -1;//changing the direction of the Fish
				if(y_front > panel.getHeight()) //if the Fish arrive to the end of the screen (y_axis)
					y_dir = -1; //changing the direction of the Fish
				if(x_front <0)
					x_dir = 1;
				if(y_front <0)
					y_dir = 1;
				
			}
			catch (BrokenBarrierException e) {
				e.printStackTrace();
			}	
			catch (InterruptedException e) {
				
				e.printStackTrace();
			} 	
		panel.repaint();//repaing the main panel 
		}
	}
	/**
	 * A function that returns the object of the copy constructor
	 */
	@Override
	public Jellyfish clone() throws CloneNotSupportedException{
		return new Jellyfish(this);
	}
	/**
	 * A function that get values and sets them to the Jellyfish
	 */
	@Override
	public void editSwimmable(int size, int horSpeed, int verSpeed, Color col) {
		this.size=size;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.col=col;
		
	}
	/**
	 * A function that gets Color and sets it to the Jellyfish
	 */
	@Override
	public void PaintFish(Color col) {
		this.col=col;
	}
	/**
	 * A function that returns the x_dir of the Jellyfish
	 */
	@Override
	public int getX_dir() {
		return x_dir;
	}
	/**
	 * A function that returns the y_dir of the Jellyfish
	 */
	@Override
	public int getY_dir() {
		return y_dir;
	}
	/**
	 * A function that returns the x_front of the Jellyfish
	 */
	@Override
	public int getXfront() {
		return x_front;
	}
	/**
	 * A function that returns the y_front of the Jellyfish
	 */
	@Override
	public int getYfront() {
		return y_front;
	}
	/**
	 * A function that returns the Color of the Jellyfish
	 */
	@Override
	public Color getColorAnimal() {
		return this.col;
	}
	/**
	 * A function that get values and sets them to the Jellyfish
	 */
	@Override
	public void setState(Color col, int size, int x_front, int y_front, int horSpeed, int verSpeed,int x_dir,int y_dir) {
		this.col=col;
		this.size=size;
		this.x_front=x_front;
		this.y_front=y_front;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.x_dir=x_dir;
		this.y_dir=y_dir;
	}
	/**
	 * A function that gets the HungerState and sets it to the Jellyfish currentState
	 */
	@Override
	public void setHungeryState(HungerState state) { 
		currentState=state;
		
	}
	/**
	 * A function that returns the currentState in a string 
	 */
	@Override
	public String getHungerState() {
		return currentState.toString();
	}
}
