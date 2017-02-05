package fr.amu.univ.cveditor.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Table(name = "PERSON")
public class Person {

	@Id
	private String email;

	@Column(name = "name")
	private String name;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "website")
	private String webSite;
	@Column(name = "birthdate")
	private String birthdate;
	@Column(name = "password")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cv cv;
	
	/* Constructor */
	public Person() {}

	/* Getters */ 
	public String getName() 		{ return name; 		}
	public String getFirstName() 	{ return firstName; }
	public String getEmail() 		{ return email; 	}
	public String getWebSite() 		{ return webSite; 	}
	public String getBirthdate() 	{ return birthdate; }
	public String getPassword() 	{ return password; 	}
	public Cv	  getCv()			{ return cv;		}

	/* Setters */
	public void setName(String name) 			{ this.name = name; 			}
	public void setFirstName(String firstName) 	{ this.firstName = firstName; 	}
	public void setEmail(String email) 			{ this.email = email; 			}
	public void setWebSite(String webSite) 		{ this.webSite = webSite; 		}
	public void setBirthdate(String birthdate) 	{ this.birthdate = birthdate; 	}
	public void setPassword(String password) 	{ this.password = DigestUtils.sha256Hex(password);}
	public void setCv(Cv cv) 					{ this.cv = cv;					}
	
	/* Members methods */
	public String toString(){
		return 	"\nEmail-> " + this.email +
				"\nName-> " + this.name +
				"\nFirstname-> " + this.firstName +
				"\nBirthdate-> " + this.birthdate +
				"\nWebsite->" + this.webSite;
	}//toString()
	
}//Person
