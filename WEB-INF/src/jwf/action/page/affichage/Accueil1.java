package jwf.action.page.affichage;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.event.implement.IncludeRelativePath;
import org.apache.velocity.runtime.RuntimeConstants;
import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

import jwf.action.AActionCredential;
import jwf.constantes.ConstantsKeys;
import jwf.error.JwfErrorHandler;
import jwf.renderer.Renderer;

public class Accueil1 extends AActionCredential implements IAction {

	@Override
	public void proceed(IContext context) {

		VelocityEngine ve = new VelocityEngine();

		ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, context
				._getRequest().getRealPath("/") + "/WEB-INF/templates");
		ve.setProperty(RuntimeConstants.EVENTHANDLER_INCLUDE,
				IncludeRelativePath.class.getName());
		ve.init();

		new Renderer().render(context);
		VelocityContext vcontext = new VelocityContext();
		List<String> cities = new ArrayList<String>();
		HashMap<String, String> order = context.getPageOrder();
		vcontext.put("header", order.get("header"));
		vcontext.put("footer", order.get("footer"));
		vcontext.put("css", "/pages/styles.css");
		vcontext.put("cities", cities);
		vcontext.put("connecter", false);

		System.out.println(context.getSessionAttribute("user_name"));
		if (context.getSessionAttribute("user_name") != null
				&& context.getSessionAttribute("user_right") != null) {
			vcontext.put("identifiant", context.getAttribute("user_name"));
			vcontext.put("credential", context.getAttribute("user_right"));
			vcontext.put("connecter", true);

			if (((String) context.getSessionAttribute("user_right"))
					.equals(ConstantsKeys.PERSONNE_STATUS_ADMIN)) {
				vcontext.put("connecteradm", true);

			}
		}
		context._getResponse().setContentType("text/html");
		Template t = null;

		try {
			t = ve.getTemplate("/pages/accueil.vm");

			StringWriter sw = new StringWriter();
			t.merge(vcontext, sw);

			context._getResponse().getWriter().println(sw.toString());
		} catch (Exception e) {
			JwfErrorHandler.displayError(context, 500,
					"error while writting response : " + e.getMessage());
			e.printStackTrace();
		}
	}

}
