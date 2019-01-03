package mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.InformationsDAO;
import modele.metier.Informations;

public class MYSQLInformationsDAO implements InformationsDAO {

	public static InformationsDAO getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Informations> afficherListe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouter(Informations info) {
		try {

			PreparedStatement req = Connexion.getInstance().prepareStatement(
					"insert into Infrormations (nom_utilisateur,aime,aime_pas,acivites) values(?,?,?,?)");
			
			req.setString(1, info.getNom_utilisateur());
			req.setString(2, info.getAime());
			req.setString(3, info.getAime_pas());	
			req.setString(4, info.getActivites());
			req.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("Erreur Ajout  " + sqle.getMessage());
		}
	}

	@Override
	public void modifier(Informations info) {
		try {
			PreparedStatement req = Connexion.getInstance().prepareStatement("update Informations set where aime = ? and aime_pas = ? and activites = ? where nom_utilisateur = ?");

			req.setString(1, info.getNom_utilisateur());
			req.setString(2, info.getAime());	
			req.setString(3, info.getAime_pas());
			req.setString(4, info.getActivites());
			req.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Erreur modification  " + sqle.getMessage());
		}
		return;
	}

	@Override
	public void supprimer(Informations info) {
		try {
			PreparedStatement req = Connexion.getInstance().prepareStatement("delete from Informations where nom_utilisateur = ?");
			req.setString(1, info.getNom_utilisateur());
			req.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Erreur, le patient ne peut être supprimé  " + sqle.getMessage());
		}
	}

	@Override
	public Informations getById(Informations info) {
		int nbid = 0;
		try {

			String query = "Select count() from Informations where nom_utilisateur =" + info.getNom_utilisateur();
			Statement requetetestid = Connexion.getInstance().createStatement();
			ResultSet res = requetetestid.executeQuery(query);
			res.next();
			nbid = res.getInt(1);
			if (nbid == 0) {
				System.out.println("Aucun Patient ne possède cette association");
			} else {
				ResultSet res2 = requetetestid.executeQuery("SELECT * FROM Informations where nom_utilisateur =" + info.getNom_utilisateur());
				res2.next();
				Informations infores = new Informations(res2.getString(1), res2.getString(2),res2.getString(3),res2.getString(4));
				return infores;

			}

		} catch (SQLException sqle) {
			System.out.println("Erreur affichage  " + sqle.getMessage());
		}

		return null;
	}


	@Override
	public void existsfile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
