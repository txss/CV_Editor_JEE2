package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.amu.univ.cveditor.entities.Cv;
import fr.amu.univ.cveditor.services.CvManager;

@ManagedBean(name="cv")
@SessionScoped
public class CvController implements Serializable {
	
	private static final long serialVersionUID = 3314456687259986660L;
	
	private Cv cv = new Cv();
	private Navigation nav = new Navigation();
	
	@EJB
	private CvManager cvm;
	
	@ManagedProperty(value="#{auth}")
	private AuthenticateController auth;
	
	public void setAuthenticateController(AuthenticateController auth) {
		this.auth = auth;
	}//setAuthenticateController
	
	/* Members Methods */
	public Cv getCv() {
		return this.cv;
	}//getCv()
	
	public List<Cv> findAll() {
		return cvm.listAll();
	}//findAll()
	
	public String store() {
		auth.getConnectedUser().setCv(cv);
		auth.updateConnectedUser();
		
		return nav.showCV();
	}//store()
	
	public String remove() {
		cvm.remove(cv);
		
		return nav.account();
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