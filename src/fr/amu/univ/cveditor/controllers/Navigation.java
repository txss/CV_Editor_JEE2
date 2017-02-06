package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="nav")
@ApplicationScoped
public class Navigation implements Serializable {

	private static final long serialVersionUID = -9209523733087903449L;

	public String index() {
		return "index?faces-redirect=true";
	}//index()

	public String auth() {
		return "authenticate?faces-redirect=true";
	}//auth()

	public String signIn() {
		return "signIn?faces-redirect=true";
	}//signIn()

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
	}//editPerson()

}//Navigation