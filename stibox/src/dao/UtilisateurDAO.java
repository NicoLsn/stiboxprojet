package dao;

import modele.metier.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {

	void existsfile();

	int auth(Utilisateur object);

	void update();

}
