package mysql;

import dao.UtilisateurDAO;
import dao.FamilleDAO;
import dao.InformationsDAO;
import dao.RangDAO;
import dao.DAOFactory;
import dao.EtatDAO;

public class MYSQLDAOFactory extends DAOFactory {

	@Override
	public UtilisateurDAO getUtilisateurDAO() {
		return MYSQLUtilisateurDAO.getInstance();
	}
	
	@Override
	public FamilleDAO getFamilleDAO() {
		return MYSQLFamilleDAO.getInstance();
	}

	@Override
	public EtatDAO getEtatDAO() {
		return MYSQLEtatDAO.getInstance();
	}
	
	@Override
	public InformationsDAO getInformationsDAO() {
		return MYSQLInformationsDAO.getInstance();
	}
	
	@Override
	public RangDAO getRangDAO() {
		return MYSQLRangDAO.getInstance();
	}
}
