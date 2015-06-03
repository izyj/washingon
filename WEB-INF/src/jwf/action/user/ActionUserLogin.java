package jwf.action.user;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import jwf.DAO.ToGet;
import jwf.action.AActionCredential;
import jwf.constantes.ConstantsKeys;
import jwf.context.Context;
import jwf.error.JwfErrorHandler;
import jwf.message.JwfMessage;
import jwf.modele.Administrateur;
import jwf.modele.Client;
import jwf.modele.Personne;
import jwf.renderer.Renderer;
import jwf.singleton.BddSingleton;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionUserLogin extends AActionCredential {

	@Override
	public void proceed(IContext context) {
		// Get login and password
		String login = ((Context) context).getParameterUnique("conPseudo");
		String pass = ((Context) context).getParameterUnique("conMdp");

		// context._getResponse().setContentType("text/html");

		if (login != null && !login.isEmpty() && pass != null
				&& !pass.isEmpty()) {

			boolean rep = false;
			Personne personne = null;
			Administrateur adm;
			BddSingleton con = BddSingleton.getInstance();
			Iterator it = con.ListeBDDAdministrateur.iterator();

			while (it.hasNext()) {
				adm = (Administrateur) it.next();
				if (adm.getPseudo().equals(login)
						&& adm.getMotDePasse().equals(pass)) {
					rep = true;
					personne = new Personne();
					personne.setStatus(ConstantsKeys.PERSONNE_STATUS_ADMIN);
					personne.setPrenom(adm.getPseudo());

					continue;
				} else {
					adm.setStatus(null);
				}
			}
			if (!rep) {
				it = con.ListeBDDClient.iterator();
				Client client;
				while (it.hasNext()) {
					client = (Client) it.next();
					if (client.getPseudo().equals(login)
							&& client.getMdp().equals(pass)) {
						rep = true;
						personne = new Personne();
						personne.setStatus(ConstantsKeys.PERSONNE_STATUS_CLIENT);
						personne.setPrenom(client.getPrenom());

						continue;
					} else {
						client.setStatus(null);
					}
				}

			}

			if (rep && personne != null) {
				HttpSession session = context._getRequest().getSession(false);
				// test si session existe
				if (null == session.getAttribute("user_name")) {
					// Save session

					context.setSessionAttribute("user_name",
							personne.getPrenom());
					context.setSessionAttribute("user_right",
							personne.getStatus());
					rightLoad(context);

				}
				// Redirect
				try {
					context._getResponse().sendRedirect(
							"http://localhost:8080/JWFISI/accueil");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JwfErrorHandler.displayError(context, 500,
							"error when redirecting");
				}

				return;

			} else
				// Redirect
				try {
					context._getResponse().sendRedirect(
							"http://localhost:8080/JWFISI/accueil");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JwfErrorHandler.displayError(context, 500,
							"error when redirecting");
				}
		} else {
			// Redirect
			try {
				context._getResponse().sendRedirect(
						"http://localhost:8080/JWFISI/accueil");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JwfErrorHandler.displayError(context, 500,
						"error when redirecting");
			}
		}
	}

	public void rightLoad(IContext c) {
		String[] listvalues = { ConstantsKeys.PERSONNE_STATUS_ADMIN,
				ConstantsKeys.PERSONNE_STATUS_CLIENT };
		String[] rep = null;
		JsonFactory f = new JsonFactory();
		JsonParser jp;
		Integer compteur = 0;

		try {

			PrintWriter out = c._getResponse().getWriter();
			jp = f.createJsonParser(new File(c._getRequest().getRealPath("/")
					+ "WEB-INF/json/PageNeedRight.json"));
			jp.nextToken();
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				String fieldname = jp.getCurrentName();
				jp.nextToken();

				while (jp.nextToken() != JsonToken.END_OBJECT) {
					String namefield = jp.getCurrentName();
					jp.nextToken();
					for (String name : listvalues) {
						if (name.equalsIgnoreCase(namefield))
							c._getRequest()
									.getSession(false)
									.setAttribute(compteur.toString(),
											namefield);
						compteur++;
					}
				}
			}
			c._getRequest()
					.getSession(false)
					.setAttribute(compteur.toString(),
							ConstantsKeys.PAGE_SESSION_NUMBER);
			jp.close();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
