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
import dao.InformationsDAO;
import modele.metier.Informations;
import stibox.main;

public class FileInformationsDAO implements InformationsDAO {
	
	private static FileInformationsDAO instance;
	DAOFactory daos = main.getDaoFactory();

	private ArrayList<Informations> donnees;
	LocalDate date;
	File file = new File("C://Users/nicolas/Desktop/stibox/informations.txt");
	File folder = new File("C://Users/nicolas/Desktop/stibox");

	public static FileInformationsDAO getInstance(){
		if (instance==null){
			instance = new FileInformationsDAO();
		}
		return instance;
	}

	private FileInformationsDAO() {

		this.donnees = new ArrayList<Informations>();

		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = reader.readLine();
			while(line != null){
				if(line.equals("*")){
					this.donnees.add(new Informations(reader.readLine(),reader.readLine(),reader.readLine(),reader.readLine()));
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
				pr.println(donnees.get(i).getAime());
				pr.println(donnees.get(i).getAime_pas());
				pr.println(donnees.get(i).getActivites());
			}
			pr.close();
			bw.close();
			writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Informations> afficherListe() {
		return this.donnees;
		}

	@Override
	public void ajouter(Informations objet) {
		try {
		FileWriter writer = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(writer);
		PrintWriter pr = new PrintWriter(bw);
		pr.println("*");
		pr.println(objet.getNom_utilisateur());
		pr.println(objet.getAime());
		pr.println(objet.getAime_pas());
		pr.println(objet.getActivites());
		pr.close();
		bw.close();
		writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void modifier(Informations objet) {
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
	public void supprimer(Informations objet) {
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
	public Informations getById(Informations objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		Informations i = objet;
		int idx = this.donnees.indexOf(i);
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

}
