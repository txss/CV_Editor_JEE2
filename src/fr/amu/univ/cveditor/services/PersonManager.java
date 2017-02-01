package fr.amu.univ.cveditor.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Person;
import fr.amu.univ.cveditor.exceptions.BadPerson;
import fr.amu.univ.cveditor.utils.EmailValidator;
import fr.amu.univ.cveditor.utils.IValidator;
import fr.amu.univ.cveditor.utils.PswValidator;

@Stateful(name = "personManager", description = "Manager d'entités pour les personnes") 
public class PersonManager {

	private boolean isAuth = false;

	@EJB
	private ConnectedUserManager um;

	@PersistenceContext(unitName = "myPGSQLBase")
	private EntityManager em;

	@PostConstruct
	public void init() {
		if(um.getUser() != null) isAuth = true;
	}//init()

	@Remove
	public void close() {

	}//close()


	/* Interceptor */
	@AroundInvoke
	public Object interceptor(InvocationContext context) throws Exception {
		Object obj = null;

		if(isAuth)
			obj = context.proceed();
		return obj;
	}//interceptor()

	/* Members Methods */
	public List<Person> listAll() {
		return em.createQuery("SELECT * FROM \"PERSONS\"", Person.class).getResultList();
	}//getAll()

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

	public void updatePerson(Person p) {
		Person modifiedPerson;

		modifiedPerson = em.find(Person.class, p.getEmail());

		modifiedPerson.setName(p.getName());
		modifiedPerson.setFirstName(p.getFirstName());
		modifiedPerson.setBirthdate(p.getBirthdate());
		modifiedPerson.setWebSite(p.getWebSite());
		modifiedPerson.setPassword(p.getPassword());
	}//updatePerson()

	public Person getPerson(String email) {
		return em.find(Person.class, email);
	}//getPerson()

	public Person searchPerson(Person p) {
		return em.find(Person.class, p.getEmail());
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