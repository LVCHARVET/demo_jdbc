package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import demo_jdbc.ExceptionTechnique;
import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDAO {

	private Connection connection;

	public FournisseurDaoJdbc(Connection connection) {
		this.setConnection(connection);
	}

	@Override
	public List<Fournisseur> extraire() {
		List<Fournisseur> arrayFournisseur = new ArrayList<>();
		try {
			Statement stat = connection.createStatement();
			ResultSet curseur = stat.executeQuery("SELECT ID, NOM FROM FOURNISSEUR");
			while (curseur.next()) {
				Integer id = curseur.getInt("ID");
				String nom = curseur.getString("NOM");

				Fournisseur fournisseurActuel = new Fournisseur(id, nom);
				arrayFournisseur.add(fournisseurActuel);
			}

			curseur.close();

			stat.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la connection a la DB", e);
		}
		System.out.println("connection établi a la bdd : " + connection);
		return arrayFournisseur;

	}
	
	/**/

	@Override
	public void insert(Fournisseur fournisseur) {
		PreparedStatement insertFournisseur = null;
		int nb;
		try {
			
			insertFournisseur = connection.prepareStatement("INSERT INTO fournisseur (ID, NOM) VALUES (?, ?)");
			insertFournisseur.setInt(1, fournisseur.getId());
			insertFournisseur.setString(2, fournisseur.getNom());
			nb = insertFournisseur.executeUpdate();
			
			insertFournisseur.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la connection a la DB", e);
		}
		System.out.println(nb);
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		PreparedStatement updateFournisseur = null;
		int nb;
		try {
			updateFournisseur = connection.prepareStatement("UPDATE fournisseur SET NOM=? WHERE NOM=?");
			updateFournisseur.setString(1, nouveauNom);
			updateFournisseur.setString(2, ancienNom);
			nb = updateFournisseur.executeUpdate();
			updateFournisseur.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la connection a la DB", e);
		}
		System.out.println(nb);

		return nb;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		PreparedStatement deleteFournisseur = null;
		int nb;
		try {
			deleteFournisseur = connection.prepareStatement("DELETE FROM fournisseur WHERE NOM=?");
			deleteFournisseur.setString(1, fournisseur.getNom());
			nb = deleteFournisseur.executeUpdate();
			deleteFournisseur.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la connection a la DB", e);
		}
		System.out.println(nb);

		return false;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
