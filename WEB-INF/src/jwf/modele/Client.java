package jwf.modele;

public class Client extends Personne {
	

	private String CodeP;
	private String ville;
	private String rue;
	private String numtel;
	private String pseudo;
	private String mdp;
	
	public Client( int id, String nom , String prenom , String codep, String ville , String rue, String numt
			,String peseudo , String mdp) {
		
		this.CodeP = codep;
		super.id = id;
		this.mdp = mdp;
		super.nom = nom;
		this.numtel  =  numt;
		super.prenom = prenom;
		this.pseudo = pseudo;
		this.rue = rue;
		this.ville = ville;
	}

	public Client() {
		
	}



	public String getCodeP() {
		return CodeP;
	}

	public void setCodeP(String codeP) {
		CodeP = codeP;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getNumtel() {
		return numtel;
	}

	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	@Override
	public boolean equals(Object client) {
		boolean rep = false ;
	if(	this.pseudo == ((Client)client).pseudo &&
		this.nom == ((Client)client).nom && 
		this.prenom == ((Client)client).prenom &&
		this.CodeP ==  ((Client)client).CodeP &&
		this.id ==  ((Client)client).id &&
		this.mdp ==  ((Client)client).mdp &&
		this.numtel ==  ((Client)client).numtel &&
		this.rue ==  ((Client)client).rue &&
		this.ville ==  ((Client)client).ville){
		
		rep= true;
	}
		return super.equals(client);
	}

	@Override
	public String toString() {
		System.out.println( nom+" "+prenom+" "+pseudo+" "+CodeP+" "+mdp+" "+numtel+" "+rue+" "+ville);
		return super.toString();
	}
}
