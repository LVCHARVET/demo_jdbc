package fr.diginamic.jdbc.dao;

import java.sql.Connection;
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

	@Override
	public void insert(Fournisseur fournisseur) {
		Statement stat = null;
		int nb;
		try {
			stat = connection.createStatement();
			nb = stat.executeUpdate("INSERT INTO fournisseur (ID, NOM) VALUES (" + fournisseur.getId() + ", '"
					+ fournisseur.getNom() + "')");
			stat.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la connection a la DB", e);
		}
		System.out.println(nb);
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		Statement stat = null;
		int nb;
		try {
			stat = connection.createStatement();
			nb = stat.executeUpdate("UPDATE fournisseur SET NOM='" + nouveauNom + "' WHERE NOM='" + ancienNom + "'");
			stat.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la connection a la DB", e);
		}
		System.out.println(nb);

		return 0;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		Statement stat = null;
		int nb;
		try {
			stat = connection.createStatement();
			nb = stat.executeUpdate("DELETE FROM fournisseur WHERE NOM='" + fournisseur.getNom() + "'");

			stat.close();
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
