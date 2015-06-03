package jwf.action.pages;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import jwf.action.AActionCredential;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionCss extends AActionCredential implements IAction {

	public ActionCss() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int setPriority(int priority) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addCredential(String role) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean needsCredentials() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasCredential(String[] roles) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void proceed(IContext context) {

		int position = context._getRequest().getRequestURL().lastIndexOf("/");
		File css = new File(context._getRequest().getRealPath(
				"/WEB-INF/templates/css/"
						+ context._getRequest().getRequestURL()
								.substring(position + 1)));
		context._getResponse().setContentType("text/css");

		ServletOutputStream out;
		FileInputStream in = null;
		try {

			out = context._getResponse().getOutputStream();
			if (css == null)

				context._getResponse().sendError(
						HttpServletResponse.SC_NOT_FOUND);

			try {

				byte[] tampon = new byte[6 * 1024];
				in = new FileInputStream(css);
				while (in.read(tampon) >= 0) {
					out.write(tampon);
				}

			} catch (IOException e) {
				// si une erreur se produit au milieu de la réponse
				// on envoie le code d'erreur HTTP adéquat
				// context._getResponse().sendError(HttpServletResponse.SC_PARTIAL_CONTENT);
			} finally {
				if (css != null)
					in.close();
				out.flush();
				out.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * récupere le type MIME
	 * 
	 * @param file
	 * @return
	 */
	public static String getMIMEType(File file) {
		if (file.isDirectory()) {
			return "repertoire";
		}
		if (!file.exists()) {
			return "fichier inexistant";
		}
		try {
			URL url = file.toURL();
			URLConnection connection = url.openConnection();
			return connection.getContentType();
		} catch (MalformedURLException mue) {
			return mue.getMessage();
		} catch (IOException ioe) {
			return ioe.getMessage();
		}
	}
}
