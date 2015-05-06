package jwf.modele;

public class Single {

	private int numSingle;
	private String nomSingle;
	private int idAlbum;
	private int numGenre;
	
	public Single() {
	
	}
	
	public Single(int idSingle, String nomSingle, int idAlbum, int numGenre ) {
		
		this.numSingle = idSingle;
		this.idAlbum= idAlbum;
		this.nomSingle = nomSingle;
		this.numGenre = numGenre;
		
	}

	public int getNumSingle() {
		return numSingle;
	}

	public void setNumSingle(int numSingle) {
		this.numSingle = numSingle;
	}

	public String getNomSingle() {
		return nomSingle;
	}

	public void setNomSingle(String nomSingle) {
		this.nomSingle = nomSingle;
	}

	public int getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public int getNumGenre() {
		return numGenre;
	}

	public void setNumGenre(int numGenre) {
		this.numGenre = numGenre;
	}
	
	
}
