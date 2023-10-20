package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import demo_jdbc.ExceptionTechnique;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			System.out.println("connection établi");

		} catch (ClassNotFoundException e) {

			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors du chargement du Driver", e);

		}

		Connection maConnection = null;

		ArrayList<Fournisseur> arrayFournisseur = new ArrayList<>();

		try {

			maConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
			System.out.println("connection établi a la bdd : " + maConnection);

			Statement stat = maConnection.createStatement();
			ResultSet curseur = stat.executeQuery("SELECT ID, NOM FROM FOURNISSEUR");

			while (curseur.next()) {
				Integer id = curseur.getInt("ID");
				String nom = curseur.getString("NOM");

				Fournisseur fournisseurActuel = new Fournisseur(id, nom);
				arrayFournisseur.add(fournisseurActuel);
			}

			curseur.close();

			stat.close();

			for (Fournisseur fournisseurs : arrayFournisseur) {
				System.out.println(fournisseurs);
			}

		} catch (SQLException e) {

			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la connection a la DB", e);

		} finally {

			try {

				if (maConnection != null && maConnection.isClosed()) {
					maConnection.close();

				}

			} catch (SQLException e) {

				throw new ExceptionTechnique("Un probléme est survenu lors de la Fermeture de la DB", e);

			}
		}

	}

}
