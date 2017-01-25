package fr.amu.univ.cveditor.services;

import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Person;

@Stateful
public class PersonManager {
	
	@PersistenceContext(unitName = "myMySQLBase")
	private EntityManager em;
	
	@PreDestroy
	public void close() {
		System.out.println("PersonEJB is closing...");
	}//close()

	
	/* Members Methods */
	
	public void savePerson(Person person) {
		em.persist(person);
	}//savePerson()
	
	public void updatePerson(Person person) {
		Person modifiedPerson;
		
		modifiedPerson = em.find(Person.class, person.getEmail());
		
		modifiedPerson.setName(person.getName());
		modifiedPerson.setFirstName(person.getFirstName());
		modifiedPerson.setBirthdate(person.getBirthdate());
		modifiedPerson.setWebSite(person.getWebSite());
		modifiedPerson.setPassword(person.getPassword());
	}//updatePerson()
	
	public Person getPerson(String email) {
		return em.find(Person.class, email);
	}//getPersonByEmail()

	public Person searchPerson(Person person) {
		//TODO
		return null;
	}//searchPerson()
	
	public boolean removePerson(String email) {
		Person person = em.find(Person.class, email);
		if(person != null) {
			em.remove(person);
			return true;
		}
		return false;
	}//deletePersonByEmail()

}//PersonEJB