package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;
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
	private CvController currentCv;

	//must povide the setter method
	public void setCvController(CvController cvController) {
		this.currentCv = cvController;
	}//setCvController()

	
	@PostConstruct
	public void init() {
	}//init()

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
		System.out.println(getActivites().size());
		if(getActivites().isEmpty())
			activite.setId(1);
		activite.setId(getHighestActivityId() + 1);
	}//newActivity()

	private int exist(int idAct) {
		for(int i = 0 ; i < getActivites().size() ; ++i) {
			if(getActivites().get(i).getId() == idAct) {
				return i;
			}
		}
		return -1;
	}//exist()

	private void save() {
		if(exist(activite.getId()) > 0) {
			getActivites().set(activite.getId(), activite);
		}
		else {
			getActivites().add(activite);
		}
	}//store()
	

	/* Members Methods */
	public Activite getThisActivite() {
		return activite;
	}//getActivite()

	public Activite getActivite(int index) {
		activite = getActivites().get(index);
		return activite;
	}//getActivite()

	public List<Activite> getActivites() {
		return currentCv.getCv().getActivites();
	}//getActivites()

	public void remove(Integer idAct) {
		getActivites().remove(exist(idAct));
	}//remove()

	public void listener(AjaxBehaviorEvent event) {
		System.out.println(getActivites());
		save();
		newActivite();
	}//listener()

}//ActiviteController
