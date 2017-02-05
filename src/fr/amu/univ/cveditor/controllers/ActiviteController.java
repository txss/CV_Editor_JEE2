package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.amu.univ.cveditor.entities.Activite;

@ManagedBean(name = "activite")
@SessionScoped
public class ActiviteController implements Serializable {

	private static final long serialVersionUID = -6927463438834842300L;

	private Activite activite = new Activite(); 
	
	@ManagedProperty(value = "#{cv}")
	private CvController currentCv;

	//must povide the setter method
	public void setCvController(CvController cvController) {
		this.currentCv = cvController;
	}//setCvController()

	/* Members Methods */
	public Activite getActivite() {
		return activite;
	}//getActivite()

	public Activite getActivite(int index) {
		activite = getActivites().get(index);
		return activite;
	}//getActivite()

	public List<Activite> getActivites() {
		return currentCv.getCv().getActivites();
	}//getActivites()

	public void store() {
		if(getActivites().contains(activite)) {
			getActivites().set(activite.getId(), activite);
		}
		else {
			activite.setId(getActivites().size());
			getActivites().add(activite);
		}
	}//storeActivite()

	public void remove() {
		getActivites().remove(activite.getId());
	}//removeActivite()


}//ActiviteController
