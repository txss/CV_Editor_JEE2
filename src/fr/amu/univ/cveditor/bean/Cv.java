package fr.amu.univ.cveditor.bean;

import java.util.ArrayList;

public class Cv {

	public int id;
	public ArrayList<Activite> activites;
	
	// default constructor
	public Cv() {}

	
	// Begin getters
	public int getId() {
		return id;
	}

	public ArrayList<Activite> getActivites() {
		return activites;
	}

	// Begin setters
	public void setId(int id) {
		this.id = id;
	}

	public void setActivites(ArrayList<Activite> activites) {
		this.activites = activites;
	}
	
	public String toString(){
		return 	"\nId-> " + this.id + 
				"\nActivites-> " + this.activites;
	}
	

}
