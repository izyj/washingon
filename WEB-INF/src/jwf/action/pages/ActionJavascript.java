package jwf.action.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jwf.action.AActionCredential;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionJavascript extends AActionCredential implements IAction {

	@Override
	public void proceed(IContext context) {
		int position  = context._getRequest().getRequestURL().lastIndexOf("/");
		File javascript = new File(context._getRequest().getRealPath("/WEB-INF/templates/javascript/"+context._getRequest().getRequestURL().substring(position+1)));
		System.out.println("js :   "+context._getRequest().getRealPath("/WEB-INF/templates/javascript/"+context._getRequest().getRequestURL().substring(position+1)));
		context._getResponse().setContentType("text/javascript");
		ServletOutputStream out = null;
		FileInputStream in = null;

		try {
			 out = context._getResponse().getOutputStream();
			System.out.println(javascript.exists());
		 // récupération d'une référence sur le fichier demandé
		 
		 if (javascript == null) 
		  // si le fichier est introuvable on envoie un code d'erreur 404
		context._getResponse().sendError(HttpServletResponse.SC_NOT_FOUND);
		
		 try {
			   
		     byte[] tampon = new byte[7 * 1024];
		     in = new FileInputStream(javascript);
		     while (in.read(tampon) >= 0) {
		       out.write(tampon);
		     }
		   } catch (IOException e) {
		     // si une erreur se produit au milieu de la réponse
		     // on envoie le code d'erreur HTTP adéquat
			  // context._getResponse().sendError(HttpServletResponse.SC_PARTIAL_CONTENT);
		   } finally {
		     if (javascript != null) 
		       out.close();
		   }
		 } 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	} 
}