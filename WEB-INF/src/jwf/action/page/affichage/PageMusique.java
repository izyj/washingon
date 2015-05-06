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

public class PageMusique  extends AActionCredential implements IAction {
	
	@Override
	public void proceed(IContext context) {

        VelocityEngine ve = new VelocityEngine();
        
        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, context._getRequest().getRealPath("/")+"/WEB-INF/templates");
        ve.setProperty(RuntimeConstants.EVENTHANDLER_INCLUDE, IncludeRelativePath.class.getName());
        ve.init();
        
        new Renderer().render(context);
		VelocityContext vcontext = new VelocityContext();
		List<String> cities = new ArrayList<String>();
		HashMap<String, String> order = context.getPageOrder();
		//vcontext.put("connecter",order.get(arg0))
		vcontext.put("header", order.get("header"));
		vcontext.put("footer", order.get("footer"));
		vcontext.put("css", "/pages/styles.css");
		context._getResponse().setContentType("text/html");
		Template t = null;

		try {
			t = ve.getTemplate("/pages/musique.vm");
						
			StringWriter sw = new StringWriter();
			t.merge(vcontext, sw);
			
			context._getResponse().getWriter().println(sw.toString());
		} catch(Exception e) {
			JwfErrorHandler.displayError(context, 500, "error while writting response : " + e.getMessage());
			e.printStackTrace();
		}
	}

}
