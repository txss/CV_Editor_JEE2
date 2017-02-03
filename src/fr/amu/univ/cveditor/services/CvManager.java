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

import fr.amu.univ.cveditor.entities.Cv;

@Stateful(name="cvManager", description = "Manager d'entités pour les CV")
public class CvManager {

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
	public List<Cv> listAll() {
		return em.createQuery("SELECT * FROM \"CV\"", Cv.class).getResultList();
	}//listAll()

	public void create(Cv cv) {
		em.persist(cv);
	}//create()

	public void update(Cv cv) {
		Cv modifiedCv = em.find(Cv.class, cv.getId());

		modifiedCv.setActivites(cv.getActivites());
	}//update()

	public Cv find(int id) {
		return em.find(Cv.class, id);
	}//find()

	public Cv search(Cv cv) {
		return em.find(Cv.class, cv.getId());
	}//search()

	public boolean remove(int id) {
		Cv cv = em.find(Cv.class, id);
		if(cv != null) {
			em.remove(cv);
			return true;
		}
		return false;
	}//remove()

}//CvManager