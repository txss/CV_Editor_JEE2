package fr.amu.univ.cveditor.managers;

import fr.amu.univ.cveditor.bean.Cv;

public class CvEJB {

	private Cv curriculumVitae;
	
	public CvEJB() {}

	// Begin getters
	public Cv getCurriculumVitae() {
		return curriculumVitae;
	}

	// Begin setters
	public void setCurriculumVitae(Cv curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}
	
	// Methodes
	public boolean saveCv(Cv cv){
		//TODO
		return false;
	}
	
	public boolean updateCv(Cv cv){
		//TODO
		return false;
	}
	
	public Cv getCv(int id){
		//TODO
		return null;
	}
	
	public Cv searchCv(Cv cv){
		//TODO
		return null;
	}
	
	public boolean deleteCv(int id){
		//TODO
		return false;
	}
}
