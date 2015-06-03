package jwf.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import jwf.constantes.BddConstantesKeys;
import jwf.modele.Administrateur;
import jwf.modele.Album;
import jwf.modele.Artiste;
import jwf.modele.Client;



public class ToGet extends Database {
	

	public ToGet() {
		// declaration des propriétés de connexion
		  Properties props = new Properties();
		  props.setProperty("user",BddConstantesKeys.login);
		  props.setProperty("password",BddConstantesKeys.motDePasse);
		  props.setProperty("autoReconnect", "true");
	
	}
	
	/**
	 * recupere la liste complete des clients 
	 * @param name
	 * @param mpd
	 * @return
	 */
	public ArrayList<Client> ToGetAllClient(){
		ArrayList<Client> cls = new ArrayList<Client>() ;
		OpenConnection();
		requete = "select "+BddConstantesKeys.SQL_CLIENT_ID+","+BddConstantesKeys.SQL_CLIENT_MPD+","+BddConstantesKeys.SQL_CLIENT_NOM
		+","+BddConstantesKeys.SQL_CLIENT_NUMTEL+","+BddConstantesKeys.SQL_CLIENT_PRENOM+","+BddConstantesKeys.SQL_CLIENT_PSEUDO+","+BddConstantesKeys.SQL_CLIENT_RUE+","
		+BddConstantesKeys.SQL_CLIENT_VILLE+","+BddConstantesKeys.SQL_CLIENT_CODEP+" From "+BddConstantesKeys.SQL_TABLE_CLIENT+";";
		try {
			ResultSet result = st.executeQuery(requete);
			while (result.next()){
			
			Client cl = new Client();
			cl.setId(result.getInt(1));
			cl.setMdp(result.getString(2));
			cl.setNom(result.getString(3));
			cl.setNumtel(result.getString(4));
			cl.setPrenom(result.getString(5));
			cl.setPseudo(result.getString(6));
			cl.setRue(result.getString(7));
			cl.setVille(result.getString(8));
			cl.setCodeP(result.getString(9));
			cls.add(cl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Requete SQL : "+requete);
		}
		finally{
			System.out.println("Requete SQL : "+requete);
			CloseConnection();
		}
	
		return cls;	
	}
	
	/**
	 * recupere les données dun client 
	 * @param name
	 * @param mpd
	 * @return
	 */
	public Client ToGetclient(String pseudo , String mpd){
		Client cl =null;
		OpenConnection();
		requete = "select "+BddConstantesKeys.SQL_CLIENT_ID+","+BddConstantesKeys.SQL_CLIENT_MPD+","+BddConstantesKeys.SQL_CLIENT_NOM+","
		+BddConstantesKeys.SQL_CLIENT_NUMTEL+","+BddConstantesKeys.SQL_CLIENT_PRENOM+","+BddConstantesKeys.SQL_CLIENT_PSEUDO+","+BddConstantesKeys.SQL_CLIENT_RUE+","
		+BddConstantesKeys.SQL_CLIENT_VILLE+",é"+BddConstantesKeys.SQL_CLIENT_CODEP+" From "+BddConstantesKeys.SQL_TABLE_CLIENT+" where "+ BddConstantesKeys.SQL_CLIENT_PSEUDO +"= "+ pseudo +
		" and "+BddConstantesKeys.SQL_CLIENT_MPD +"="+ mpd+"";
		try {
			ResultSet result = st.executeQuery(requete);
			cl = new Client();
			cl.setId(result.getInt(1));
			cl.setMdp(result.getString(2));
			cl.setNom(result.getString(3));
			cl.setNumtel(result.getString(4));
			cl.setPrenom(result.getString(5));
			cl.setPseudo(result.getString(6));
			cl.setRue(result.getString(7));
			cl.setVille(result.getString(8));
			cl.setCodeP(result.getString(9));
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Requete SQL : "+requete);
		}
		finally{
			CloseConnection();
		}
	
		return cl;	
	}
	
	/**
	 * recupere tout les Albums
	 * @param name
	 * @param mpd
	 * @return
	 */
	public ArrayList<Album> ToGetAllAlbum(){
		
		Album album =null;
		OpenConnection();
		ArrayList<Album> albums = new ArrayList<Album>();
		requete ="select "+BddConstantesKeys.SQL_ALBUM_ID+","+BddConstantesKeys.SQL_ALBUM_NB_TITRE+","+BddConstantesKeys.SQL_ALBUM_NOM+
				","+ BddConstantesKeys.SQL_ALBUM_NUM_IMAGE+ ","+ BddConstantesKeys.SQL_ALBUM_NUMGENRE+ ","+ BddConstantesKeys.SQL_ALBUM_PRIX+
				","+ BddConstantesKeys.SQL_ALBUM_PRIX_REDUC+","+ BddConstantesKeys.SQL_ALBUM_DATE_AJOUT+" from "+BddConstantesKeys.SQL_TABLE_ALBUM ; 
		try {
			ResultSet result = st.executeQuery(requete);
			while (result.next()){
			
				album = new Album();
				//album.setDateAjout(result.getDate(8));
				album.setId(result.getInt(1));
				album.setIdImage(result.getInt(4));
				album.setNbTitre(result.getInt(2));
				album.setNom(result.getString(3));
				album.setNumGenre(result.getInt(5));
				album.setPrix(result.getInt(6));
				album.setPrixReduc(result.getInt(7));
				albums.add(album);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Requete SQL : "+requete);
		}finally{
			CloseConnection();
		}
		
		
		return albums;
		
	}
	
	public ArrayList<Administrateur> ToGetAllAdmin(){
		
		ArrayList<Administrateur> admins = new ArrayList<Administrateur>();
		OpenConnection();
		requete = "select "+BddConstantesKeys.SQL_ADMIN_ID+","+BddConstantesKeys.SQL_ADMIN_MPD+","+BddConstantesKeys.SQL_ADMIN_PSEUDO
				+" from "+BddConstantesKeys.SQL_TABLE_ADMIN + " ;";
		try {
			ResultSet result = st.executeQuery(requete);
			
			while(result.next()){
			Administrateur admin = new Administrateur();
			admin.setId(result.getInt(1));
			admin.setMotDePasse(result.getString(2));
			admin.setPseudo(result.getString(3));
			admins.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Requete SQL : "+requete);
		}
		finally{
			CloseConnection();
		}
		
		return admins;
	}
	
	
	public Administrateur ToGetAdmin(String name , String mdp){
		
		Administrateur admin = null;
		OpenConnection();
		requete = "select "+BddConstantesKeys.SQL_ADMIN_ID+","+BddConstantesKeys.SQL_ADMIN_MPD+","+BddConstantesKeys.SQL_ADMIN_PSEUDO
				+" from "+BddConstantesKeys.SQL_TABLE_ADMIN + " where "+BddConstantesKeys.SQL_ADMIN_PSEUDO +" = "+ name +" and "+ BddConstantesKeys.SQL_ADMIN_MPD+" = "+mdp;
		try {
			ResultSet result = st.executeQuery(requete);
			result.next();
			admin = new Administrateur();
			admin.setId(result.getInt(1));
			admin.setMotDePasse(result.getString(2));
			admin.setPseudo(result.getString(3));
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Requete SQL : "+requete);
		}
		finally{
			CloseConnection();
		}
		
		return admin;
	}
	public ArrayList<Artiste> ToGetAllArtiste(){
		
		ArrayList<Artiste> listArtiste = new ArrayList<Artiste>();
		OpenConnection();
		requete ="select "+BddConstantesKeys.SQL_ARTISTE_ID+","+BddConstantesKeys.SQL_ARTISTE_ID_ALBUM+","+BddConstantesKeys.SQL_ARTISTE_NOM+
				","+ BddConstantesKeys.SQL_ARTISTE_NUMGENRE +" from "+BddConstantesKeys.SQL_TABLE_ARTISTE ; 
		try {
			ResultSet result = st.executeQuery(requete);
			while ( result.next()){
				Artiste artiste = new Artiste();
				artiste.setIdalbum(result.getInt(2));
				artiste.setIdArtiste(result.getInt(1));
				artiste.setNom(result.getString(3));
				artiste.setNumgenre(result.getInt(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Requete SQL : "+requete);
		}finally{
			CloseConnection();
		}
		
		
		
		return listArtiste;
	}
	
	public Artiste ToGetArtiste(String name){
		
		Artiste artiste =null;
		OpenConnection();
		requete ="select "+BddConstantesKeys.SQL_ARTISTE_ID+","+BddConstantesKeys.SQL_ARTISTE_ID_ALBUM+","+BddConstantesKeys.SQL_ARTISTE_NOM+
				","+ BddConstantesKeys.SQL_ARTISTE_NUMGENRE +" from "+BddConstantesKeys.SQL_TABLE_ARTISTE +" where "+BddConstantesKeys.SQL_ARTISTE_NOM+ " = "+name ; 
		try {
			ResultSet result = st.executeQuery(requete);
				result.next();
				artiste = new Artiste();
				artiste.setIdalbum(result.getInt(2));
				artiste.setIdArtiste(result.getInt(1));
				artiste.setNom(result.getString(3));
				artiste.setNumgenre(result.getInt(4));
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Requete SQL : "+requete);
		}finally{
			CloseConnection();
		}
		
		
		
		return artiste;
	}
	public Album ToGetAlbum(String name){
		
		Album album =null;
		OpenConnection();
		requete ="select "+BddConstantesKeys.SQL_ALBUM_ID+","+BddConstantesKeys.SQL_ALBUM_NB_TITRE+","+BddConstantesKeys.SQL_ALBUM_NOM+
				","+ BddConstantesKeys.SQL_ALBUM_NUM_IMAGE+ ","+ BddConstantesKeys.SQL_ALBUM_NUMGENRE+ ","+ BddConstantesKeys.SQL_ALBUM_PRIX+
				","+ BddConstantesKeys.SQL_ALBUM_PRIX_REDUC+","+ BddConstantesKeys.SQL_ALBUM_DATE_AJOUT+" from "+BddConstantesKeys.SQL_TABLE_ARTISTE +" where "+BddConstantesKeys.SQL_ARTISTE_NOM+ " = "+name ; 
		try {
			ResultSet result = st.executeQuery(requete);
			
			album = new Album();
			album.setDateAjout(result.getDate(8));
			album.setId(result.getInt(1));
			album.setIdImage(result.getInt(4));
			album.setNbTitre(result.getInt(2));
			album.setNom(result.getString(3));
			album.setNumGenre(result.getInt(5));
			album.setPrix(result.getInt(6));
			album.setPrixReduc(result.getInt(7));
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Requete SQL : "+requete);
		}finally{
			CloseConnection();
		}
		
		
		return album;
		
	}
	
	


}
