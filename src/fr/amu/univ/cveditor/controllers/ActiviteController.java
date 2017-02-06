package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import fr.amu.univ.cveditor.entities.Activite;

@ManagedBean(name = "activite")
@RequestScoped
public class ActiviteController implements Serializable {

	private static final long serialVersionUID = -6927463438834842300L;

	private Activite activite = new Activite(); 
	
	@ManagedProperty(value = "#{cv}")
	private CvController currentCv;
	
	@ManagedProperty(value = "#{auth}")
	private AuthenticateController auth;

	//must povide the setter method
	public void setCvController(CvController cvController) {
		this.currentCv = cvController;
	}//setCvController()
	
	//must povide the setter method
		public void setAuthenticateController(AuthenticateController AuthController) {
			this.auth = AuthController;
		}//setCvController()

	/* Members Methods */
	public Activite getActivite() {
		return activite;
	}//getActivite()

	public Activite getActivite(int index) {
		activite = getActivites().get(index);
		return activite;
	}//getActivite()
	
	public void newActivite() {
		activite = new Activite();
	}//newActivity()

	public List<Activite> getActivites() {
		return currentCv.getCv().getActivites();
	}//getActivites()
	
	private boolean exist() {
		for(int i = 0 ; i < getActivites().size() ; ++i) {
			if(getActivites().get(i).getId() == activite.getId()) {
				return true;
			}
		}
		return false;
	}//exist()
	
	public void store() {
		if(exist())
			getActivites().set(activite.getId(), activite);
		else
			getActivites().add(activite);
	}//store()

	
	public void remove() {
		getActivites().remove(activite.getId());
	}//remove()
	
	public void listener(AjaxBehaviorEvent event) {
		store();
		newActivite();
	}//listener()

}//ActiviteController
