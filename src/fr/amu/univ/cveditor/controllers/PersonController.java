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

	private Person p;
	private Navigation nav;

	@EJB
	private PersonManager pm;

	public Person getPerson(String email) {
		return this.p;
	}//getPerson()

	public List<Person> findAll() {
		return pm.listAll();
	}//findAll()

	public void storePerson(Person p) throws BadPerson {
		pm.create(p);
	}//storePerson()

	public void updatePerson(Person p){
		pm.update(p);
	}//updatePerson()


	public String show(String email) {
		p = pm.find(email);
		return nav.showPerson();
	}//show()

	public String edit() {
		return nav.editPerson();
	}//edit()

	public String newPerson() {
		p = new Person();
		return nav.editPerson();
	}//newPerson()

}//PersonController