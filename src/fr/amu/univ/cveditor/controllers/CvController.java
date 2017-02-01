package fr.amu.univ.cveditor.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.amu.univ.cveditor.entities.Cv;
import fr.amu.univ.cveditor.exceptions.BadPerson;
import fr.amu.univ.cveditor.services.CvManager;

@ManagedBean(name="cv")
@SessionScoped
public class CvController {
	
	private Cv cv;
	private Navigation nav;
	
	@EJB
	private CvManager cvm;
	
	public List<Cv> getAll() {
		return cvm.listAll();
	}//getAllPerson()
	
	public Cv getCv() {
		return this.cv;
	}//getPerson()
	
	public String show(Integer id) {
		cv = cvm.find(id);
		return nav.showCV();
	}//show()
	
	public void storeCv(Cv cv) throws BadPerson {
		cvm.create(cv);
	}//setPerson()
	
	public void updateCv(Cv cv){
		cvm.update(cv);
	}//updatePerson()
	
	public String newCv() {
		cv = new Cv();
		return nav.editCv();
	}//newCv()
	
	public String editCv() {
		return nav.editCv();
	}//editCv()

}//CvController