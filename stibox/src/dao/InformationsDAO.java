package dao;

import modele.metier.Informations;

public interface InformationsDAO extends DAO<Informations> {

	void existsfile();

	void update();

}
