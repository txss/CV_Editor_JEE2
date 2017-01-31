package fr.amu.univ.cveditor.services;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Person;
import fr.amu.univ.cveditor.utils.EmailValidator;
import fr.amu.univ.cveditor.utils.IValidator;
import fr.amu.univ.cveditor.utils.PswValidator;

@Stateful(name = "personManager", description = "Manager d'entité pour les personnes") 
public class PersonManager {

	private Person p;
	private boolean isAuth = false;

	@EJB
	private AuthenticateManager authManager;

	@PersistenceContext(unitName = "myPGSQLBase")
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		if(!isAuth)
			isAuth = authManager.login(p.getEmail(), p.getPassword());
	}//init()

	@Remove
	public void close() {
		authManager.logout(p.getEmail());
	}//close()


	/* Interceptor */
	@AroundInvoke
	public Object interceptor(InvocationContext context) throws Exception {
		Object obj = null;
		try {
			if(isAuth)
				obj = context.proceed();
		}
		catch (Exception e) {
			
		}
		return obj;
	}//interceptor()

	/* Members Methods */
	public void createPerson(Person p) throws BadPerson {
		em.persist(p);

		IValidator valid = new EmailValidator();
		if(valid.validate(p.getEmail())) {
			throw new BadPerson("Not a valid email address");
		}

		valid = new PswValidator();
		if(valid.validate(p.getPassword())) {
			throw new BadPerson("Not a valid password");
		}

	}//createPerson()

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