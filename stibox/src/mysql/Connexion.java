package mysql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {

	private static Connection maConnexion;
	
	private Connexion() {
		
		Properties accesBdd = new Properties();
		File fBdd = new File("src/bdd.properties");
		
		try {
		FileInputStream source = new FileInputStream(fBdd);
		accesBdd.loadFromXML(source);
		} catch (IOException ioe) {
		ioe.printStackTrace();
		}

		String url = accesBdd.getProperty("adresse_ip");
		String login = accesBdd.getProperty("login");
		String pwd =  accesBdd.getProperty("pass");
		
		try {
			maConnexion = DriverManager.getConnection(url, login, pwd);
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion " + sqle.getMessage());
		}
	}
	

	 public static Connection getInstance(){

		    if(maConnexion == null){

		      new Connexion();
		    }

		    return maConnexion;   

		  } 
}
