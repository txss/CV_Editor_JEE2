package fr.amu.univ.cveditor.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Activite;
import fr.amu.univ.cveditor.entities.Cv;
import fr.amu.univ.cveditor.entities.Person;

@Stateful(name="cvManager", description = "Manager d'entités pour les CV")
public class CvManager {

	@PersistenceContext(unitName = "myPGSQLBase")
	private EntityManager em;

	@PostConstruct
	public void init() {
	}//init()

	@Remove
	public void close() {
	}//close()


	/* Interceptor */
	/*@AroundInvoke
	public Object interceptor(InvocationContext context) throws Exception {
		Object obj = null;
		if(um.getUser() != null)
			obj = context.proceed();
		return obj;
	}//interceptor()*/


	/* Members Methods */
	public List<Cv> listAll() {
		return em.createQuery("SELECT * FROM \"CV\"", Cv.class).getResultList();
	}//listAll()

	public void create(Cv cv) {
		em.persist(cv);
	}//create()

	public void update(Cv cv) {
		em.merge(cv);
	}//update()


	public Cv find(Integer id) {
		Cv cv = em.find(Cv.class, id);
		if(cv.getActivites() != null)
			cv.getActivites().size();
		if(cv.getPerson() != null)
			cv.getPerson().getEmail();
		
		return cv;
	}//find()
	
/*
	public Cv search(Cv cv) {
		
	}//search()
*/
	public void remove(Cv cv, Person person) {
		cv.setActivites(new ArrayList<Activite>());
		cv = em.merge(cv);
		em.remove(cv);
		
//		person.setCv(null);
//		person = em.merge(person);
		Person p1 = em.find(Person.class, person.getEmail());
		p1.setCv(null);
	}//remove()

}//CvManager