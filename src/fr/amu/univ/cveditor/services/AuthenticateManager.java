package fr.amu.univ.cveditor.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Person;

@Stateless(name = "authManager", description="Manager d'authentification utilisateur")
public class AuthenticateManager {
	
	@PersistenceContext(unitName = "myPGSQLBase")
	private EntityManager em;

	public Person login(String login, String pwd) {
		Person p = em.find(Person.class, login);
		
		if(p != null)
			if (p.getPassword().equals(pwd)) {
				return p;
			}
		return null;
	}//login()
	
	
	/*public Person logout() {
		return null;
	}//logout()*/
	
}//AuthenticateManager
