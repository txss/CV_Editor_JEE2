package fr.amu.univ.cveditor.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.amu.univ.cveditor.services.AuthenticateManager;

@ManagedBean
@ApplicationScoped
public class AuthenticateController {
	
	private Navigation nav;
	
	@EJB
	private AuthenticateManager authm;
	
	public String login(String login, String pwd) {
		return authm.login(login, pwd) ? nav.account() : nav.auth();
	}//login()
	
	public void logout(String login) {
		authm.logout(login);
	}//logout()
	
}//AuthentificationController()