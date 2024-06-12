package com.Jeshna;

import java.util.Objects;
import com.Jeshna.Patient;
import com.Jeshna.PriorityQueue;

/**
 * @author JeshnaKanduri
 * @date 11/06/2024
 * Unit 1 Lab- appropriately asesssing and treating patients according to their priority using LinkedLists, and Queues
 */
public class PriorityQueue {
	
	/**
	 * Instance variable for list of patients
	 */
    private WLinkedList<Patient> list;
    
    /**
	 * Instance variable for highest priority patient
	 */
    private Patient highestPriority;
    
    /**
	 * Instance variable for size
	 */
    private int size; 
  
    /**
     * A default constructor that creates highest priority patient and new list to store patients
     */
    public PriorityQueue() {
    	list = new WLinkedList<Patient>();
    	highestPriority = new Patient(); 
    }
    
    /**
     * Adds incoming patient to list and sets as the highest priority patient if needed 
     * @param name Patient's name
     * @param priority Integer value indicating urgency of patient's condition
     */
    public void enqueue(String name, int priority) {
    	size++;
    	Patient newPatient = new Patient(name, priority);
    	list.addToBackSlow(newPatient);
    	
    	if(newPatient.priority() > highestPriority.priority()) {
    		highestPriority = newPatient;
    	}
    }
    
    /**
     * Removes and diplays name of first patient in the list
     * @return Patient's name
     * @throws Exception Cannot look at the front of an empty list
     */
    public String dequeue() throws Exception {
    	size--;
    	Patient nextPatient = list.lookAtFront();
		list.removeFromFront();
		
		if(nextPatient.equals(highestPriority)) {
			highestPriority.delete();
		}
		return nextPatient.name();
    }
  
    /**
     * Displays the name of the first patient in the list
     * @return Patient's name
     * @throws Exception Cannot look at the front of an empty list
     */
    public String peek() throws Exception {
    	Patient nextPatient = list.lookAtFront();
    	return nextPatient.name();
    }
    
    /**
     * Checks whether or not the patient list is empty
     * @return Boolean value displaying whether or not the patient list is empty
     */
    public boolean isEmpty() {
    	return list.isEmpty();
    }

    /**
     * Calculates size of the patient list based on whether a patient was added or removed
     * @return Integer value displaying size of patient list
     */
    public int size() {
    	return size;
    }
    
   /**
    * Displays patient list as a string
    * @return String of patient list
    */
    public String toString()  {
    	return list.toString();
	}
 
    /**
     * Retrieves and displays the highest priority patient
     * @return Name of the highest priority patient
     */
    public String getHighestPriorityPatient() {
    	return this.highestPriority.name();
    }

// ----------------------- MAIN -------------------------------
  /**
   * Main method
   * @param args Command line agruments 
   * @throws Exception Cannot look at the front of an empty list
   */
    public static void main(String[] args) throws Exception {
    
    	//Creating and filling up new Patient list
        PriorityQueue hospitalLineup = new PriorityQueue();
        hospitalLineup.enqueue("Habib", 9000);
        hospitalLineup.enqueue("Laura", 4);
        hospitalLineup.enqueue("George", 10);
        hospitalLineup.enqueue("Linda", 9);

        // Displaying Patient list
        System.out.println(hospitalLineup);
   
        // Displaying and removing the current highest priority patient 
        System.out.println(hospitalLineup.getHighestPriorityPatient());
        String next = hospitalLineup.dequeue();
        System.out.println(next);
        
        // Adding new patient 
        hospitalLineup.enqueue("GiJoe", 10_000_000);
        
        // Displaying new highest priority patient
        System.out.println(hospitalLineup.getHighestPriorityPatient());
    }
}
 // -----------------------	 PATIENT CLASS ------------------------------- 

/**
 * Custom class creating a patient's attributes
 */
class Patient {
	
	/**
	 * Instance variable for name
	 */
    private String name;
    
    /**
	 * Instance variable for priority
	 */
    private int priority;
    
    /**
	 * Instance variable for isEmpt method
	 */
    private boolean isEmpty;

    /**
     * Default constructor setting defaults of the instance variables if no input is received 
     */
    public Patient() {
        this.name = "";
        this.priority = -1;
        this.isEmpty = true;
    }
  
    /**
     * Creating new patient with name and priority 
     * @param first Patient's name
     * @param second Integer value indicating urgency of patient's condition
     */
    public Patient(String first, int second) {
        this.name = first;
        this.priority = second;
        this.isEmpty = false;
    }
    
    /**
     * Displays name of patient
     * @return Patient's name
     */
    public String name() {
        return this.name;
    }
  
    /**
     * Displays priority value of patient's condition
     * @return Patient's priority
     */
    public int priority() {
        return this.priority;
    }
    
  /**
   * Deletes patient with priority of -1 rendering the patient class empty (null patient)
   */
    public void delete() {
        this.priority = -1;
        this.isEmpty = true;
    }
  
    /**
     * Checks whether or not patient class is empty
     * @return Boolean value displaying whether or not the patient class is empty
     */
    public boolean isEmpty() {
        return isEmpty;
    }
   
    @Override
   /**
    * Determining if two patients are acutally the same patient
    * @param obj Patient class 
    */
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Patient)) return false;
        Patient o = (Patient) obj;
        return Objects.equals(name, o.name)
                && Objects.equals(this.priority, o.priority);
    }
    
    @Override
    /**
     * Displays name and priority value of a patient as a String
     * @return String of patient's name and priority value
     */
    public String toString() {
        return "<" + this.name +", " + this.priority + ">";
    }
       
}