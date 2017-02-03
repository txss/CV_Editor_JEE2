package fr.amu.univ.cveditor.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.amu.univ.cveditor.services.ConnectedUserManager;

@ManagedBean(name="auth")
@SessionScoped
public class AuthenticateController {
	
	private Navigation nav;
	
	@EJB
	private ConnectedUserManager um;
	
	public String login(String login, String pwd) {
		return um.login(login, pwd) != null ? nav.account() : nav.auth();
	}//login()
	
	public String logout() {
		um.logout();
		return nav.auth();
	}//logout()
	
	public boolean isConnected() {
		return um.getUser() != null;
	}//isConnected()
	
}//AuthentificationController()