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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.EtatDAO;
import modele.metier.Etat;

public class FileEtatDAO implements EtatDAO{
	private static FileEtatDAO instance;

	private ArrayList<Etat> donnees;
	LocalDate date;
	File file = new File("C://Users/nicolas/Desktop/stibox/etat.txt");
	File folder = new File("C://Users/nicolas/Desktop/stibox");
	Etat etat = new Etat();

	public static FileEtatDAO getInstance(){
		if (instance==null){
			instance = new FileEtatDAO();
		}
		return instance;
	}

	private FileEtatDAO() {

		this.donnees = new ArrayList<Etat>();

		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = reader.readLine();
			DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			while(line != null){
				if(line.equals("*")){
					this.donnees.add(new Etat(reader.readLine(),LocalDate.parse(reader.readLine(), formatage),reader.readLine(),reader.readLine()));
				}
				line = reader.readLine();
			}
			reader.close();
		} catch(IOException e){
			e.printStackTrace();
		}
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
				pr.println(donnees.get(i).getNom_intervenant());
				pr.println(donnees.get(i).getDate());
				pr.println(donnees.get(i).getEtat());
			}
			pr.close();
			bw.close();
			writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Etat> afficherListe() {
		return this.donnees;
	}

	@Override
	public void ajouter(Etat objet) {
		try {
			FileWriter writer = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(writer);
			PrintWriter pr = new PrintWriter(bw);
			pr.println("*");
			pr.println(objet.getNom_utilisateur());
			pr.println(objet.getNom_intervenant());
			pr.println(objet.getDate());
			pr.println(objet.getEtat());
			pr.close();
			bw.close();
			writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void modifier(Etat objet) {
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
	public void supprimer(Etat objet) {
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
	public Etat getById(Etat objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		Etat e = objet;
		int idx = this.donnees.indexOf(e);
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
}
