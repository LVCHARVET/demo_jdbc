package fr.diginamic.recensement;

import java.util.List;

public interface DepartementDAO {
	List<Departement> extraire();
	void insert(Departement departement);
	int update(String ancienNom, String nouveauNom);
	boolean delete(Departement departement);
}
