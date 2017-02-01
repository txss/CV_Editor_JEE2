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
	
	@EJB
	private CvManager cvm;
	
	public List<Cv> getAllCv() {
		return cvm.listAll();
	}//getAllPerson()
	
	public Cv getCv(Integer id) {
		return cvm.getCv(id);
	}//getPerson()
	
	public void setCv(Cv cv) throws BadPerson {
		cvm.createCv(cv);
	}//setPerson()
	
	public void updateCv(Cv cv){
		cvm.updateCv(cv);
	}//updatePerson()

}//CvController