package jwf.action.pages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

import jwf.action.AActionCredential;

public class ActionImage extends AActionCredential implements IAction {

	@Override
	public void proceed(IContext context) {
		int position = context._getRequest().getRequestURL().lastIndexOf("/");
		// File css = new File();

		context._getResponse().setContentType("image/png");
		ServletOutputStream out = null;

		try {
			FileInputStream in = new FileInputStream(context._getRequest()
					.getRealPath(
							"/WEB-INF/templates/images/"
									+ context._getRequest().getRequestURL()
											.substring(position + 1)));
			int taille = in.available();
			// System.out.println(css.exists());
			// récupération d'une référence sur le fichier demandé

			// si le fichier est introuvable on envoie un code d'erreur 404
			if (in == null)
				context._getResponse().sendError(
						HttpServletResponse.SC_NOT_FOUND);

			try {

				// context._getResponse().setContentLength((int)css.length());
				// in = new FileInputStream(css);
				out = context._getResponse().getOutputStream();
				// Copy the contents of the file to the output stream
				byte[] buf = new byte[taille];
				int count = 0;
				while ((count = in.read(buf)) >= 0) {
					// System.out.println("passer");
					out.write(buf, 0, count);

				}

			} catch (IOException e) {
				// si une erreur se produit au milieu de la réponse
				// on envoie le code d'erreur HTTP adéquat
				context._getResponse().sendError(
						HttpServletResponse.SC_PARTIAL_CONTENT);
			} finally {
				// context._getResponse().setHeader("Cache-Control", "cache");
				// context._getResponse().setHeader("Pragma", "cache");
				out.close();
				in.close();

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
