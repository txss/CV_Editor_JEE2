package fr.amu.univ.cveditor.entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CV")
public class Cv {

	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	

	@Column(name = "activites")
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
