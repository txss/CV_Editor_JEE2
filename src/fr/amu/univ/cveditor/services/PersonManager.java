package fr.amu.univ.cveditor.services;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Person;
import fr.amu.univ.cveditor.utils.EmailValidator;
import fr.amu.univ.cveditor.utils.IValidator;
import fr.amu.univ.cveditor.utils.PswValidator;

@Stateless(name = "personManager", description = "Manager d'entité pour les personnes") 
public class PersonManager {
	
	@EJB
	private AuthenticateManager authManager;

	@PersistenceContext(unitName = "myMySQLBase")
	private EntityManager em;

	@PreDestroy
	public void close() {
		System.out.println("PersonEJB is closing...");
	}//close()


	/* Members Methods */

	public void createPerson(Person p) throws BadPerson {

		Person person = new Person();
		person.setEmail(p.getEmail());
		person.setFirstName(p.getFirstName());
		person.setName(p.getName());
		person.setBirthdate(p.getBirthdate());
		person.setWebSite(p.getWebSite());
		person.setPassword(p.getPassword());
		
		em.persist(person);

		IValidator valid = new EmailValidator();
		if(valid.validate(p.getEmail())) {
			throw new BadPerson("Not a valid email address");
		}
		
		valid = new PswValidator();
		if(valid.validate(p.getPassword())) {
			throw new BadPerson("Not a valid password");
		}
		
	}//createPerson()

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
	}//getPerson()

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
	}//deletePerson()

}//PersonEJB