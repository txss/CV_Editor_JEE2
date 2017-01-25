package fr.amu.univ.cveditor.services;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import fr.amu.univ.cveditor.entities.Person;

@Stateful(name = "connectedUser")
public class ConnectedUserBean implements ConnectedUser {
	
	@EJB
	private PersonManager pm;

	public boolean login(String login, String pwd) {
		Person p = pm.getPerson(login);
		
		//Vérification de l'utilisateur et de son mot de passe
		if(p != null && p.getPassword().equals(pwd)) {
			System.out.println("Login user " + this);
			return true;
		}
		return false;
	}//login()

	@Remove
	public void logout() {
		System.out.println("Logout user " + this);
	}//logout()
	
}//ConnectedUserBean
