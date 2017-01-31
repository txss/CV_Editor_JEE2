package fr.amu.univ.cveditor.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BadPerson extends Exception {
	private static final long serialVersionUID = 1L;

	public BadPerson(String msg) {
		super(msg);
	}//BadPerson()
}//BadPerson
