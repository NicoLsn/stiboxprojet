package modele.metier;

public class Personne {

	protected String nom_utilisateur; 
	protected String nom; 
	protected String prenom; 
	protected int age; 
	protected int rang;
	protected String mdp;

	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personne(String nom_utilisateur, String nom, String prenom, int age, int rang, String mdp) {
		super();
		this.nom_utilisateur = nom_utilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.rang = rang;
		this.mdp = mdp;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getRang() {
		return rang;
	}
	public void setRang(int rang) {
		this.rang = rang;
	} 

	public String getNom_utilisateur() {
		return nom_utilisateur;
	}


	public void setNom_utilisateur(String nom_utilisateur) {
		this.nom_utilisateur = nom_utilisateur;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	@Override
	public String toString() {
		return "personne [nom_utilisateur=" + nom_utilisateur + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", rang=" + rang
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom_utilisateur == null) ? 0 : nom_utilisateur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (nom_utilisateur == null) {
			if (other.nom_utilisateur != null)
				return false;
		} else if (!nom_utilisateur.equals(other.nom_utilisateur))
			return false;
		return true;
	}

}
