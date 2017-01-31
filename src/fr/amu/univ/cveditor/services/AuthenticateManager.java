package fr.amu.univ.cveditor.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.amu.univ.cveditor.entities.Person;

@Stateless(name = "authManager", description="Manager d'authentification utilisateur")
public class AuthenticateManager {
	
	@PersistenceContext(unitName = "myMySQLBase")
	private EntityManager em;

	public boolean login(String login, String pwd) {
		Person p = em.find(Person.class, login);
		
		if(p != null)
			if (p.getPassword().equals(pwd)) {
				System.out.println("Connected user : \n "
						+ "name : " + p.getFirstName() + " " + p.getName());
				return true;
			}
		return false;
	}//login()
	
	
	public void logout(String login) {
		Person p = em.find(Person.class, login);
		System.out.println("Disconnected user : \n "
				+ "name : " + p.getFirstName() + " " + p.getName());
	}//logout()
}//AuthenticateManager
