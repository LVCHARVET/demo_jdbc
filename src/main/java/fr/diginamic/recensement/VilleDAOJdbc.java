package fr.diginamic.recensement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import demo_jdbc.ExceptionTechnique;

public class VilleDAOJdbc implements VilleDAO {

	private Connection connection;

	public VilleDAOJdbc(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Ville> extraire() {
		List<Ville> arrayVille = new ArrayList<>();
		try {
			Statement stat = connection.createStatement();
			ResultSet curseur = stat.executeQuery(
					"SELECT codeRegion, nomRegion, codeDepartement, codeCommune, nomCommune, popTotale FROM VILLES");
			while (curseur.next()) {
				String codeRegion = curseur.getString("codeRegion");
				String nomRegion = curseur.getString("nomRegion");
				String codeDepartement = curseur.getString("codeDepartement");
				String codeCommune = curseur.getString("codeCommune");
				String nomCommune = curseur.getString("nomCommune");
				int popTotale = curseur.getInt("popTotale");

				Ville villeActuel = new Ville(codeRegion, nomRegion, codeDepartement, codeCommune, nomCommune,
						popTotale);
				arrayVille.add(villeActuel);
			}

			curseur.close();

			stat.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la connection a la DB", e);
		}
		System.out.println("connection établi a la bdd : " + connection);
		return arrayVille;
	}

	@Override
	public void insert(Ville ville) {
		PreparedStatement insertVille = null;
		int nb;
		try {

			insertVille = connection.prepareStatement(
					"INSERT INTO VILLES (codeRegion, nomRegion, codeDepartement, codeCommune, nomCommune, popTotale, idDept, idRegion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			insertVille.setString(1, ville.getCodeRegion());
			insertVille.setString(2, ville.getNomRegion());
			insertVille.setString(3, ville.getCodeDepartement());
			insertVille.setString(4, ville.getCodeCommune());
			insertVille.setString(5, ville.getNomCommune());
			insertVille.setInt(6, ville.getPopTotale());
			insertVille.setInt(7, ville.getIdDep());
			insertVille.setInt(8, ville.getIdReg());
			nb = insertVille.executeUpdate();

			insertVille.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de l'insertion a la DB", e);
		}
		System.out.println(nb);

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		PreparedStatement updateVille = null;
		int nb;
		try {
			updateVille = connection.prepareStatement("UPDATE VILLES SET nomCommune=? WHERE nomCommune=?");
			updateVille.setString(1, nouveauNom);
			updateVille.setString(2, ancienNom);
			nb = updateVille.executeUpdate();
			updateVille.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la modification d'élément vers la DB", e);
		}
		System.out.println(nb);

		return nb;
	}

	@Override
	public boolean delete(Ville ville) {
		PreparedStatement deleteVille = null;
		int nb;
		try {
			deleteVille = connection.prepareStatement("DELETE FROM VILLES WHERE NOM=?");
			deleteVille.setString(1, ville.getNomCommune());
			nb = deleteVille.executeUpdate();
			deleteVille.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la suppression d'élément vers la DB", e);
		}
		System.out.println(nb);
		return false;
	}

}
