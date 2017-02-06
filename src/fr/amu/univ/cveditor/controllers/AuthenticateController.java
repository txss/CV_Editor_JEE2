package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
	private String date = "";
	
	@EJB
	private ConnectedUserManager um;
	
	@ManagedProperty(value = "#{cv}")
	private CvController cvController;
	
	
	//must povide the setter method
	public void setCvController(CvController cvController) {
		this.cvController = cvController;
	}//setCvController()
	

	public String login(String login, String pwd) {
		return um.login(login, DigestUtils.sha256Hex(pwd)) != null ? 
				nav.account() : nav.auth();
	}//login()

	public String logout() {
		um.logout();
		return nav.auth();
	}//logout()

	public boolean isConnected() {
		return um.getUser() != null;
	}//isConnected()
	
	public Person getConnectedUser() {
		return um.getUser();
	}//getConnectedUser()
	
	public void setDate(String date){
		this.date = date;
		um.setBirthdate(date);
		updateConnectedUser();
	}
	
	public String getDate(){
		return getConnectedUser().getBirthdate();
	}
	
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

	public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         System.out.println("click");
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
	
	
}//AuthentificationController()