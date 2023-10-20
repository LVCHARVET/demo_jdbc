package fr.diginamic.jdbc.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.mariadb.jdbc.Connection;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoJdbc {

	public static void main(String[] args) {
		FournisseurDAO fournisseurDao = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			fournisseurDao = new FournisseurDaoJdbc(DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", ""));
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
		

	}

}
