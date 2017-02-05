package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.amu.univ.cveditor.entities.Person;
import fr.amu.univ.cveditor.services.PersonManager;

@ManagedBean(name="person")
@SessionScoped

public class PersonController implements Serializable {

	private static final long serialVersionUID = 1497101057300726477L;
	
	private Person p = new Person();
	private Navigation nav = new Navigation();

	@EJB
	private PersonManager pm;
	
	
	@PostConstruct
	public void init(){
	}//init()

	public Person getPerson() {
		return this.p;
	}//getPerson()

	public List<Person> findAll() {
		return pm.listAll();
	}//findAll()

	public String storePerson() {
		try {
			pm.create(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return nav.index();
		}
		return nav.auth();
	}//storePerson()

	public void updatePerson(){
		pm.update(p);
	}//updatePerson()


	public String show(Person p) {
		this.p = pm.search(p);
		return nav.showPerson();
	}//show()

	public String edit() {
		return nav.editPerson();
	}//edit()

}//PersonController