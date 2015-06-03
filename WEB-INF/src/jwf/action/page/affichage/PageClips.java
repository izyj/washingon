package jwf.action.page.affichage;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jwf.action.AActionCredential;
import jwf.error.JwfErrorHandler;
import jwf.renderer.Renderer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.event.implement.IncludeRelativePath;
import org.apache.velocity.runtime.RuntimeConstants;
import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class PageClips extends AActionCredential implements IAction {

	@Override
	public void proceed(IContext context) {
		String var = "<iframe title=\"YouTube video player\" width=\"240\" height=\"220\" src=\"http://www.youtube.com/embed/4tuSqN4pNF4\" frameborder=\"0\" allowfullscreen></iframe> <param name=\"FlashVars\" value=\"soundFile=http://new.hulkshare.com/stream/yglboyen3iol.mp3&titles=Ja Rule - Real Life Fantasy (Prod. By 7 Aurelius) ( 2o11 ) [ www.MzHipHop.com ].mp3&skin=sheep&dllink=http://www.hulkshare.com/yglboyen3iol/\"><param name=\"quality\" value=\"high\"><param name=\"menu\" value=\"false\"><param name=\"wmode\" value=\"transparent\"></object>";
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
		// vcontext.put("connecter",order.get(arg0))
		vcontext.put("header", order.get("header"));
		vcontext.put("footer", order.get("footer"));
		vcontext.put("lien", var);
		vcontext.put("css", "/pages/styles.css");
		context._getResponse().setContentType("text/html");
		Template t = null;

		if (context.getSessionAttribute("user_name") != null
				&& context.getSessionAttribute("user_right") != null) {
			vcontext.put("identifiant", context.getAttribute("user_name"));
			vcontext.put("credential", context.getAttribute("user_right"));
			vcontext.put("connecter", true);
		}

		try {
			t = ve.getTemplate("/pages/clips.vm");

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
