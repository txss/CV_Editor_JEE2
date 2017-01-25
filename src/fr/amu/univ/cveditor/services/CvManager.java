package fr.amu.univ.cveditor.services;

import java.util.ArrayList;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Activite;
import fr.amu.univ.cveditor.entities.Cv;

public class CvManager {
	
	@PersistenceContext(unitName = "myMySQLBase")
	private EntityManager em;
	
	@PreDestroy
	public void close() {
		System.out.println("CvEJB is closing...");
	}//close()


	/* Members Methods */
	
	public void createCv(int id, ArrayList<Activite> activites) {
		
		Cv cv = new Cv();
		cv.setId(id);
		cv.setActivites(activites);
		
		em.persist(cv);
	}//createCv()
	
	public void saveCv(Cv cv){
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
