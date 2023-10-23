package fr.diginamic.recensement;

import java.util.List;

public interface VilleDAO {
	List<Ville> extraire();
	void insert(Ville ville);
	int update(String ancienNom, String nouveauNom);
	boolean delete(Ville ville);
}
