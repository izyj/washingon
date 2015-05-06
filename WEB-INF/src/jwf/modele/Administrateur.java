package jwf.modele;

public class Administrateur extends Personne {
	

	private String pseudo;
	private String motDePasse;
	
	public Administrateur() {
	
	}
	public Administrateur(String ps, String mpd) {
		super.id = id;
		this.pseudo = ps;
		this.motDePasse = mpd;
	}
	public Administrateur(int iden,String ps, String mpd) {
		
		this.id = iden;
		this.pseudo = ps;
		this.motDePasse = mpd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	
	

}
