package fr.amu.univ.cveditor.bean;

public class Personne {

	private String name;
	private String firstName;
	private String email;
	private String webSite;
	private String birthdate;
	private String password;
	
	// default constructor
	public Personne() {}

	// Begin getters 
	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public String getWebSite() {
		return webSite;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public String getPassword() {
		return password;
	}

	// Begin setters
	public void setName(String name) {
		this.name = name;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		return 	"\nEmail-> " + this.email +
				"\nName-> " + this.name +
				"\nFirstname-> " + this.firstName +
				"\nBirthdate-> " + this.birthdate +
				"\nWebsite->" + this.webSite;
	}
	
}
