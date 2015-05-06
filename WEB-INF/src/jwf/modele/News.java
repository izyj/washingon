package jwf.modele;

import java.util.Date;

public class News {

	private int id;
	private String auteur;
	private String titre;
	private Date date;
	private String text;
	
	public News() {
		
	}
	

	public News(int id, String auteur, String titre, Date date , String txt) {
		
		this.id= id;
		this.auteur = auteur;
		this.titre = titre;
		this.date = date;
		this.text =txt;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	
}
