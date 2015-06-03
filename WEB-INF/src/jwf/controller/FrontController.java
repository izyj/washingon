package jwf.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import jwf.action.AActionCredential;
import jwf.constantes.ConstantsKeys;
import jwf.context.Context;
import jwf.error.JwfErrorHandler;
import jwf.modele.Administrateur;
import jwf.modele.Client;
import jwf.modele.Personne;
import jwf.router.Dispatcher;
import jwf.router.RewriteRule;
import jwf.router.Rewriter;
import jwf.singleton.BddSingleton;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.esgi.web.framework.core.interfaces.IFrontController;
import org.esgi.web.framework.router.interfaces.IDispatcher;
import org.esgi.web.framework.router.interfaces.IRewriter;


public class FrontController extends HttpServlet implements IFrontController {

	private static final long serialVersionUID = 1L;
	
	public static String URIroot = "/JWFISI";
	
	private IRewriter rewriter;
	private IDispatcher dispatcher;
	private Context c;
	
	public void init() {
		rewriter = new Rewriter();
		dispatcher = new Dispatcher();
		
		BddSingleton.getInstance().ChargementSingleton();

		//partie  administrateur
		rewriter.addRule(new RewriteRule(URIroot + "/backOffice", "GET|POST", "jwf.action.pages.admin.BackOffice"));
		// gestion client
		rewriter.addRule(new RewriteRule(URIroot + "/gestionClient", "GET|POST", "jwf.action.pages.admin.client.GestionClient"));
		rewriter.addRule(new RewriteRule(URIroot + "/suppresionClient", "GET|POST", "jwf.action.pages.admin.client.SuppressionClient"));
		rewriter.addRule(new RewriteRule(URIroot + "/suppressionClientAction", "GET|POST", "jwf.action.admin.ActionSuppressionClient"));
		//gestion album
		rewriter.addRule(new RewriteRule(URIroot + "/gestionAlbum", "GET|POST", "jwf.action.pages.admin.album.GestionAlbum"));
		rewriter.addRule(new RewriteRule(URIroot + "/SuppressionAlbum", "GET|POST", "jwf.action.pages.admin.album.SuppressionAlbum"));
		rewriter.addRule(new RewriteRule(URIroot + "/suppressionAlbumAction", "GET|POST", "jwf.action.admin.ActionSuppressionAlbum"));
		rewriter.addRule(new RewriteRule(URIroot + "/AjoutAlbumAction", "GET|POST", "jwf.action.admin.ActionAjoutAlbum"));
		
		//gestion des news
		rewriter.addRule(new RewriteRule(URIroot + "/ajoutNews", "GET|POST", "jwf.action.pages.admin.AjoutNews"));	
		rewriter.addRule(new RewriteRule(URIroot + "/gestionNews", "GET|POST", "jwf.action.pages.admin.AjoutNews"));
		
		//deconnexion
		rewriter.addRule(new RewriteRule(URIroot + "/Logout", "GET|POST", "jwf.action.user.ActionUserLogout"));
		//recuperation du formulaire de connexion
		rewriter.addRule(new RewriteRule(URIroot + "/connection", "GET|POST", "jwf.action.user.ActionUserLogin"));
		//recuperation du formulaire d'inscription
		rewriter.addRule(new RewriteRule(URIroot + "/recuperation", "GET|POST", "jwf.action.user.ActionInscription"));
		// chargement du javascript	
		rewriter.addRule(new RewriteRule(URIroot + "/Javascript/(.+)", "GET|POST", "jwf.action.pages.ActionJavascript"));
		//Chargement des images
		rewriter.addRule(new RewriteRule(URIroot + "/css/(.+)/(.+)", "GET|POST", "jwf.action.pages.ActionImage"));
		rewriter.addRule(new RewriteRule(URIroot + "/images/(.+)", "GET|POST", "jwf.action.pages.ActionImage"));
		//Chargement du CSS
		rewriter.addRule(new RewriteRule(URIroot + "/css/\\w", "GET|POST", "jwf.action.pages.ActionCss"));
			
		rewriter.addRule(new RewriteRule(URIroot + "/velocity", "GET|POST", "jwf.velocity.ActionVelocityTest"));
		
		/////////////////////Chargement des pages ////////////////////////////////////////////
		// Page d'accueil
		rewriter.addRule(new RewriteRule(URIroot + "/accueil", "GET|POST", "jwf.action.page.affichage.Accueil1"));
		
		rewriter.addRule(new RewriteRule(URIroot + "/inscription", "GET|POST", "jwf.action.page.affichage.Inscription"));
		
		rewriter.addRule(new RewriteRule(URIroot + "/musique", "GET|POST", "jwf.action.page.affichage.PageMusique"));
		
		rewriter.addRule(new RewriteRule(URIroot + "/clips", "GET|POST", "jwf.action.page.affichage.PageClips"));
			

	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		handle(request, response);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			
			
			rewriter.rewrite(c = new Context(request, response));
			if(checkClass(c)) {
				dispatcher.dispatch(c);
				/*if(checkClientRights(c)) {
					dispatcher.dispatch(c);
				}else if(checkAdminRights(c)) {
					dispatcher.dispatch(c);
					
				}
				else if(c._getRequest().getSession().isNew()){
					dispatcher.dispatch(c);
				}
				else {
				
					// 403
					JwfErrorHandler.displayError(c, 403, "you doesn't have the rights to view this page");
				}*/
			}
			 else {
				// 404
				JwfErrorHandler.displayError(c, 404, "the page doesn't exist");
			}
			
			//c.removeUploadedFiles();
		} catch (Exception e) {
			e.printStackTrace(); // 500
			JwfErrorHandler.displayError(c, 500, "error while loading page : " + e);
			}
		}
	
	
	private boolean checkClass(Context c) {
		return c.getActionClass() != null && c.getActionClass().length() > 0;
	}
	
	private boolean checkClientRights(Context c) throws Exception {
		

		boolean rep = false;


			 String var = ""; 
			 Enumeration e = c._getRequest().getSession().getAttributeNames();
			 while(e.hasMoreElements()){
				 var = (String)e.nextElement();
			 	 if(c.getActionClass().equals(var))
			 		rep = true;
			 
		}
		return rep;
	}
	
	private boolean checkAdminRights(Context c) throws Exception {
		

		boolean rep = false;

				 String var = ""; 
				 Enumeration e = c._getRequest().getSession().getAttributeNames();
				 while(e.hasMoreElements()){
					 var = (String)e.nextElement();
				 	 if(c.getActionClass().equals(var))
				 		rep = true;
			}
		
		
		return rep;
	}

	
}
