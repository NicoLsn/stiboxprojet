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
import dao.RangDAO;
import modele.metier.Rang;
import stibox.main;

public class FileRangDAO implements RangDAO {
	
	private static FileRangDAO instance;
	DAOFactory daos = main.getDaoFactory();

	private ArrayList<Rang> donnees;
	LocalDate date;
	File file = new File("C://Users/nicolas/Desktop/stibox/rang.txt");
	File folder = new File("C://Users/nicolas/Desktop/stibox");

	public static FileRangDAO getInstance(){
		if (instance==null){
			instance = new FileRangDAO();
		}
		return instance;
	}

	private FileRangDAO() {

		this.donnees = new ArrayList<Rang>();

		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = reader.readLine();
			while(line != null){
				if(line.equals("*")){
					this.donnees.add(new Rang(Integer.parseInt(reader.readLine()),reader.readLine()));
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
				pr.println(donnees.get(i).getNo_rang());
				pr.println(donnees.get(i).getNom_rang());
			}
			pr.close();
			bw.close();
			writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Rang> afficherListe() {
		return this.donnees;
		}

	@Override
	public void ajouter(Rang objet) {
		try {
		FileWriter writer = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(writer);
		PrintWriter pr = new PrintWriter(bw);
		pr.println("*");
		pr.println(objet.getNo_rang());
		pr.println(objet.getNom_rang());
		pr.close();
		bw.close();
		writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void modifier(Rang objet) {
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
	public void supprimer(Rang objet) {
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
	public Rang getById(Rang objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		Rang p = new Rang(objet.getNo_rang(),"");
		int idx = this.donnees.indexOf(p);
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
}
