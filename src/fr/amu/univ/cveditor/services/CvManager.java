package fr.amu.univ.cveditor.services;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Cv;
import fr.amu.univ.cveditor.entities.Person;
import fr.amu.univ.cveditor.exceptions.NotAuthenticate;

@Stateless(name="cvManager", description = "Manager d'entité pour les CV")
public class CvManager {
	
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
	public Object interceptor(InvocationContext context) throws NotAuthenticate {
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
	
	public void createCv(Cv cv){
		em.persist(cv);
	}// saveCv()

	public void updateCv(Cv cv){
		Cv modifiedCv = em.find(Cv.class, cv.getId());
		
		modifiedCv.setActivites(cv.getActivites());
	}// updateCv()

	public Cv getCv(int id){
		return em.find(Cv.class, id);
	}// getCvById()

	public Cv searchCv(Cv cv){
		//TODO
		return null;
	}//searchCv()

	public boolean removeCv(int id){
		Cv cv = em.find(Cv.class, id);
		if(cv != null) {
			em.remove(cv);
			return true;
		}
		return false;
	}// deleteCvById()

}//CvEJB
