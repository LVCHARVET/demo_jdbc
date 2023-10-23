package fr.diginamic.recensement;

import java.util.Objects;

public class Departement {
	private int id;
	String codeDepartement;

	public Departement(int id, String codeDepartement) {
		this.id = id;
		this.codeDepartement = codeDepartement;
	}

	@Override
	public String toString() {
		return "Departement [codeDepartement=" + codeDepartement + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeDepartement);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departement other = (Departement) obj;
		return Objects.equals(codeDepartement, other.codeDepartement);
	}

	public String getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
