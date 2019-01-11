package mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DAOFactory;
import dao.UtilisateurDAO;
import modele.metier.Rang;
import modele.metier.Utilisateur;
import stibox.main;

public class MYSQLUtilisateurDAO implements UtilisateurDAO {
	
	private static MYSQLUtilisateurDAO instance;
	DAOFactory daos = main.getDaoFactory();

	public static MYSQLUtilisateurDAO getInstance(){
		if (instance==null){
			instance = new MYSQLUtilisateurDAO();
		}
		return instance;
	}
	
	@Override
	public List<Utilisateur> afficherListe() {
		try {
			List<Utilisateur> plist = new ArrayList<Utilisateur>();
			Statement requete = Connexion.getInstance().createStatement();
			ResultSet res =  requete.executeQuery("SELECT * FROM Utilisateur");
			Rang r = new Rang();
			while (res.next()) {
				r.setNo_rang(res.getInt(5));
				Utilisateur p = new Utilisateur(res.getString(1), res.getString(2), res.getString(3), res.getInt(4), daos.getRangDAO().getById(r), res.getString(6));
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
	public void ajouter(Utilisateur p) {
		try {

			PreparedStatement req = Connexion.getInstance().prepareStatement(
					"insert into Utilisateur (identifiant,nom,prenom,age,rang) values(?,?,?,?,?,?)");
			
			req.setString(1, p.getidentifiant());	
			req.setString(2, p.getNom());
			req.setString(3, p.getPrenom());
			req.setInt(4, p.getAge());	
			req.setInt(5, p.getRang().getNo_rang());
			req.setString(6, p.getMdp());		
			req.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("Erreur Ajout  " + sqle.getMessage());
		}
		
	}

	@Override
	public void modifier(Utilisateur p) {
		try {
			PreparedStatement req = Connexion.getInstance().prepareStatement("update Utilisateur set nom=?, prenom=?, age=?, rang=?, mdp=? where identifiant = ?");
			
			req.setString(1, p.getNom());
			req.setString(2, p.getPrenom());
			req.setInt(3, p.getAge());	
			req.setInt(4, p.getRang().getNo_rang());
			req.setString(5, p.getMdp());	
			req.setString(6, p.getidentifiant());	
			req.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Erreur modification  " + sqle.getMessage());
		}

	}

	@Override
	public void supprimer(Utilisateur p) {
		try {
			PreparedStatement req = Connexion.getInstance().prepareStatement("delete from Utilisateur where identifiant=?");
			req.setString(1, p.getidentifiant());
			req.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Erreur, la Utilisateur ne peut être supprimé  " + sqle.getMessage());
		}
	}

	@Override
	public Utilisateur getById(Utilisateur p) {
		int nbid = 0;
		try {

			String query = "Select count(id) from Utilisateur where identifiant =" + p.getidentifiant();
			Statement requetetestid = Connexion.getInstance().createStatement();
			ResultSet res = requetetestid.executeQuery(query);
			res.next();
			nbid = res.getInt(1);
			Rang r = new Rang();
			if (nbid == 0) {
				System.out.println("Aucune Utilisateur ne possède ce identifiant");
			} else {
				ResultSet res2 = requetetestid.executeQuery("SELECT * FROM Utilisateur where identifiant=" +  p.getidentifiant());
				res2.next();
				r.setNo_rang(res.getInt(5));
				Utilisateur pres = new Utilisateur(res.getString(1), res.getString(2), res.getString(3), res.getInt(4), daos.getRangDAO().getById(r), res.getString(6));
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
	public int auth(Utilisateur object) {
		Utilisateur u = new Utilisateur();
		u = getById(object);
		if(u.getMdp().equalsIgnoreCase(object.getMdp())){
			String id = u.getidentifiant();
			String nom = u.getNom();
			String prenom = u.getPrenom();
			int age = u.getAge();
			int rang = u.getRang().getNo_rang();
			String mdp = u.getMdp();
			//ICI MET LE CODE POUR ECRIRE DANS LE FICHIER!!! FLORIAN
			return 1;
		}
		return 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
