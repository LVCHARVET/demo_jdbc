package fr.diginamic.recensement;

import java.util.Objects;

public class Region {
	private int id;
	String nomRegion;

	public Region(int id, String nomRegion) {
		this.id = id;
		this.nomRegion = nomRegion;
	}

	@Override
	public String toString() {
		return "Region [nomRegion=" + nomRegion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomRegion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Region other = (Region) obj;
		return Objects.equals(nomRegion, other.nomRegion);
	}

	public String getNomRegion() {
		return nomRegion;
	}

	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}

	public int getId() {
		return id;
	}

}
