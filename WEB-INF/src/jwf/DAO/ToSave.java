package jwf.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import jwf.constantes.BddConstantesKeys;
import jwf.modele.Album;
import jwf.modele.Client;
import jwf.singleton.BddSingleton;

public class ToSave extends Database {

	  
	public ToSave() {
		// declaration des propriétés de connexion
		  Properties props = new Properties();
		  props.setProperty("user",BddConstantesKeys.login);
		  props.setProperty("password",BddConstantesKeys.motDePasse);
		  props.setProperty("autoReconnect", "true");
		 
	}
	
	/**
	 * enregistre un client dans la base de données
	 * @param Client
	
	 * @return
	 */
	public boolean ToSaveClient(Client client ){
		
		boolean rep = false;
		OpenConnection();
		System.err.println(requete);
		requete = "Insert into "+BddConstantesKeys.SQL_TABLE_CLIENT+" ("+BddConstantesKeys.SQL_CLIENT_MPD+","+BddConstantesKeys.SQL_CLIENT_NOM+","
		+BddConstantesKeys.SQL_CLIENT_NUMTEL+","+BddConstantesKeys.SQL_CLIENT_PRENOM+","+BddConstantesKeys.SQL_CLIENT_PSEUDO+","+BddConstantesKeys.SQL_CLIENT_RUE+","
		+BddConstantesKeys.SQL_CLIENT_VILLE+","+BddConstantesKeys.SQL_CLIENT_CODEP+") values ('"+ client.getMdp()+"','"+ client.getNom()+"','"+  client.getNumtel()+"','"+  client.getPrenom() +"','"+ client.getPseudo()+"','"+client.getRue()
		+"','"+ client.getVille()+"',"+  client.getCodeP()+")";
		try {
			int result = st.executeUpdate(requete);
			if(result > 0){
				rep = true;
			//rechargement de la liste des client dans le singleton
			BddSingleton.getInstance().ChargementClients();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			CloseConnection();
		}
		
		return rep;

}
	
public boolean ToSaveAlbum(Album album){
		
		boolean rep = false;
		OpenConnection();
		System.err.println(requete);
		requete = "Insert into "+BddConstantesKeys.SQL_TABLE_ALBUM+" ("+BddConstantesKeys.SQL_ALBUM_ID+","+BddConstantesKeys.SQL_ALBUM_NB_TITRE+","
		+BddConstantesKeys.SQL_ALBUM_NOM+","+BddConstantesKeys.SQL_ALBUM_NUM_IMAGE+","+BddConstantesKeys.SQL_ALBUM_NUMGENRE+","+BddConstantesKeys.SQL_ALBUM_PRIX+","
		+BddConstantesKeys.SQL_ALBUM_PRIX_REDUC+") values ('"+ album.getId()+"','"+ album.getNbTitre()+"','"+  album.getNom()+"','"+  album.getIdImage() +"','"+ album.getNumGenre()+"','"+album.getPrix()
		+"','"+ album.getPrixReduc()+")";
		try {
			int result = st.executeUpdate(requete);
			if(result > 0){
				rep = true;
			//rechargement du singleton
			BddSingleton.getInstance().ChargementAlbums();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			CloseConnection();
		}
		
		return rep;

}
}
