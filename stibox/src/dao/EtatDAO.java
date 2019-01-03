package dao;

import modele.metier.Etat;

public interface EtatDAO  extends DAO<Etat> {

	void existsfile();

	void update();
}
