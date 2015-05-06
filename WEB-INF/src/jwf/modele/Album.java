package jwf.modele;

import java.util.Date;

public class Album {
	
	private int id; 
	private String nom;
	private int nbTitre;
	private int numGenre;
	private int prix;
	private int prixReduc;
	private int idImage;
	private Date dateAjout;
	private String image;

	public Album() {
	
	}
	
	public Album(int id, String nom, int nbTitre, int numGenre, int prix,int prixReduc, int idImage, Date dateAjout, String image) {
		this.id = id;
		this.nbTitre = nbTitre;
		this.numGenre =numGenre;
		this.prix = prix;
		this.prixReduc =prixReduc;
		this.idImage =idImage;
		this.dateAjout =dateAjout;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbTitre() {
		return nbTitre;
	}

	public void setNbTitre(int nbTitre) {
		this.nbTitre = nbTitre;
	}

	public int getNumGenre() {
		return numGenre;
	}

	public void setNumGenre(int numGenre) {
		this.numGenre = numGenre;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getPrixReduc() {
		return prixReduc;
	}

	public void setPrixReduc(int prixReduc) {
		this.prixReduc = prixReduc;
	}

	public int getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
