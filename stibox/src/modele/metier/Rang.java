package modele.metier;

public class Rang {
	protected int no_rang;
	protected String nom_rang;
	
	public Rang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rang(int no_rang, String nom_rang) {
		super();
		this.no_rang = no_rang;
		this.nom_rang = nom_rang;
	}
	public int getNo_rang() {
		return no_rang;
	}
	public void setNo_rang(int no_rang) {
		this.no_rang = no_rang;
	}
	public String getNom_rang() {
		return nom_rang;
	}
	public void setNom_rang(String nom_rang) {
		this.nom_rang = nom_rang;
	}
	
	@Override
	public String toString() {
		return "Rang [no_rang=" + no_rang + ", nom_rang=" + nom_rang + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + no_rang;
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
		Rang other = (Rang) obj;
		if (no_rang != other.no_rang)
			return false;
		return true;
	}

}
