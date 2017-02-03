package fr.amu.univ.cveditor.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.amu.univ.cveditor.entities.Person;
import fr.amu.univ.cveditor.exceptions.BadPerson;
import fr.amu.univ.cveditor.services.PersonManager;

@ManagedBean(name="person")
@SessionScoped
public class PersonController {
	
	@EJB
	private PersonManager pm;
	
	public List<Person> getAllPerson() {
		return pm.listAll();
	}//getAllPerson()
	
	public Person getPerson(String email) {
		return pm.find(email);
	}//getPerson()
	
	public void setPerson(Person p) throws BadPerson {
		pm.create(p);
	}//setPerson()
	
	public void updatePerson(Person p){
		pm.update(p);
	}//updatePerson()
	
}//PersonController