package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import demo_jdbc.ExceptionTechnique;

public class TestInsertion {

	public static void main(String[] args) {
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			System.out.println("connection établi");

		} catch (ClassNotFoundException e) {

			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors du chargement du Driver", e);

		}

		Connection maConnection = null;

		try {

			maConnection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
			System.out.println("connection établi a la bdd : " + maConnection);
			
			Statement stat = maConnection.createStatement();
			int nb = stat.executeUpdate("INSERT INTO fournisseur (ID, NOM) VALUES (4, 'BLOT & FILS')");
			System.out.println(nb);
			
			stat.close();
			

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
