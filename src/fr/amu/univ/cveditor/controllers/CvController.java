package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.amu.univ.cveditor.entities.Activite;
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
	
	@ManagedProperty(value = "#{auth}")
	private AuthenticateController auth;
	
	public AuthenticateController getAuthenticateController() {
		return auth;
	}//getAuthenticateController()
	public void setAuthenticateController(AuthenticateController AuthController) {
		this.auth = AuthController;
	}//setCvController()
	
	@PostConstruct
	public void init() {
		System.out.println("------------------INIT------------------");
		System.out.println(auth.getConnectedUser().getEmail());
		if(auth.getConnectedUser().getCv() != null)
			System.out.println(auth.getConnectedUser().getCv().getId());
	}//init()
	
	
	
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
		System.out.println("------------------SHOW------------------");
		System.out.println(auth.getConnectedUser().getCv().getId());
		
		cv = cvm.find(auth.getConnectedUser().getCv().getId());
		return nav.showCV();
	}//show()
	
	public String edit() {
		cv = cvm.find(auth.getConnectedUser().getCv().getId());
		
		return nav.editCv();
	}//editCv()
	
	public String store() {
		System.out.println("------------------STORE------------------");
		System.out.println(cv.getId());
		System.out.println(cv.getActivites());
		
		auth.getConnectedUser().setCv(cv);
		
		System.out.println(auth.getConnectedUser().getCv().getId());
		
		auth.updateConnectedUser();
		
		return nav.showCV();
	}//store()

	public String newCv() {
		System.out.println("------------------NEW CV------------------");

		cv = new Cv();
		cv.setActivites(new ArrayList<Activite>());
		
		return nav.editCv();
	}//newCv()
	
	public String remove() {
		System.out.println("------------------REMOVE------------------");
		System.out.println(auth.getConnectedUser().getEmail());
		System.out.println(auth.getConnectedUser().getCv().getId());
		
		String emailPerson = auth.getConnectedUser().getEmail();
		Integer idCv = auth.getConnectedUser().getCv().getId();
		
		auth.getConnectedUser().setCv(null);
		
		cvm.remove(idCv, emailPerson);
		return nav.account();
	}//remove()
	
}//CvController