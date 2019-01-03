package modele.metier;

public class Famille {

	protected int no_patient; 
	protected int no_famille;
	
	public Famille(int no_patient, int no_famille) {
		super();
		this.no_patient = no_patient;
		this.no_famille = no_famille;
	}
	
	public Famille() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getNo_patient() {
		return no_patient;
	}
	
	public void setNo_patient(int no_patient) {
		this.no_patient = no_patient;
	}
	
	public int getNo_famille() {
		return no_famille;
	}
	
	public void setNo_famille(int no_famille) {
		this.no_famille = no_famille;
	}
	
	@Override
	public String toString() {
		return "Famille [no_patient=" + no_patient + ", no_famille=" + no_famille + "]";
	} 
	
}
