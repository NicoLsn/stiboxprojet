package file;

import dao.UtilisateurDAO;
import dao.FamilleDAO;
import dao.InformationsDAO;
import dao.RangDAO;
import dao.DAOFactory;
import dao.EtatDAO;

public class FileDAOFactory extends DAOFactory {

	@Override
	public UtilisateurDAO getUtilisateurDAO() {
		return FileUtilisateurDAO.getInstance();
	}
	
	@Override
	public RangDAO getRangDAO() {
		return FileRangDAO.getInstance();
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
