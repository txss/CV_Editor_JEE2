package fr.amu.univ.cveditor.controllers;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Navigation {

    public String index() {
        return "index";
    }//index()
    
    public String auth() {
        return "authentification";
    }//auth()
    
    public String account() {
    	return "myAccount";
    }//account()

	public String showCV() {
		return "showCV";
	}//showCV()

	public String editCv() {
		return "editCV";
	}//editCv()

	public String showPerson() {
		return "showPerson";
	}//showPerson()

	public String editPerson() {
		return "editPerson";
	}
	

}//Navigation