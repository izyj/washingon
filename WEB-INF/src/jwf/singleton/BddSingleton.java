package jwf.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import jwf.DAO.ToGet;
import jwf.modele.Administrateur;
import jwf.modele.Album;
import jwf.modele.Client;
import jwf.modele.News;


public final class BddSingleton {

	     private static volatile BddSingleton instance = null;
	 
	     public static ArrayList<Client> ListeBDDClient;
	     public static ArrayList<Administrateur> ListeBDDAdministrateur;
	     public static ArrayList<News> listeBDDNews;
	     public static ArrayList<Album> listeBDDAlbums;
	     public ToGet  recuperation = null;
	     //public static Map<String,String> listeconnection = new  Hashtable<String,String>();
	     

	 
	     /**
	      * Constructeur de l'objet.
	      */
	     private BddSingleton() {
	        
	         super();
	     }
	 
	     public final static BddSingleton getInstance() {
	       
	         if (BddSingleton.instance == null) {
	            
	            synchronized(BddSingleton.class) {
	              if (BddSingleton.instance == null) {
	            	  BddSingleton.instance = new BddSingleton();
	              }
	            }
	         }
	         return BddSingleton.instance;
	     }
	 
	     
	     public final void  ChargementClients(){
	    	 if(recuperation == null)
	    	 recuperation = new ToGet();
	    	 
	    	 ListeBDDClient = recuperation.ToGetAllClient(); 	 
	    	 
	     }
	     
	     public final void ChargementAdmins(){
	    	 if(recuperation == null)
	    	 recuperation = new ToGet();
	    	 
	    	 ListeBDDAdministrateur = recuperation.ToGetAllAdmin();
	     }
	     
	     public final void ChargementAlbums(){
	    	 if(recuperation == null)
	    	 recuperation = new ToGet();
	    	 
	    	 listeBDDAlbums = recuperation.ToGetAllAlbum();
   
	 }
	     
	   public final void ChargementSingleton(){
		   ChargementAlbums();
		   ChargementAdmins();
		   ChargementClients();
	   }
}


