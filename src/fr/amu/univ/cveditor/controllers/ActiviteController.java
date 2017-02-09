package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import fr.amu.univ.cveditor.entities.Activite;

@ManagedBean(name = "activite")
@ViewScoped
public class ActiviteController implements Serializable {

	private static final long serialVersionUID = -6927463438834842300L;

	private Activite activite = new Activite(); 

	@ManagedProperty(value = "#{cv}")
	private CvController cvController;

	//must povide the setter method
	public void setCvController(CvController cvController) {
		this.cvController = cvController;
	}//setCvController()

	
	@PostConstruct
	public void init() {
	}//init()
	
	
	
	
	
	/* Private methods */
	
	private List<Activite> getActivites() {
		return cvController.getCv().getActivites();
	}//getActivites()

	private int getHighestActivityId() {
		int id = 0;
		for(int i = 0 ; i < getActivites().size() ; ++i) {
			if(getActivites().get(i).getId() > id)
				id = getActivites().get(i).getId();
		}
		return id;
	}//getHighestActivityId()

	private void newActivite() {
		activite = new Activite();
	}//newActivity()

	private int indexOf(int idAct) {
		for(int i = 0 ; i < getActivites().size() ; ++i) {
			if(getActivites().get(i).getId() == idAct) {
				return i;
			}
		}
		return -1;
	}//exist()

	private void setId() {
			if(getActivites().isEmpty())
				activite.setId(1);
			else
				activite.setId(getHighestActivityId() + 1);
	}//setNewId()
	
	private void save() {
		if(getActivites() == null)
			cvController.getCv().setActivites(new ArrayList<Activite>());
		
		int indexAct = indexOf(activite.getId());
		if(indexAct >= 0) {
			getActivites().set(indexAct, activite);
		}
		else {
			setId();
			getActivites().add(activite);
		}
	}//store()
	
	
	

	/* Members Methods */
	public Activite getThisActivite() {
		return activite;
	}//getActivite()

	public Activite getActivite(int idAct) {
		activite = getActivites().get(indexOf(idAct));
		return activite;
	}//getActivite()

	public void remove(Integer idAct) {
		int indexAct = indexOf(idAct);
		List<Activite> modifiedActivities = getActivites();
		
		/* Update Activities's id */
		modifiedActivities.remove(indexAct);
		for(int i = indexAct ; i < modifiedActivities.size() ; ++i) {
			modifiedActivities.get(i).setId(modifiedActivities.get(i).getId() - 1);
		}
		
		cvController.getCv().setActivites(modifiedActivities);
	}//remove()

	/* Listener */
	public void saveAct(AjaxBehaviorEvent event) {
		save();
		newActivite();
	}//saveAct()
	
	public void removeAct(AjaxBehaviorEvent event) {
		remove(activite.getId());
		newActivite();
	}//removeAct()

}//ActiviteController
