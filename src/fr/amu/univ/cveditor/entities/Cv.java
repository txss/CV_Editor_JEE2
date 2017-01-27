package fr.amu.univ.cveditor.entities;

import java.util.ArrayList;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cv {

	@Id
	public int id;
	

	@ElementCollection
	public ArrayList<Activite> activites;
	
	/* Constructor */
	public Cv() {}

	
	/* Getters */
	public int 					getId() 		{ return id;		}
	public ArrayList<Activite> 	getActivites() 	{ return activites; }

	/* Setters */
	public void setId(int id) 								{ this.id = id; 				}
	public void setActivites(ArrayList<Activite> activites) { this.activites = activites; 	}
	
	/* Members Methods */
	public String toString(){
		return 	"\nId-> " + this.id + 
				"\nActivites-> " + this.activites;
	}//toString()
	

}//Cv
