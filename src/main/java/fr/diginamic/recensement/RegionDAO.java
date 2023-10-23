package fr.diginamic.recensement;

import java.util.List;

public interface RegionDAO {
	List<Region> extraire();
	void insert(Region region);
	int update(String ancienNom, String nouveauNom);
	boolean delete(Region region);
}
