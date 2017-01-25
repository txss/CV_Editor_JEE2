package fr.amu.univ.cveditor.services;

import javax.ejb.Remote;

@Remote
public interface ConnectedUser {
	public boolean login(String login, String pwd);
	
	public void logout();
}
