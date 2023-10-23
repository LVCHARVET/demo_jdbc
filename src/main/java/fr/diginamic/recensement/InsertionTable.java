package fr.diginamic.recensement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class InsertionTable {
	public static void main(String[] args) throws IOException {
		List<Ville> arrayVille = new ArrayList<>();
		HashSet<String> comptageDepartement = new HashSet<>();
		HashSet<String> comptageRegion = new HashSet<>();

		Path pathfile = Paths.get("C:/temp/TP16/TP16_recensement.csv");

		List<String> lines = Files.readAllLines(pathfile);

		for (int i = 0; i < lines.size(); i++) {

			String line = lines.get(i);
			String[] tokens = line.split(";");

			if (i > 1) {

				if (tokens.length == 10) {

					String codeRegion = tokens[0];
					String nomRegion = tokens[1];
					String codeDepartement = tokens[2];
					String codeCommune = tokens[5];
					String nomCommune = tokens[6];
					String transfoPop = tokens[9].replace(" ", "");
					int popTotal = Integer.parseInt(transfoPop);

					Ville ville = new Ville(codeRegion, nomRegion, codeDepartement, codeCommune, nomCommune, popTotal);
					comptageDepartement.add(codeDepartement);
					comptageRegion.add(nomRegion);
					arrayVille.add(ville);
				}
			}
		}
		List<String> departement = new ArrayList<>(comptageDepartement);
		List<Departement> arrayDepartement = new ArrayList<>();
		for (String departements : departement) {
			Departement newDepartement = new Departement(0, departements);
			arrayDepartement.add(newDepartement);
		}

		List<String> region = new ArrayList<>(comptageRegion);
		List<Region> arrayRegion = new ArrayList<>();
		for (String regions : region) {
			Region newRegion = new Region(0, regions);
			arrayRegion.add(newRegion);
		}

		VilleDAO villeDao = null;
		DepartementDAO departementDao = null;
		RegionDAO regionDao = null;
		Connection connection = null;
		ResourceBundle conf = ResourceBundle.getBundle("conf");
		String url = conf.getString("url");
		String user = conf.getString("user");
		String pw = conf.getString("pw");
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pw);
			villeDao = new VilleDAOJdbc(connection);
			departementDao = new DepartementDAOJdbc(connection);
			regionDao = new RegionDAOJdbc(connection);
			System.out.println("La connection est établi");
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}

		long startDep = System.currentTimeMillis();

		List<Departement> arrayDepartementFromDb = departementDao.extraire();
		for (Departement departements : arrayDepartement) {
			if (!(arrayDepartementFromDb.contains(departements))) {
				departementDao.insert(departements);
			}
		}
		
		arrayDepartementFromDb = departementDao.extraire();

		long endDep = System.currentTimeMillis();
		long startReg = System.currentTimeMillis();

		List<Region> arrayRegionFromDb = regionDao.extraire();
		for (Region regions : arrayRegion) {
			if (!(arrayRegionFromDb.contains(regions))) {
				regionDao.insert(regions);
			}
		}
		
		arrayRegionFromDb = regionDao.extraire();

		long endReg = System.currentTimeMillis();
		long startVille = System.currentTimeMillis();

		List<Ville> arrayVilleFromDb = villeDao.extraire();
		for (Ville villes : arrayVille) {
			if (!(arrayVilleFromDb.contains(villes))) {
				for (Region regions : arrayRegionFromDb) {
					if (villes.getNomRegion().equals(regions.getNomRegion())) {
						villes.setIdReg(regions.getId());
					}
				}
				for (Departement departements : arrayDepartementFromDb) {
					if (villes.getCodeDepartement().equals(departements.getCodeDepartement())) {
						villes.setIdDep(departements.getId());
					}
				}
				villeDao.insert(villes);
			}
		}

		long endVille = System.currentTimeMillis();

		System.out.println(
				"Le temps d'execution pour la ville est de : " + ((endVille - startVille) / 1000) + " secondes");
		System.out.println(
				"Le temps d'execution pour le departement est de : " + ((endDep - startDep) / 1000) + " secondes");
		System.out
				.println("Le temps d'execution pour la region est de : " + ((endReg - startReg) / 1000) + " secondes");
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
