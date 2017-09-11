package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class ConnexionBDD {
	
		private static ConnexionBDD singleton = null;
		private static Connection con = null;

		final static String url = "jdbc:mysql://localhost/javafx";
		final static String login = "root";
		final static String passwd = "";
		

		private ConnexionBDD() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, login, passwd);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		public static Connection getConnexion() throws Exception{
			if (singleton == null){ 
				singleton = new ConnexionBDD();
			}
			return con;
		}

		public static void close() {
			try {
				if (singleton != null){
					con.close();
					con=null;
					singleton=null;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				System.exit(0);
			}
		}
		
		public void CreateSQLdb() throws Exception{
			try {
				String createDatabaseQuery = "CREATE DATABASE javafx;";
				String useDatabaseQuery = "USE javafx;";
				String createTableQuery = "CREATE TABLE USERFX " + "(IDUSER AUTO_INCREMENT,"
															   + "firstName varchar(15),"
															   + "lastName varchar(15),"
															   + "street varchar(15),"
															   + "city varchar(15),"
															   + "postalcode int,"
															   + "birthday int)";
				
				Statement statement = getConnexion().createStatement();
				statement.executeUpdate(createDatabaseQuery);
				statement.executeUpdate(useDatabaseQuery);
				statement.executeUpdate(createTableQuery);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

}
