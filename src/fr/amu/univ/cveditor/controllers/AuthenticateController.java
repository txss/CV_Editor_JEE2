package fr.amu.univ.cveditor.controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;

import fr.amu.univ.cveditor.entities.Person;
import fr.amu.univ.cveditor.services.ConnectedUserManager;

@ManagedBean(name="auth")
@SessionScoped
public class AuthenticateController implements Serializable {

	private static final long serialVersionUID = 6471683296530961330L;

	private Navigation nav = new Navigation();
	
	@EJB
	private ConnectedUserManager um;
	
	@PostConstruct
	public void init() {
	}//init()
	
	
	public String login(String login, String pwd) {
		Person user = um.login(login, DigestUtils.sha256Hex(pwd));
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (user == null) {
			context.addMessage(null, new FacesMessage("Login ou mot de passe incorrect, Veuillez réessayer"));
			return null;
		}
		return nav.account();
	}//login()

	public String logout() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		um.logout();
		return nav.auth();
	}//logout()

	public boolean isConnected() {
		return um.getUser() != null;
	}//isConnected()
	
	public Person getConnectedUser() {
		return um.getUser();
	}//getConnectedUser()
	
	public Person updateConnectedUser() {
		return um.updateUser();
	}//updateConnectedUser()
	
	
	/* Listeners */
	public void redirectToAuth(ComponentSystemEvent event) {
		if(!isConnected()) {
			FacesContext fc = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler cNav
			= (ConfigurableNavigationHandler)
			fc.getApplication().getNavigationHandler();

			cNav.performNavigation("authenticate.xhtml");
		}
	}//redirectToAuth()
	
	public void listener(AjaxBehaviorEvent event) {
		updateConnectedUser();
		RequestContext requestContext = RequestContext.getCurrentInstance();  
		requestContext.execute("$( '#editForm' ).hide()");
	}//listener()
	
	public boolean isCv(){
		return um.getUser().getCv() != null ? true : false;
	}//isCv()
	
}//AuthentificationController()