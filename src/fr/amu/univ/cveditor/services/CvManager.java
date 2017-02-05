package fr.amu.univ.cveditor.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Cv;

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

	public Cv find(Cv cv) {
		return em.find(Cv.class, cv.getId());
	}//find()
	
	public Cv find(Integer id) {
		return em.find(Cv.class, id);
	}//find()
	
/*
	public Cv search(Cv cv) {
		
	}//search()
*/
	public void remove(Cv cv) {
		em.remove(cv);
	}//remove()

}//CvManager