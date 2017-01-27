package fr.amu.univ.cveditor.services;

import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful(name = "authManager")
public class AuthenticateManager {

	public boolean login(String login, String pwd) {
		return false;
	}//login()
	
	@Remove
	public void logout() {
	
	}//logout()
}//AuthenticateManager
