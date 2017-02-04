package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.amu.univ.cveditor.entities.Activite;

@ManagedBean(name = "activite")
@ViewScoped
public class ActiviteController implements Serializable{
	
	private static final long serialVersionUID = -6927463438834842300L;

	@ManagedProperty("#{cv}")
	private CvController currentCv;
	
	private Activite activite = new Activite(); 
	
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
	
	public void storeActivite() {
		activite.setId(getActivites().size());
		
		getActivites().add(activite);
	}//storeActivite()
	
	public void updateActivite() {
		getActivites().set(activite.getId(), activite);
	}//updateActivite()
	
	public void removeActivite() {
		getActivites().remove(activite.getId());
	}//removeActivite()
	
	
}//ActiviteController
