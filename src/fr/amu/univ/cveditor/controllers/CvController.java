package fr.amu.univ.cveditor.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.amu.univ.cveditor.entities.Cv;
import fr.amu.univ.cveditor.services.CvManager;

@ManagedBean(name="cv")
@SessionScoped
public class CvController {
	
	private Cv cv = new Cv();
	private Navigation nav = new Navigation();
	
	@EJB
	private CvManager cvm;
	
	
	/* Members Methods */
	public Cv getCv() {
		return this.cv;
	}//getCv()
	
	public List<Cv> findAll() {
		return cvm.listAll();
	}//findAll()
	
	public void store() {
		cvm.create(cv);
	}//store()
	
	public void update() {
		cvm.update(cv);
	}//update()
	
	public void remove() {
		cvm.remove(cv);
	}//remove()
	
	
	public String show(Integer id) {
		cv = cvm.find(id);
		return nav.showCV();
	}//show()
	
	public String edit() {
		return nav.editCv();
	}//editCv()

	public String newCv() {
		cv = new Cv();
		return nav.editCv();
	}//newCv()
	
}//CvController