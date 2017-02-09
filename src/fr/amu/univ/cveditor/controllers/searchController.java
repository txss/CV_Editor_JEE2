package fr.amu.univ.cveditor.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.amu.univ.cveditor.entities.Cv;
import fr.amu.univ.cveditor.entities.Person;

@ManagedBean(name="search")
@SessionScoped
public class searchController {
	
	private Navigation nav = new Navigation();
	
	private String request;
	private List<Person> resultPerson = null;
	private List<Cv> resultCv = null;
	
	@ManagedProperty(value="#{cv}")
	private CvController cvControl;
	
	@ManagedProperty(value="#{person}")
	private PersonController pControl;

	public void setCvController(CvController cvControl) {
		this.cvControl = cvControl;
	}//setCvControl()

	public void setPersonController(PersonController pControl) {
		this.pControl = pControl;
	}//setPersonController()
	
	public String getRequest() {
		return request;
	}//getRequest()
	
	public void setRequest(String request) {
		this.request = request;
	}//setRequest()
	
	public List<Person> getResultPerson() {
		return resultPerson;
	}//getResultPerson()
	
	public List<Cv> getResultCv() {
		return resultCv;
	}//getResultPerson()
	
	
	/* Member Methods */
	public String search() {
		resultPerson = new ArrayList<Person>(pControl.search(request));
		resultCv = new ArrayList<Cv>(cvControl.search(request));
		
		return nav.resultSearch();
	}//search()
	
	
}//searchController
