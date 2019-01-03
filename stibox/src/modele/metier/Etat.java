package modele.metier;

import java.time.LocalDate;

public class Etat {
	protected String nom_utilisateur;
	LocalDate date;
	protected String etat;
	protected String nom_intervenant;
	
	public Etat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etat(String nom_utilisateur, LocalDate date, String etat, String nom_intervenant) {
		super();
		this.nom_utilisateur = nom_utilisateur;
		this.date = date;
		this.etat = etat;
		this.nom_intervenant = nom_intervenant;
	}

	public String getNom_utilisateur() {
		return nom_utilisateur;
	}

	public void setNom_utilisateur(String nom_utilisateur) {
		this.nom_utilisateur = nom_utilisateur;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getNom_intervenant() {
		return nom_intervenant;
	}

	public void setNom_intervenant(String nom_intervenant) {
		this.nom_intervenant = nom_intervenant;
	}
	
	@Override
	public String toString() {
		return "Etat [nom_utilisateur=" + nom_utilisateur + ", date=" + date + ", etat=" + etat + "nom_utilisateur=" + nom_utilisateur+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		Etat other = (Etat) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (nom_utilisateur == null) {
			if (other.nom_utilisateur != null)
				return false;
		} else if (!nom_utilisateur.equals(other.nom_utilisateur))
			return false;
		return true;
	}
	
	
}
