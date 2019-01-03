package file;

import dao.PersonneDAO;
import dao.FamilleDAO;
import dao.InformationsDAO;
import dao.DAOFactory;
import dao.EtatDAO;

public class FileDAOFactory extends DAOFactory {

	@Override
	public PersonneDAO getPersonneDAO() {
		return FilePersonneDAO.getInstance();
	}
	
	@Override
	public FamilleDAO getFamilleDAO() {
		return FileFamilleDAO.getInstance();
	}

	@Override
	public EtatDAO getEtatDAO() {
		return FileEtatDAO.getInstance();
	}
	
	@Override
	public InformationsDAO getInformationsDAO() {
		return FileInformationsDAO.getInstance();
	}

}
