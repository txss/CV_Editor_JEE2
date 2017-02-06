package fr.amu.univ.cveditor.services;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import fr.amu.univ.cveditor.entities.Person;

@Stateful(name="connectedUser", description="Gère les informations d'un utilisateur connecté")
public class ConnectedUserManager {
	
	private Person p = null;
	
	@EJB
	private AuthenticateManager auth;
	@EJB
	private PersonManager pm;
	
	@PostConstruct 
	public void init(){
		System.out.println("A user is connecting...");
	}//init()
	
	@Remove
	public void close() {
		System.out.println(this.p.getFirstName() + " " + this.p.getName() 
								+ " deconnected.");
	}//close()
	
	/* Getter */
	public Person getUser() { 
		return this.p; 
	} //getUser()
	
	
	/* Members methods */
	public Person login(String login, String password) {
		this.p = auth.login(login, password);
		return this.p;
	}//login()
	
	public void logout() {
		this.p = null;
	}//logout()
	
	public void updateUser() {
		pm.update(p);
	}//updateUser()
	
	public void setBirthdate(String birthdate){
		this.p.setBirthdate(birthdate);
		updateUser();
	}
	
	
}//ConnectedUserManager()