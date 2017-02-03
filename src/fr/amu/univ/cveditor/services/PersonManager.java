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

	@EJB
	private ConnectedUserManager um;

	@PersistenceContext(unitName = "myPGSQLBase")
	private EntityManager em;

	@PostConstruct
	public void init() {
	}//init()

	@Remove
	public void close() {
	}//close()


	/* Interceptor */
	@AroundInvoke
	public Object interceptor(InvocationContext context) throws Exception {
		Object obj = null;

		if(um.getUser() != null)
			obj = context.proceed();
		return obj;
	}//interceptor()

	/* Members Methods */
	public List<Person> listAll() {
		return em.createQuery("SELECT * FROM \"PERSONS\"", Person.class).getResultList();
	}//listAll()

	public void create(Person p) throws BadPerson {
		em.persist(p);

		IValidator valid = new EmailValidator();
		if(valid.validate(p.getEmail())) {
			throw new BadPerson("Not a valid email address");
		}

		valid = new PswValidator();
		if(valid.validate(p.getPassword())) {
			throw new BadPerson("Not a valid password");
		}

	}//create()

	public void update(Person p) {
		Person modifiedPerson;

		modifiedPerson = em.find(Person.class, p.getEmail());

		modifiedPerson.setName(p.getName());
		modifiedPerson.setFirstName(p.getFirstName());
		modifiedPerson.setBirthdate(p.getBirthdate());
		modifiedPerson.setWebSite(p.getWebSite());
		modifiedPerson.setPassword(p.getPassword());
	}//update()

	public Person find(String email) {
		return em.find(Person.class, email);
	}//find()

	public Person search(Person p) {
		return em.find(Person.class, p.getEmail());
	}//search()

	public boolean remove(String email) {
		Person person = em.find(Person.class, email);
		if(person != null) {
			em.remove(person);
			return true;
		}
		return false;
	}//remove()

}//PersonManager