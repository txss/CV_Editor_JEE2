package fr.amu.univ.cveditor.controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.codec.digest.DigestUtils;

import fr.amu.univ.cveditor.entities.Person;
import fr.amu.univ.cveditor.services.ConnectedUserManager;

@ManagedBean(name="auth")
@SessionScoped
public class AuthenticateController implements Serializable {

	private static final long serialVersionUID = 6471683296530961330L;

	private Navigation nav = new Navigation();
	
	@EJB
	private ConnectedUserManager um;
	
	@ManagedProperty(value = "#{cv}")
	private CvController cvController;
	
	
	//must povide the setter method
	public void setCvController(CvController cvController) {
		this.cvController = cvController;
	}//setCvController()
	

	public String login(String login, String pwd) {
		Person user = um.login(login, DigestUtils.sha256Hex(pwd));
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (user == null) {
			context.addMessage(null, new FacesMessage("Unknown login, try again"));
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
	
	
	public String updateConnectedUser() {
		um.getUser().setCv(cvController.getCv());
		
		um.updateUser();
		
		return nav.showCV();
	}//updateConnectedUser()
	

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
	}//listener()
	
}//AuthentificationController()