package fr.amu.univ.cveditor.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.amu.univ.cveditor.services.AuthenticateManager;
import fr.amu.univ.cveditor.services.ConnectedUserManager;

@ManagedBean(name="auth")
@ApplicationScoped
public class AuthenticateController {
	
	private Navigation nav;
	
	@EJB
	private AuthenticateManager authm;
	@EJB
	private ConnectedUserManager um;
	
	public String login(String login, String pwd) {
		return authm.login(login, pwd) ? nav.account() : nav.auth();
	}//login()
	
	public String logout() {
		authm.logout(um.getUser().getEmail());
		return nav.auth();
	}//logout()
	
	public boolean isConnected() {
		if(um.getUser() != null) return true;
		return false;
	}//isConnected()
	
}//AuthentificationController()