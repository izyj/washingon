package jwf.action.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URLDecoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jwf.action.AActionCredential;

import org.apache.commons.lang.StringEscapeUtils;
import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionJavascript extends AActionCredential implements IAction {

	@Override
	public void proceed(IContext context) {
		int position = context._getRequest().getRequestURL().lastIndexOf("/");
		File javascript = new File(context._getRequest().getRealPath(
				"/WEB-INF/templates/javascript/"
						+ context._getRequest().getRequestURL()
								.substring(position + 1)));
		context._getResponse().setContentType(
				"application/x-javascript; charset=utf-8");
		context._getResponse().setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		FileInputStream in = null;
		ServletOutputStream out = null;
		String render = chargeFichierString(javascript);

		try {

			context._getResponse().getWriter().print(render);

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

	public String chargeFichierString(File fichier) {

		String chaine = "";
		try {
			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips, "UTF-8");
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				chaine += ligne;

			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		return chaine;
	}

	public void render(OutputStream out, IContext context, InputStream in,
			File Javascript) {
		try {
			out = context._getResponse().getOutputStream();

			if (Javascript == null)

				context._getResponse().sendError(
						HttpServletResponse.SC_NOT_FOUND);

			try {

				byte[] tampon = new byte[5 * 1024];
				in = new FileInputStream(Javascript);
				while (in.read(tampon) >= 0) {
					out.write(tampon);
				}

			} catch (IOException e) {
				// si une erreur se produit au milieu de la réponse
				// on envoie le code d'erreur HTTP adéquat
				// context._getResponse().sendError(HttpServletResponse.SC_PARTIAL_CONTENT);
			} finally {
				if (Javascript != null)
					in.close();
				out.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}