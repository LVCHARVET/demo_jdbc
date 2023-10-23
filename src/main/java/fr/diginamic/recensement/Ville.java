package fr.diginamic.recensement;

import java.util.Objects;

public class Ville {
	String codeRegion;
	String nomRegion;
	String codeDepartement;
	String codeCommune;
	String nomCommune;
	int popTotale;
	private int idDep;
	private int idReg;

	public Ville(String codeRegion, String nomRegion, String codeDepartement, String codeCommune, String nomCommune,
			int popTotale) {
		super();
		this.codeRegion = codeRegion;
		this.nomRegion = nomRegion;
		this.codeDepartement = codeDepartement;
		this.codeCommune = codeCommune;
		this.nomCommune = nomCommune;
		this.popTotale = popTotale;
	}

	

	@Override
	public String toString() {
		return "Ville [codeRegion=" + codeRegion + ", nomRegion=" + nomRegion + ", codeDepartement=" + codeDepartement
				+ ", codeCommune=" + codeCommune + ", nomCommune=" + nomCommune + ", popTotale=" + popTotale
				+ ", idDep=" + idDep + ", idReg=" + idReg + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(codeDepartement, nomCommune);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		return Objects.equals(codeDepartement, other.codeDepartement) && Objects.equals(nomCommune, other.nomCommune);
	}

	public String getCodeRegion() {
		return codeRegion;
	}

	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}

	public String getNomRegion() {
		return nomRegion;
	}

	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}

	public String getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	public String getCodeCommune() {
		return codeCommune;
	}

	public void setCodeCommune(String codeCommune) {
		this.codeCommune = codeCommune;
	}

	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	public int getPopTotale() {
		return popTotale;
	}

	public void setPopTotale(int popTotale) {
		this.popTotale = popTotale;
	}

	public int getIdDep() {
		return idDep;
	}

	public void setIdDep(int idDep) {
		this.idDep = idDep;
	}

	public int getIdReg() {
		return idReg;
	}

	public void setIdReg(int idReg) {
		this.idReg = idReg;
	}

	
}
