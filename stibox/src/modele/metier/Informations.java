package modele.metier;

public class Informations {

	protected String nom_utilisateur;
	protected String aime;
	protected String aime_pas;
	protected String activites;
	
	public String getNom_utilisateur() {
		return nom_utilisateur;
	}
	public void setNom_utilisateur(String nom_utilisateur) {
		this.nom_utilisateur = nom_utilisateur;
	}
	public String getAime() {
		return aime;
	}
	public void setAime(String aime) {
		this.aime = aime;
	}
	public String getAime_pas() {
		return aime_pas;
	}
	public void setAime_pas(String aime_pas) {
		this.aime_pas = aime_pas;
	}
	public String getActivites() {
		return activites;
	}
	public void setActivites(String activites) {
		this.activites = activites;
	}
	
	public Informations(String nom_utilisateur, String aime, String aime_pas, String activites) {
		super();
		this.nom_utilisateur = nom_utilisateur;
		this.aime = aime;
		this.aime_pas = aime_pas;
		this.activites = activites;
	}
	
	public Informations() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Information [nmo_utilisateur=" + nom_utilisateur + ", aime=" + aime + ", aime_pas=" + aime_pas
				+ ", activites=" + activites + "]";
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
		Informations other = (Informations) obj;
		if (nom_utilisateur == null) {
			if (other.nom_utilisateur != null)
				return false;
		} else if (!nom_utilisateur.equals(other.nom_utilisateur))
			return false;
		return true;
	}
	
	


}
