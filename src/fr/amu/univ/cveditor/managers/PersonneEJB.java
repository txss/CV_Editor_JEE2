package fr.amu.univ.cveditor.managers;

import javax.ejb.Stateful;

import fr.amu.univ.cveditor.bean.Personne;

@Stateful
public class PersonneEJB {

	private Personne person;
	
	// default constructor
	public PersonneEJB(){}

	// Begin getters
	public Personne getPerson() {
		return person;
	}

	// Begin setters
	public void setPerson(Personne person) {
		this.person = person;
	}
	
	
	// Methodes
	public boolean savePersonne(Personne person){
		//TODO
		return false;
	}
	
	public boolean updatePersonne(Personne person){
		//TODO
		return false;
	}
	
	public Personne getPersonne (int id){
		//TODO
		return null;
	}

	public Personne searchPersonne(Personne person){
		//TODO
		return null;
	}
	
	public boolean deletPersonne(int id){
		//TODO
		return false;
	}
}
