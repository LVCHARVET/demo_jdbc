package fr.diginamic.recensement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import demo_jdbc.ExceptionTechnique;

public class DepartementDAOJdbc implements DepartementDAO {

	private Connection connection;

	public DepartementDAOJdbc(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Departement> extraire() {
		List<Departement> arrayDepartement = new ArrayList<>();
		try {
			Statement stat = connection.createStatement();
			ResultSet curseur = stat.executeQuery("SELECT id, codeDepartement FROM DEPTS");
			while (curseur.next()) {
				int id = curseur.getInt("id");
				String codeDepartement = curseur.getString("codeDepartement");

				Departement departementActuel = new Departement(id, codeDepartement);
				arrayDepartement.add(departementActuel);
			}

			curseur.close();

			stat.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la connection a la DB", e);
		}
		System.out.println("connection établi a la bdd : " + connection);
		return arrayDepartement;
	}

	@Override
	public void insert(Departement departement) {
		PreparedStatement insertDepartement = null;
		int nb;
		try {

			insertDepartement = connection.prepareStatement("INSERT INTO DEPTS (codeDepartement) VALUES (?)");

			insertDepartement.setString(1, departement.getCodeDepartement());
			nb = insertDepartement.executeUpdate();

			insertDepartement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de l'insertion a la DB", e);
		}
		System.out.println(nb);

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		PreparedStatement updateDepartement = null;
		int nb;
		try {
			updateDepartement = connection
					.prepareStatement("UPDATE DEPTS SET codeDepartement=? WHERE codeDepartement=?");
			updateDepartement.setString(1, nouveauNom);
			updateDepartement.setString(2, ancienNom);
			nb = updateDepartement.executeUpdate();
			updateDepartement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la modification d'élément vers la DB", e);
		}
		System.out.println(nb);

		return nb;
	}

	@Override
	public boolean delete(Departement departement) {
		PreparedStatement deleteDepartementement = null;
		int nb;
		try {
			deleteDepartementement = connection.prepareStatement("DELETE FROM DEPTS WHERE codeDepartement=?");
			deleteDepartementement.setString(1, departement.getCodeDepartement());
			nb = deleteDepartementement.executeUpdate();
			deleteDepartementement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());

			throw new ExceptionTechnique("Un probléme est survenu lors de la suppression d'élément vers la DB", e);
		}
		System.out.println(nb);
		return false;
	}

}
