package dao;

import file.FileDAOFactory;
import mysql.MYSQLDAOFactory;

public abstract class DAOFactory  {
	
	private static final int MYSQL =1;
	private static final int File =2;
	
	public static DAOFactory getDAOFactory(int cible) {
		DAOFactory daoF = null;
			switch (cible) {
				case MYSQL:
					daoF = new MYSQLDAOFactory();
					break;
				case File:
					daoF = new FileDAOFactory();
					break;
							}
		return daoF;
		}


	public abstract UtilisateurDAO getUtilisateurDAO();
	public abstract FamilleDAO getFamilleDAO();
	public abstract RangDAO getRangDAO();
	public abstract EtatDAO getEtatDAO();
	public abstract InformationsDAO getInformationsDAO();

		
}
