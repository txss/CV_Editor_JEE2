package fr.amu.univ.cveditor.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.amu.univ.cveditor.entities.Activite;
import fr.amu.univ.cveditor.entities.Person;
import fr.amu.univ.cveditor.exceptions.BadPerson;

@Stateless(name = "personManager", description = "Manager d'entités pour les personnes") 
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


//	/* Interceptor */
//	@AroundInvoke
//	public Object interceptor(InvocationContext context) throws Exception {
//		Object obj = null;
//
//		if(um.getUser() != null)
//			obj = context.proceed();
//		return obj;
//	}//interceptor()

	/* Members Methods */
	public List<Person> listAll() {
		return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
	}//listAll()

	public void create(Person p) throws BadPerson {
		em.persist(p);
		// TODO NEGRO
//		IValidator valid = new EmailValidator();
//		if(valid.validate(p.getEmail())) {
//			throw new BadPerson("Not a valid email address");
//		}
//
//		valid = new PswValidator();
//		if(valid.validate(p.getPassword())) {
//			throw new BadPerson("Not a valid password");
//		}

	}//create()

	public Person update(Person p) {
		return em.merge(p);
	}//update()

	public Person find(String email) {
		Person p = em.find(Person.class, email);
		if(p != null)
			if(p.getCv() != null) {
				p.getCv().getId();
				p.getCv().getName();
				
				if(p.getCv().getActivites() != null) {
					p.getCv().getActivites().size();
				}
				else
					p.getCv().setActivites(new ArrayList<Activite>());
			}
		return p;
	}//find()

	public List<Person> search(String s) {
		TypedQuery<Person> q = em.createQuery(
				"SELECT p FROM Person p WHERE p.firstName LIKE :search "
				+ "OR p.name LIKE :search OR CONCAT(p.firstName,' ', p.name) LIKE :search "
				+ "OR p.email LIKE :search", Person.class);
		
		return q.setParameter("search", "%"+s+"%").getResultList();
	}//search()
	
	public boolean remove(String email) {
		Person person = em.find(Person.class, email);
		if(person != null) {
			person = em.merge(person);
			em.remove(person);
			return true;
		}
		return false;
	}//remove()

}//PersonManager