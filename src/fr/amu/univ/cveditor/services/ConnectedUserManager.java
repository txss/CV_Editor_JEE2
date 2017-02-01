package fr.amu.univ.cveditor.services;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import fr.amu.univ.cveditor.entities.Person;

@Stateful(name="connectedUser", description="Gère les informations d'un utilisateur connecté")
public class ConnectedUserManager {
	
	private Person p = null;
	
	@PostConstruct 
	public void init(){
		System.out.println("A user is connecting...");
	}//init()
	
	@Remove
	public void close() {
		System.out.println(this.p.getFirstName() + " " + this.p.getName() 
								+ " deconnected.");
	}//close()
	
	/* Members methods */
	public Person getUser() { 
		return this.p; 
	} //getUser()
	
	public void setUser(Person p) {	
		this.p = p; 
	} //setUser()
	
}//ConnectedUserManager()