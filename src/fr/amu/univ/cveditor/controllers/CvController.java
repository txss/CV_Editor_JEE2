package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import fr.amu.univ.cveditor.entities.Activite;
import fr.amu.univ.cveditor.entities.Cv;
import fr.amu.univ.cveditor.services.CvManager;

@ManagedBean(name="cv")
@ViewScoped
public class CvController implements Serializable {
	private static final long serialVersionUID = 3314456687259986660L;
	
	private Cv cv = new Cv();
	private Navigation nav = new Navigation();
	
	@EJB
	private CvManager cvm;
	
	@ManagedProperty(value = "#{auth}")
	private AuthenticateController auth;
	
	public AuthenticateController getAuthenticateController() {
		return auth;
	}//getAuthenticateController()
	public void setAuthenticateController(AuthenticateController AuthController) {
		this.auth = AuthController;
	}//setCvController()
	
	/* Members Methods */
	public Cv getCv() {
		return this.cv;
	}//getCv()
	
	public void setCv(Cv cv) {
		this.cv = cv;
	}//setCv()
	
	public List<Cv> findAll() {
		return cvm.listAll();
	}//findAll()
	
	

	public String show() {
		cv = cvm.find(auth.getConnectedUser().getCv().getId());
		return nav.showCV();
	}//show()
	
	public String show(Integer id) {
		cv = cvm.find(id);
		return nav.showCV();
	}//show()
	
	public String edit() {
		cv = cvm.find(auth.getConnectedUser().getCv().getId());
		return nav.editCv();
	}//editCv()
	
	public String store() {
		auth.getConnectedUser().setCv(cv);
		auth.updateConnectedUser();
		
		return nav.showCV();
	}//store()

	public String newCv() {
		cv = new Cv();
		cv.setActivites(new ArrayList<Activite>());
		
		return nav.editCv();
	}//newCv()
	
	public String remove() {
		String emailPerson = auth.getConnectedUser().getEmail();
		Integer idCv = auth.getConnectedUser().getCv().getId();
		
		auth.getConnectedUser().setCv(null);
		cv = null;
		
		cvm.remove(idCv, emailPerson);
		
		return nav.account();
	}//remove()
	
	
	
	/* Listener */
	public void redirectToAccount(ComponentSystemEvent event) {
		if(auth.getConnectedUser() == null || auth.getConnectedUser().getCv() == null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler cNav
			= (ConfigurableNavigationHandler)
			fc.getApplication().getNavigationHandler();

			cNav.performNavigation("myAccount.xhtml");
		}
	}//redirectToAuth()
	
	public void redirectWhenUserTryToAccessToShowCVByURL(ComponentSystemEvent event) {
		if(cv == null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler cNav
			= (ConfigurableNavigationHandler)
			fc.getApplication().getNavigationHandler();

			cNav.performNavigation("myAccount.xhtml");
		}
	}//redirectWhenUserTryToAccessToShowCVByURL()
	
}//CvController