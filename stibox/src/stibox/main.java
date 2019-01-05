package stibox;

import dao.DAOFactory;

public class main {
	private static DAOFactory dao = DAOFactory.getDAOFactory(2);

	public static DAOFactory getDaoFactory() {
		return dao;
	}

	public static void setDaoFactory(DAOFactory daoFactory) {
		dao = daoFactory;
	}

	public static void main(String[] args) {
		dao.getUtilisateurDAO().existsfile();
		System.out.println(dao.getUtilisateurDAO().afficherListe());
		dao.getUtilisateurDAO().update();
		/*Utilisateur p = new Utilisateur();
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir nom d'utilisateur:");
		String str = sc.next();
		p.setNom_utilisateur(str);
		System.out.println("Saisir nom :");
		String strrr = sc.next();
		p.setNom(strrr);
		System.out.println("Saisir prenom:");
		String strrrr = sc.next();
		p.setPrenom(strrrr);
		System.out.println("Saisir age:");
		int strrrrr = sc.nextInt();
		p.setAge(strrrrr);
		System.out.println("Saisir rang:");
		int stre = sc.nextInt();
		p.setRang(stre);
		System.out.println("Saisir le mot de passe:");
		String strr = sc.next();
		p.setMdp(strr);
		dao.getUtilisateurDAO().ajouter(p);*/
		/*System.out.println(dao.getUtilisateurDAO().auth(p));
		if(dao.getUtilisateurDAO().auth(p)==1)
			System.out.println("OK");
		else
			System.out.println("Non");
		
	}*/
	}
}

/*

try {
	
	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
	String line = reader.readLine();
	while(line != null){
		if(line.equals("losson28u"))
		System.out.println(reader.readLine());
		line = reader.readLine();
	}
	reader.close();
} catch(IOException e){
	e.printStackTrace();
}

*/