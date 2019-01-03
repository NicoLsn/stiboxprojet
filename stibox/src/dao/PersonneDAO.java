package dao;

import java.util.ArrayList;

import modele.metier.Personne;

public interface PersonneDAO extends DAO<Personne> {

	void existsfile();

	int auth(Personne object);

	void update();

}
