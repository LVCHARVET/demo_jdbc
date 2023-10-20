package demo_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionjdbc {

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