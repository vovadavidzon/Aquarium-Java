package aquarium3;

import java.util.HashMap;

/**
 * This CareTaker class of the prototype pattern .
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class CareTaker {

	private HashMap<Integer, Memento> swimmableMementoList; 
	private HashMap<Integer, Memento> plantesMementoList; 
	private static int counter=0;
	public final int objectID;
	
	/**
	 * constructor which creates the HashMap
	 */
	public CareTaker(){
		this.objectID=++counter;
		swimmableMementoList=new HashMap<Integer, Memento>(); 
		plantesMementoList=new HashMap<Integer, Memento>(); 
		
	}
	/**
	 * A function that returns the ID
	 */
	public int getID(){return objectID;}

	/**
	 * A function that adds the ID and state of the animal to the swimmableMementoList
	 * @param idObj - the id of the animal
	 * @param state - the state of the animal
	 */
	public void addSwimmableMemento(int idObj,Memento state){
		swimmableMementoList.put(idObj,state);
	}
	
	/**
	 * A function that adds the ID and state of the plants to the plantesMementoList
	 * @param idObj - the id of the plant
	 * @param state - the state of the plant
	 */
	public void addImmobileMemento(int idObj,Memento state){
		plantesMementoList.put(idObj, state);
	}
	/**
	 * A function that returns the momento of the animals with the state
	 * @param index - the index of the momento
	 */
	public Memento getSwimmableMemento(int index){
		return swimmableMementoList.get(index);
	}
	/**
	 * A function that returns the momento of the plantes with the state
	 * @param index - the index of the momento
	 */
	public Memento getImmobileMemento(int index){
		return plantesMementoList.get(index);
	}
	public HashMap<Integer, Memento> getSwimmableMementoList(){return swimmableMementoList;}
	public HashMap<Integer, Memento>getPlantesMementoList(){return plantesMementoList;}

	
	
	
	
	
}
