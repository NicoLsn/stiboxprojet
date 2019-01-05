package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.DAOFactory;
import dao.UtilisateurDAO;
import stibox.main;
import modele.metier.Rang;
import modele.metier.Utilisateur;

public class FileUtilisateurDAO implements UtilisateurDAO {
	
	private static FileUtilisateurDAO instance;
	DAOFactory daos = main.getDaoFactory();

	private ArrayList<Utilisateur> donnees;
	LocalDate date;
	File file = new File("C://Users/nicolas/Desktop/stibox/account.txt");
	File folder = new File("C://Users/nicolas/Desktop/stibox");

	public static FileUtilisateurDAO getInstance(){
		if (instance==null){
			instance = new FileUtilisateurDAO();
		}
		return instance;
	}

	private FileUtilisateurDAO() {

		this.donnees = new ArrayList<Utilisateur>();
		Rang r = new Rang();

		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = reader.readLine();
			while(line != null){
				if(line.equals("*")){
					String id = reader.readLine();
					String nom = reader.readLine();
					String prenom = reader.readLine();
					int age = Integer.parseInt(reader.readLine());
					r.setNo_rang(Integer.parseInt(reader.readLine()));
					String mdp = reader.readLine();
					this.donnees.add(new Utilisateur(id,nom,prenom,age,daos.getRangDAO().getById(r),mdp));
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public int auth(Utilisateur object){
		System.out.println(object.toString());
		Utilisateur p = daos.getUtilisateurDAO().getById(object);
		System.out.println(p.getMdp() +" - "+ object.getMdp());
		if(p.getMdp().equalsIgnoreCase(object.getMdp()))
			return 1;
		
		return 0;
	}
	
	@Override
	public void existsfile(){
		if(!folder.exists()){
			folder.mkdir();
		}
		
		if(!file.exists()){ 
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update() {
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("Derniere mise a jour: " + date.now());
			bw.newLine();
			bw.close();
			writer.close();
			writer = new FileWriter(file, true);
			bw = new BufferedWriter(writer);
			PrintWriter pr = new PrintWriter(bw);
			for (int i = 0; i < donnees.size(); i++) {
				pr.println("*");
				pr.println(donnees.get(i).getidentifiant());
				pr.println(donnees.get(i).getNom());
				pr.println(donnees.get(i).getPrenom());
				pr.println(donnees.get(i).getAge());
				pr.println(donnees.get(i).getRang());
				pr.println(donnees.get(i).getMdp());
			}
			pr.close();
			bw.close();
			writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Utilisateur> afficherListe() {
		return this.donnees;
		}

	@Override
	public void ajouter(Utilisateur objet) {
		try {
		FileWriter writer = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(writer);
		PrintWriter pr = new PrintWriter(bw);
		pr.println("*");
		pr.println(objet.getidentifiant());
		pr.println(objet.getNom());
		pr.println(objet.getPrenom());
		pr.println(objet.getAge());
		pr.println(objet.getRang());
		pr.println(objet.getMdp());
		pr.close();
		bw.close();
		writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void modifier(Utilisateur objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			this.donnees.set(idx, objet);
			update();
		}
	}

	@Override
	public void supprimer(Utilisateur objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			this.donnees.remove(idx);
			update();
		}
	}

	@Override
	public Utilisateur getById(Utilisateur objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		Utilisateur p = new Utilisateur(objet.getidentifiant(),"","",0,null,"");
		int idx = this.donnees.indexOf(p);
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

}