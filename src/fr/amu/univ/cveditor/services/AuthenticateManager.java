package fr.amu.univ.cveditor.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Person;

@Stateless(name = "authManager", description="Manager d'authentification utilisateur")
public class AuthenticateManager {
	
	@EJB
	private ConnectedUserManager um;
	
	@PersistenceContext(unitName = "myPGSQLBase")
	private EntityManager em;

	public boolean login(String login, String pwd) {
		Person p = em.find(Person.class, login);
		
		if(p != null)
			if (p.getPassword().equals(pwd)) {
				um.setUser(p);
				return true;
			}
		return false;
	}//login()
	
	
	public boolean logout(String login) {
		if(um.getUser().getEmail().equals(login)) {
			um.close();
			return true;
		}
		return false;
	}//logout()
	
}//AuthenticateManager
