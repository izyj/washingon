package jwf.modele;

public class Artiste {
	
	private int idArtiste;
	private String nom;
	private int idalbum;
	private int numgenre;

	public Artiste() {
		
	}
	
	public Artiste(int id, String name, int idAlb, int numG){
		
		this.idalbum = idAlb;
		this.idArtiste = id ;
		this.nom = name;
		this.numgenre = numG;
	}

	public int getIdArtiste() {
		return idArtiste;
	}

	public void setIdArtiste(int idArtiste) {
		this.idArtiste = idArtiste;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdalbum() {
		return idalbum;
	}

	public void setIdalbum(int idalbum) {
		this.idalbum = idalbum;
	}

	public int getNumgenre() {
		return numgenre;
	}

	public void setNumgenre(int numgenre) {
		this.numgenre = numgenre;
	}
	
	
}
