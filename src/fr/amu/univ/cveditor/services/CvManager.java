package fr.amu.univ.cveditor.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Activite;
import fr.amu.univ.cveditor.entities.Cv;
import fr.amu.univ.cveditor.entities.Person;

@Stateless(name="cvManager", description = "Manager d'entités pour les CV")
public class CvManager {

	@PersistenceContext(unitName = "myPGSQLBase")
	private EntityManager em;

	@PostConstruct
	public void init() {
	}//init()

	@Remove
	public void close() {
	}//close()

	

	/* Members Methods */
	public List<Cv> listAll() {
		return em.createQuery("SELECT * FROM \"CV\"", Cv.class).getResultList();
	}//listAll()

	public void store(Cv cv) {
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
	public void remove(Integer idCv, String emailPerson) {
		Cv updatedCv = em.find(Cv.class, idCv);
		Person updatedPerson = em.find(Person.class, emailPerson);
		updatedCv.setActivites(new ArrayList<Activite>());
		updatedCv = em.merge(updatedCv);
		em.remove(updatedCv);
		
//		person.setCv(null);
//		person = em.merge(person);
		updatedPerson.setCv(null);
	}//remove()

}//CvManager