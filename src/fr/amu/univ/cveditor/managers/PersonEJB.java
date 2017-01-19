package fr.amu.univ.cveditor.managers;

import javax.ejb.Stateful;

import fr.amu.univ.cveditor.bean.Person;

@Stateful
public class PersonEJB {

	private Person person;
	
	// default constructor
	public PersonEJB(){}

	// Begin getters
	public Person getPerson() {
		return person;
	}

	// Begin setters
	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	// Methodes
	public boolean savePerson(Person person){
		//TODO
		return false;
	}
	
	public boolean updatePerson(Person person){
		//TODO
		return false;
	}
	
	public Person getPerson(int id){
		//TODO
		return null;
	}

	public Person searchPerson(Person person){
		//TODO
		return null;
	}
	
	public boolean deletPerson(int id){
		//TODO
		return false;
	}
}
