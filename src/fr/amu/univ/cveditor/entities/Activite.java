package fr.amu.univ.cveditor.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Embeddable
@Table(name = "ACTIVITE")
public class Activite {

	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "years")
	private String years;
	@Column(name = "nature")
	private String nature;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "email")
	private String email;
	
	/* Constructor */
	public Activite() {}

	
	/* Getters */
	public int 		getId() 			{ return id; 			}
	public String 	getYears() 			{ return years; 		}
	public String 	getNature() 		{ return nature; 		}
	public String 	getTitle() 			{ return title; 		}
	public String 	getDescription() 	{ return description; 	}
	public String 	getEmail() 			{ return email;			}

	/* Setters */
	public void setId(int id) 						{ this.id = id; 					}
	public void setYears(String years) 				{ this.years = years; 				}
	public void setNature(String nature) 			{ this.nature = nature; 			}
	public void setTitle(String title) 				{ this.title = title; 				}
	public void setDescription(String description) 	{ this.description = description; 	}
	public void setEmail(String email) 				{ this.email = email; 				}

	/* Members Methods */
	public String toString(){
		return 	"\nId-> " + this.id + 
				"\nTitle-> " + this.title +
				"\nNature-> " + this.nature +
				"\nAnnees-> " + this.years +
				"\nEmail-> " + this.email;
	}//toString()
	
}//Activite
