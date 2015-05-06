package jwf.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jwf.action.AActionCredential;
import jwf.context.Context;
import jwf.error.JwfErrorHandler;
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
		BddSingleton.getInstance();
		//recuperation du formulaire d'inscription
		rewriter.addRule(new RewriteRule(URIroot + "/recuperation", "GET|POST", "jwf.action.pages.ActionTraitementFormulaire"));
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
		
	//	rewriter.addRule(new RewriteRule(URIroot + "/clips", "GET|POST", "jwf.action.page.affichage.PageMusique"));
		
		/**
		// Login form
		rewriter.addRule(new RewriteRule(URIroot + "/user/login", "GET|POST", "jwf.action.user.ActionUserLogin"));
		
		// Logout page
		rewriter.addRule(new RewriteRule(URIroot + "/user/logout", "GET|POST", "jwf.action.user.ActionUserLogout"));

		// Add an user
		rewriter.addRule(new RewriteRule(URIroot + "/user/add", "POST", "jwf.action.user.ActionUserAdd"));

		// Search an user
		rewriter.addRule(new RewriteRule(URIroot + "/user/search/(.+)", "POST", "jwf.action.user.ActionUserSearch", new String[] { "user-login" }));
		 
		// Delete an user
		rewriter.addRule(new RewriteRule(URIroot + "/user/delete/([0-9]+)", "POST", "jwf.action.user.ActionUserDelete", new String[] { "user-id" }));
		
		// List all 
		rewriter.addRule(new RewriteRule(URIroot + "/user/list", "GET", "jwf.action.user.ActionUserDisplayAll"));

		// List specific user (or self)
		rewriter.addRule(new RewriteRule(URIroot + "/user/?([0-9]+)?", "GET", "jwf.action.user.ActionUserDisplay", new String[] { "user-id" }));
		
		// Edit specific user (or self)
		rewriter.addRule(new RewriteRule(URIroot + "/user/?([0-9]+)?", "POST", "jwf.action.user.ActionUserEdit", new String[] { "user-id" }));
		
		*/
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
				if(checkRights(c)) {
					dispatcher.dispatch(c);
				} else {
					// 403
					JwfErrorHandler.displayError(c, 403, "you doesn't have the rights to view this page");
				}
			} else {
				// 404
				JwfErrorHandler.displayError(c, 404, "the page doesn't exist");
			}
			
			c.removeUploadedFiles();
		} catch (Exception e) {
			e.printStackTrace(); // 500
			JwfErrorHandler.displayError(c, 500, "error while loading page : " + e);
		}
	}
	
	private boolean checkClass(Context c) {
		return c.getActionClass() != null && c.getActionClass().length() > 0;
	}
	
	private boolean checkRights(Context c) throws Exception {
		
		
		return true;
	}
	private boolean checkIfConnected(Context c){
	
		
		//BddSingleton.listeconnection.containsKey(c._getResponse().) 
		return false;
		
	}
	
}
