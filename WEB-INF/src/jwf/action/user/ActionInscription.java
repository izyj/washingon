package jwf.action.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jwf.DAO.ToSave;
import jwf.action.AActionCredential;
import jwf.modele.Client;

import jwf.router.Dispatcher;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.router.interfaces.IDispatcher;

public class ActionInscription extends AActionCredential implements IAction {

	private Client client = new Client();;
	private IDispatcher dispatcher = new Dispatcher();
	private ToSave bdd = new ToSave();

	@Override
	public void proceed(IContext context) {
		if (!context._getRequest().getParameterMap().isEmpty()) {

			if (!context._getRequest().getParameter("nom").isEmpty()) {

				client.setNom(context._getRequest().getParameter("nom"));

			}
			if (!context._getRequest().getParameter("prenom").isEmpty()) {
				client.setPrenom(context._getRequest().getParameter("prenom"));
			}
			if (!context._getRequest().getParameter("pseudo").isEmpty()) {
				client.setPseudo(context._getRequest().getParameter("pseudo"));
			}
			if (!context._getRequest().getParameter("mdp").isEmpty()) {
				client.setMdp(context._getRequest().getParameter("mdp"));
			}
			if (!context._getRequest().getParameter("cp").isEmpty()) {
				client.setCodeP(context._getRequest().getParameter("cp"));
			}
			if (!context._getRequest().getParameter("ville").isEmpty()) {
				client.setVille(context._getRequest().getParameter("ville"));
			}
			if (!context._getRequest().getParameter("rue").isEmpty()) {
				client.setRue(context._getRequest().getParameter("rue"));
			}
			if (!context._getRequest().getParameter("numtel").isEmpty()) {
				client.setNumtel(context._getRequest().getParameter("numtel"));
			}
			System.out.println(client.toString());

			bdd.ToSaveClient(client);

			try {
				context._getResponse().sendRedirect(
						"http://localhost:8080/JWFISI/accueil");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}