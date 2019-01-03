package mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.FamilleDAO;
import modele.metier.Famille;

public class MYSQLFamilleDAO implements FamilleDAO {

	private static MYSQLFamilleDAO instance;

	public static MYSQLFamilleDAO getInstance(){
		if (instance==null){
			instance = new MYSQLFamilleDAO();
		}
		return instance;
	}
	
	@Override
	public List<Famille> afficherListe() {
		try {
			List<Famille> flist = new ArrayList<Famille>();
			Statement requete = Connexion.getInstance().createStatement();
			ResultSet res =  requete.executeQuery("SELECT * FROM Famille");
			while (res.next()) {
				Famille f = new Famille(res.getInt(1), res.getInt(2));
				System.out.println(f.toString());
				flist.add(f);
			}
			return flist;
		} catch (SQLException sqle) {
			System.out.println("Erreur Affichage  " + sqle.getMessage());
		}
		return null;
	}
		

	@Override
	public void ajouter(Famille f) {
		try {

			PreparedStatement req = Connexion.getInstance().prepareStatement(
					"insert into Famille (no_patient,no_famille) values(?,?)");
			
			req.setInt(1, f.getNo_patient());	
			req.setInt(2, f.getNo_famille());	
			req.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("Erreur Ajout  " + sqle.getMessage());
		}
	}

	@Override
	public void modifier(Famille p) {
		/*try {
			PreparedStatement req = Connexion.getInstance().prepareStatement("update Famille set no_famille = ? where no_patient = ? and no_famille = ?");

			req.setInt(1, p.getNo_famille());
			req.setInt(2, p.getNo_patient());	
			req.setInt(3, p.getNo_famille());
			req.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Erreur modification  " + sqle.getMessage());
		}*/
		return;
	}

	@Override
	public void supprimer(Famille f) {
		try {
			PreparedStatement req = Connexion.getInstance().prepareStatement("delete from Famille where no_patient = ? and no_famille = ?");
			req.setInt(1, f.getNo_patient());
			req.setInt(2, f.getNo_famille());
			req.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Erreur, la Famille ne peut être supprimé  " + sqle.getMessage());
		}
	}

	@Override
	public Famille getById(Famille f) {
		int nbid = 0;
		try {

			String query = "Select count() from Famille where no_patient =" + f.getNo_patient() + " and no_famille =" + f.getNo_famille();
			Statement requetetestid = Connexion.getInstance().createStatement();
			ResultSet res = requetetestid.executeQuery(query);
			res.next();
			nbid = res.getInt(1);
			if (nbid == 0) {
				System.out.println("Aucune Famille ne possède cette association");
			} else {
				ResultSet res2 = requetetestid.executeQuery("SELECT * FROM Famille where no_patient =" + f.getNo_patient() + " and no_famille =" + f.getNo_famille());
				res2.next();
				Famille fres = new Famille(res2.getInt(1), res2.getInt(2));
				return fres;

			}

		} catch (SQLException sqle) {
			System.out.println("Erreur affichage  " + sqle.getMessage());
		}

		return null;
	}

}
