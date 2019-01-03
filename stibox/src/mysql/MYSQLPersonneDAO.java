package mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.PersonneDAO;
import modele.metier.Personne;

public class MYSQLPersonneDAO implements PersonneDAO {
	
	private static MYSQLPersonneDAO instance;

	public static MYSQLPersonneDAO getInstance(){
		if (instance==null){
			instance = new MYSQLPersonneDAO();
		}
		return instance;
	}
	
	@Override
	public List<Personne> afficherListe() {
		try {
			List<Personne> plist = new ArrayList<Personne>();
			Statement requete = Connexion.getInstance().createStatement();
			ResultSet res =  requete.executeQuery("SELECT * FROM personne");
			while (res.next()) {
				Personne p = new Personne(res.getString(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getString(6));
				System.out.println(p.toString());
				plist.add(p);
			}
			return plist;
		} catch (SQLException sqle) {
			System.out.println("Erreur Affichage  " + sqle.getMessage());
		}
		return null;
	}
		

	@Override
	public void ajouter(Personne p) {
		try {

			PreparedStatement req = Connexion.getInstance().prepareStatement(
					"insert into personne (nom_utilisateur,nom,prenom,age,rang) values(?,?,?,?,?,?)");
			
			req.setString(1, p.getNom_utilisateur());	
			req.setString(2, p.getNom());
			req.setString(3, p.getPrenom());
			req.setInt(4, p.getAge());	
			req.setInt(5, p.getRang());
			req.setString(6, p.getMdp());		
			req.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("Erreur Ajout  " + sqle.getMessage());
		}
		
	}

	@Override
	public void modifier(Personne p) {
		try {
			PreparedStatement req = Connexion.getInstance().prepareStatement("update personne set nom=?, prenom=?, age=?, rang=?, mdp=? where nom_utilisateur = ?");
			
			req.setString(1, p.getNom());
			req.setString(2, p.getPrenom());
			req.setInt(3, p.getAge());	
			req.setInt(4, p.getRang());
			req.setString(5, p.getMdp());	
			req.setString(6, p.getNom_utilisateur());	
			req.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Erreur modification  " + sqle.getMessage());
		}

	}

	@Override
	public void supprimer(Personne p) {
		try {
			PreparedStatement req = Connexion.getInstance().prepareStatement("delete from personne where nom_utilisateur=?");
			req.setString(1, p.getNom_utilisateur());
			req.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Erreur, la personne ne peut être supprimé  " + sqle.getMessage());
		}
	}

	@Override
	public Personne getById(Personne p) {
		int nbid = 0;
		try {

			String query = "Select count(id) from personne where nom_utilisateur =" + p.getNom_utilisateur();
			Statement requetetestid = Connexion.getInstance().createStatement();
			ResultSet res = requetetestid.executeQuery(query);
			res.next();
			nbid = res.getInt(1);
			if (nbid == 0) {
				System.out.println("Aucune Personne ne possède ce nom_utilisateur");
			} else {
				ResultSet res2 = requetetestid.executeQuery("SELECT * FROM personne where nom_utilisateur=" +  p.getNom_utilisateur());
				res2.next();
				Personne pres = new Personne(res2.getString(1), res2.getString(2), res2.getString(3), res2.getInt(4), res2.getInt(5), res2.getString(6));
				return pres;

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
	public int auth(Personne object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
