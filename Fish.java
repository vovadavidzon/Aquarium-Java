package aquarium3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * A class of a Fish that extends the Swimmable class.
 * 
 * @version 1.0  26/3/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class Fish extends Swimmable implements MarineAnimal {
	private int size; // The size of the fish.
	private Color col; //The Color of the fish.
	private int eatCount; //The amount of food a fish ate.
	private int x_front, y_front, x_dir, y_dir;
	private AquaPanel panel; 
	private String animal_color;
	private boolean isSuspended=false,ThreadLives = true ;
	private CyclicBarrier Barrier;
	private double newHorSpeed,newVerSpeed;
	private HungerState currentState;
	private int foodCounter=0;
	
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
	public Fish(AquaPanel panel,int size ,int x_front ,int y_front ,int horSpeed ,int verSpeed ,Color col,int foodFreq) {
		super(horSpeed,verSpeed,foodFreq); 
		this.panel = panel;
		eatCount=0; x_dir=1; y_dir=1;
		this.size=size;
		this.col=col;
		this.x_front=0;
		this.y_front=0;
		currentState=new Satiated();
	}
	/**
	 * A defaoult constructor that initializes the values of the fish to 0 or 1.
	 */
	public Fish() {
		super();
		eatCount=0; x_dir=1; y_dir=1;
		this.size=0;
		this.col=Color.BLACK;
		this.x_front=0;
		this.y_front=0;
	}
	/**
	 * A copy constructor that copyes all Fish fields of other object to the current object .
	 * 
	 * @param other -other Fish object.
	 */
	public Fish(Fish other) {
		super(other);
		if(other != null) {
			panel= other.panel;
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
	 * A string function that returns a string with the animal name ,the color of the Fish,
	 * the size of the Fish and the amount of food he ate.
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
	 * @return returning the amount of food a fish ate.
	 */
	@Override
	public int getEatCount() {
		return eatCount;
	}
	/**
	 * @return returning the size of the food.
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * A function that changes the size of the fish by 1.
	 */
	public void changeFish() {
		this.size +=1;
	}
	
	/**
	 * A function that feeds the fish and 
	 * changes the Fish size.
	 */
	@Override
	public void eatInc() {
		this.eatCount +=1;
		changeFish();
			
	}
	
	/**
	 * A boolean function that checks if 2 objects are eqaual
	 * 
	 * @return true if the objects are eqaual else : false
	 */
	public boolean equals(Object other) {
		boolean ans = false;
		if(other instanceof Fish) {
			ans =( size == ((Fish)other ).size &&  col ==((Fish)other).  col &&   eatCount ==((Fish)other).  eatCount&&
			x_front ==((Fish)other).  x_front && y_front ==((Fish)other).  y_front && x_dir ==((Fish)other).  x_dir && 
			y_dir ==((Fish)other).  y_dir);
		}
		return ans;
	}
	/**
	 * A boolean function that gets a size and sets
	 * it to the Fish size.
	 * 
	 * @param size - the size of the Fish.
	 * @return true if the size set is proper else :false.
	 */
	public boolean setSize(int size){
		boolean isChanged = false;
		if (size>=1) {
			this.size = size;
			isChanged = true;
		}
		return isChanged;
	}
	
	/**
	 * A boolean function that gets EatCount and sets
	 * it to the Fish EatCount.
	 * 
	 * @param eatCount - The amount of food a fish ate.
	 * @return true if the EatCount set is proper else :false.
	 */
	public boolean setEatCount(int eatCount){
		boolean isChanged = false;
		if (eatCount>=0 ) {
			this.eatCount = eatCount;
			isChanged = true;
		}
		return isChanged;
	}
	/**
	 * A boolean function that gets x_front and sets
	 * it to the Fish x_front.
	 * 
	 * @param x_front - the x_front of the fish.
	 * @return true if the x_front set is proper else :false.
	 */
	public boolean setX_front(int x_front){
		boolean isChanged = false;
		if (x_front>=0 ) {
			this.x_front = x_front;
			isChanged = true;
		}
		return isChanged;
	}
	/**
	 * A boolean function that gets y_front and sets
	 * it to the Fish y_front.
	 * 
	 * @param y_front - the y_front of the fish.
	 * @return true if the y_front set is proper else :false.
	 */
	public boolean setY_front(int y_front){
		boolean isChanged = false;
		if (y_front>=0 ) {
			this.y_front = y_front;
			isChanged = true;
		}
		return isChanged;
	}
	/**
	 * A boolean function that gets x_dir and sets
	 * it to the Fish x_dir.
	 * 
	 * @param x_dir - the x_dir of the fish.
	 * @return true if the x_dir set is proper else :false.
	 */
	public boolean setX_dir(int x_dir){
		boolean isChanged = false;
		if (x_dir>=1 ) {
			this.x_dir = x_dir;
			isChanged = true;
		}
		return isChanged;
	}
	/**
	 * A boolean function that gets y_dir and sets
	 * it to the Fish y_dir.
	 * 
	 * @param y_dir - the y_dir of the fish.
	 * @return true if the y_dir set is proper else :false.
	 */
	public boolean setY_dir(int y_dir){
		boolean isChanged = false;
		if (y_dir>=1 ) {
			this.y_dir = y_dir;
			isChanged = true;
		}
		return isChanged;
	}
	
	/**
	 * A function Which draws the fish in the main panel
	 */
	@Override
	public void drawCreature(Graphics g){
	   g.setColor(col);
	   if(x_dir==1) // fish swims to right side
	   {
		// Body of fish
		g.fillOval(x_front - size, y_front - size/4, size, size/2);

		// Tail of fish
		int[] x_t={x_front-size-size/4,x_front-size-size/4,x_front-size};
		int [] y_t = {y_front - size/4, y_front + size/4, y_front};
		Polygon t = new Polygon(x_t,y_t,3);		
		g.fillPolygon(t);

		// Eye of fish
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255- col.getBlue()));
		g2.fillOval(x_front-size/5, y_front-size/10, size/10, size/10);
				
		// Mouth of fish
		if(size>70)
			g2.setStroke(new BasicStroke(3));
		else if(size>30)
			g2.setStroke(new BasicStroke(2));
		else
			g2.setStroke(new BasicStroke(1));
	      g2.drawLine(x_front, y_front, x_front-size/10, y_front+size/10);
	      g2.setStroke(new BasicStroke(1));
	   }
	   else // fish swims to left side
	   {
		// Body of fish
		g.fillOval(x_front, y_front - size/4, size, size/2);

		// Tail of fish
		int[] x_t={x_front+size+size/4,x_front+size+size/4,x_front+size};
		int [] y_t = {y_front - size/4, y_front + size/4, y_front};
		Polygon t = new Polygon(x_t,y_t,3);		
		g.fillPolygon(t);

		// Eye of fish
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255-col.getBlue()));
		g2.fillOval(x_front+size/10, y_front-size/10, size/10, size/10);
				
		// Mouth of fish
		if(size>70)
			g2.setStroke(new BasicStroke(3));
		else if(size>30)
			g2.setStroke(new BasicStroke(2));
		else
			g2.setStroke(new BasicStroke(1));
	      g2.drawLine(x_front, y_front, x_front+size/10, y_front+size/10);
	      g2.setStroke(new BasicStroke(1));
	   }
	}

	/**A function Which sets the isSuspended flag to true and it causes to suspend the fish object **/
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
	/**set the barrier that is sent by AquaPanel class **/
	@Override
	public void setBarrier(CyclicBarrier b) {
		this.Barrier = b;

	}
	
	/**
	 * A fucntion that returns the animal_color (String).
	 * @return animal_color - The color of the fish
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
	 * A function which causes the fish to try and eat the worm .
	 */
	public void tryToEatTheWorm() {
		// changing the drection and speed of the fish to the center of the panel

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
				this.eatInc(); //the fish is eating the worm and grows
			}
		}	
	}
	
	/**
	 * A function which is responsible for the run of the Thread
	 */
	public void run() {
		
		while(ThreadLives) { //while objects thread is still alive
	        
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
				if(currentState instanceof Satiated) { //if fish is Satiated
					
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
		panel.repaint(); //repating the main panel 
		}
	}
	/**
	 * A function that returns the object of the copy constructor
	 */
	@Override
	public Object clone() throws CloneNotSupportedException{
		return new Fish(this);
	}
	
	/**
	 * A function that get values and sets them to the fish
	 */
	@Override
	public void editSwimmable(int size, int horSpeed, int verSpeed, Color col) {
		this.size=size;
		this.horSpeed=horSpeed;
		this.verSpeed=verSpeed;
		this.col=col;
		
	}
	/**
	 * A function that gets Color and sets it to the fish
	 */
	@Override
	public void PaintFish(Color col) {
		this.col=col;
	}
	/**
	 * A function that returns the x_dir of the fish
	 */
	@Override
	public int getX_dir() {
		return x_dir;
	}
	/**
	 * A function that returns the y_dir of the fish
	 */
	@Override
	public int getY_dir() {
		return y_dir;
	}
	/**
	 * A function that returns the x_front of the fish
	 */
	@Override
	public int getXfront() {
		return x_front;
	}
	/**
	 * A function that returns the y_front of the fish
	 */
	@Override
	public int getYfront() {
		return y_front;
	}
	/**
	 * A function that returns the Color of the fish
	 */
	@Override
	public Color getColorAnimal() {
		return this.col;
	}
	/**
	 * A function that get values and sets them to the fish
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
	 * A function that gets the HungerState and sets it to the fish currentState
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
