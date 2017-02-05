package fr.amu.univ.cveditor.controllers;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="nav")
@ApplicationScoped
public class Navigation implements Serializable {

	private static final long serialVersionUID = -9209523733087903449L;

	public String index() {
		return "index";
	}//index()

	public String auth() {
		return "authenticate";
	}//auth()

	public String signIn() {
		return "signIn";
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