package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoJdbc {

	public static void main(String[] args) {
		FournisseurDAO fournisseurDao = null;
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
			fournisseurDao = new FournisseurDaoJdbc(connection);
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}

		List<Fournisseur> arrayFournisseur = fournisseurDao.extraire();
		for (Fournisseur fournisseurs : arrayFournisseur) {
			System.out.println(fournisseurs);
		}

		Fournisseur newFournisseur = new Fournisseur(4, "L'Espace Création");

		fournisseurDao.insert(newFournisseur);

		fournisseurDao.update("France de matériaux", "France matériaux");

		fournisseurDao.delete(newFournisseur);
		 
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

}
