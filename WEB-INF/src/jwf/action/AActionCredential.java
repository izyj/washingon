package jwf.action;

import java.util.ArrayList;
import java.util.Iterator;

import jwf.DAO.ToGet;
import jwf.modele.Administrateur;
import jwf.modele.Client;
import jwf.singleton.BddSingleton;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public abstract class AActionCredential implements IAction {

	@Override
	public int setPriority(int priority) {
		return 0;
	}

	@Override
	public int getPriority() {
		return 0;
	}
	
	public static String[] getCredentials() {
		return new String[0];
	}

	/**
	 * Indique si le  pseudo et mdp appartiennent a 
	 * @param pseudo
	 * @param mpd
	 * @return
	 */
	public static boolean hasCredential(String pseudo , String mpd) {
		
		int i = 0 ,g;
		boolean trouver = false;
		//ToGet info = new ToGet();
		ArrayList<Client> listCl = null ;
		ArrayList<Administrateur> lstAdm = null;
		Iterator ite = null;
		while(i<2){
			if(i==0){
				listCl = BddSingleton.ListeBDDClient;
				ite= listCl.listIterator();
				while(ite.hasNext() && trouver == false){
					
					Client cl =(Client) ite.next();
					if(cl.getPseudo() == pseudo && cl.getMdp() == mpd){
						
						trouver = true;
						
					}
				}		
			}
			if(i==1){
				lstAdm = BddSingleton.ListeBDDAdministrateur;
				ite= listCl.listIterator();
				while(ite.hasNext() && trouver == false){
				
					Administrateur adm =(Administrateur) ite.next();
					if(adm.getPseudo() == pseudo && adm.getMotDePasse() == mpd){
						
						trouver = true;
				
					}		
				}
			}
		}
		return trouver;
	}

	@Override
	public abstract void proceed(IContext context);
	


	@Override
	public void addCredential(String role) {
		// Not used
	}

	@Override
	public boolean needsCredentials() {
		// Not used
		return false;
	}

	@Override
	public boolean hasCredential(String[] roles) {
		// Not used
		return false;
	}

}
