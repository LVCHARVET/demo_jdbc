package fr.diginamic.recensement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import demo_jdbc.ExceptionTechnique;

public class RegionDAOJdbc implements RegionDAO {

	private Connection connection;

	public RegionDAOJdbc(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Region> extraire() {
		List<Region> arrayRegion = new ArrayList<>();
		try {
			Statement stat = connection.createStatement();
			ResultSet curseur = stat.executeQuery("SELECT id, nomRegion FROM REGIONS");
			while (curseur.next()) {
				int id = curseur.getInt("id");
				String nomRegion = curseur.getString("nomRegion");

				Region regionActuel = new Region(id, nomRegion);
				arrayRegion.add(regionActuel);
			}

			curseur.close();

			stat.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la connection a la DB", e);
		}
		System.out.println("connection établi a la bdd : " + connection);
		return arrayRegion;
	}

	@Override
	public void insert(Region region) {
		PreparedStatement insertRegion = null;
		int nb;
		try {

			insertRegion = connection.prepareStatement("INSERT INTO REGIONS (nomRegion) VALUES (?)");

			insertRegion.setString(1, region.getNomRegion());
			nb = insertRegion.executeUpdate();

			insertRegion.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de l'insertion a la DB", e);
		}
		System.out.println(nb);

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		PreparedStatement updateRegion = null;
		int nb;
		try {
			updateRegion = connection.prepareStatement("UPDATE REGIONS SET nomRegion=? WHERE nomRegion=?");
			updateRegion.setString(1, nouveauNom);
			updateRegion.setString(2, ancienNom);
			nb = updateRegion.executeUpdate();
			updateRegion.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la modification d'élément vers la DB", e);
		}
		System.out.println(nb);

		return nb;
	}

	@Override
	public boolean delete(Region region) {
		PreparedStatement deleteRegion = null;
		int nb;
		try {
			deleteRegion = connection.prepareStatement("DELETE FROM REGIONS WHERE nomRegion=?");
			deleteRegion.setString(1, region.getNomRegion());
			nb = deleteRegion.executeUpdate();
			deleteRegion.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la suppression d'élément vers la DB", e);
		}
		System.out.println(nb);
		return false;
	}

}
