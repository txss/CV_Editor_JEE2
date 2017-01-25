package fr.amu.univ.cveditor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PswValidator implements IValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String PSW_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

	public PswValidator() {
		pattern = Pattern.compile(PSW_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 *
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

}
