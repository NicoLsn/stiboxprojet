package mysql;

import dao.PersonneDAO;
import dao.FamilleDAO;
import dao.InformationsDAO;
import dao.DAOFactory;
import dao.EtatDAO;

public class MYSQLDAOFactory extends DAOFactory {

	@Override
	public PersonneDAO getPersonneDAO() {
		return MYSQLPersonneDAO.getInstance();
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
}
