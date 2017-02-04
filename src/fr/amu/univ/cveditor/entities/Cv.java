package fr.amu.univ.cveditor.entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CV")
public class Cv {

	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name="name")
	private String name;

	@Column(name = "activites")
	@ElementCollection
	private ArrayList<Activite> activites;
	
	@Column(name="person")
	@ManyToOne
	@JoinColumn(name = "ID_PERSON")
	private Person person;
	
	/* Constructor */
	public Cv() {}

	
	/* Getters */
	public int 					getId() 		{ return id;		}
	public String				getName()		{ return name;		}
	public ArrayList<Activite> 	getActivites() 	{ return activites; }
	public Person				getPerson()		{ return person;	}

	/* Setters */
	public void setId(int id) 								{ this.id = id; 				}
	public void setName(String name)						{ this.name = name;				}
	public void setActivites(ArrayList<Activite> activites) { this.activites = activites; 	}
	public void setPerson(Person p)							{ this.person = p;				}
	
	/* Members Methods */
	public String toString(){
		return 	"\nId-> " + this.id + 
				"\nActivites-> " + this.activites;
	}//toString()
	

}//Cv
