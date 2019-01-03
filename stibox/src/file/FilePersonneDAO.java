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
import dao.PersonneDAO;
import stibox.main;
import modele.metier.Personne;

public class FilePersonneDAO implements PersonneDAO {
	
	private static FilePersonneDAO instance;
	DAOFactory daos = main.getDaoFactory();

	private ArrayList<Personne> donnees;
	LocalDate date;
	File file = new File("C://Users/nicolas/Desktop/stibox/account.txt");
	File folder = new File("C://Users/nicolas/Desktop/stibox");

	public static FilePersonneDAO getInstance(){
		if (instance==null){
			instance = new FilePersonneDAO();
		}
		return instance;
	}

	private FilePersonneDAO() {

		this.donnees = new ArrayList<Personne>();

		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = reader.readLine();
			while(line != null){
				if(line.equals("*")){
					this.donnees.add(new Personne(reader.readLine(),reader.readLine(),reader.readLine(),Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine()),reader.readLine()));
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public int auth(Personne object){
		System.out.println(object.toString());
		Personne p = daos.getPersonneDAO().getById(object);
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
				pr.println(donnees.get(i).getNom_utilisateur());
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
	public List<Personne> afficherListe() {
		return this.donnees;
		}

	@Override
	public void ajouter(Personne objet) {
		try {
		FileWriter writer = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(writer);
		PrintWriter pr = new PrintWriter(bw);
		pr.println("*");
		pr.println(objet.getNom_utilisateur());
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
	public void modifier(Personne objet) {
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
	public void supprimer(Personne objet) {
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
	public Personne getById(Personne objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		Personne p = new Personne(objet.getNom_utilisateur(),"","",0,0,"");
		int idx = this.donnees.indexOf(p);
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

}