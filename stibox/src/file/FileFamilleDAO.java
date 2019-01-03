package file;

import java.util.ArrayList;
import java.util.List;

import dao.FamilleDAO;
import modele.metier.Famille;

public class FileFamilleDAO implements FamilleDAO{
	private static FileFamilleDAO instance;

	private ArrayList<Famille> donnees;
	Famille famille = new Famille();

	public static FileFamilleDAO getInstance(){
		if (instance==null){
			instance = new FileFamilleDAO();
		}
		return instance;
	}

	private FileFamilleDAO() {

		this.donnees = new ArrayList<Famille>();

		this.donnees.add(new Famille(1, 2));
		this.donnees.add(new Famille(1, 3));
	}
	
	@Override
	public List<Famille> afficherListe() {
		return this.donnees;
		}

	@Override
	public void ajouter(Famille objet) {
		famille = getById(objet);
		if(famille.getNo_patient()>0){
				return;
		}
			// ajout du nouvel objet à la liste
		this.donnees.add(objet);
	}
	
	@Override
	public void modifier(Famille objet) {
		/*// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.donnees.set(idx, objet);
		}*/
		return;
	}

	@Override
	public void supprimer(Famille objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			this.donnees.remove(idx);
		}
	}

	@Override
	public Famille getById(Famille objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Famille(objet.getNo_patient(),objet.getNo_famille()));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

}
