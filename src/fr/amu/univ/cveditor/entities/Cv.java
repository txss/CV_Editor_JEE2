package fr.amu.univ.cveditor.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CV")
public class Cv {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name="name")
	private String name;

	@Column(name = "activites")
	@ElementCollection
	private List<Activite> activites;
	

	@OneToOne(mappedBy="cv")
	private Person person;
	
	/* Constructor */
	public Cv() {}

	
	/* Getters */
	public int 				getId() 		{ return id;		}
	public String			getName()		{ return name;		}
	public List<Activite> 	getActivites() 	{ return activites; }
	public Person			getPerson()		{ return person;	}

	/* Setters */
	public void setId(int id) 							{ this.id = id; 				}
	public void setName(String name)					{ this.name = name;				}
	public void setActivites(List<Activite> activites)	{ this.activites = activites; 	}
	public void setPerson(Person p)						{ this.person = p;				}
	
	/* Members Methods */
	public String toString(){
		return 	"\nId-> " + this.id + 
				"\nName-> " + this.name +
				"\nActivites-> " + this.activites;
	}//toString()
	

}//Cv
