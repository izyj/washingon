package jwf.modele;

public class Genre {
	
	private int  num;
	private String  nom;

	
	public Genre() {
		
	}
	
	public Genre(int n, String nom) {
		this.num = n;
		this.nom =nom;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
