package jwf.velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import jwf.action.AActionCredential;
import jwf.error.JwfErrorHandler;
import jwf.renderer.Renderer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.event.implement.IncludeRelativePath;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionVelocityTest extends AActionCredential {

	/*
	public static String[] getCredentials() {
		return new String[] { "user" } ;
	}*/

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
		vcontext.put("header", order.get("header"));
		vcontext.put("footer", order.get("footer"));
		vcontext.put("css", "/pages/styles.css");
		vcontext.put("cities", cities);
		context._getResponse().setContentType("text/html");
		Template t = null;
		try {

		try {
			t = ve.getTemplate("/pages/musical.vm");
		}
			catch( ResourceNotFoundException rnfe )
			{
			   System.out.println(rnfe.getMessage());
			}
			catch( ParseErrorException pee )
			{
			  // syntax error: problem parsing the template
				System.out.println(pee.getMessage());
			}
			catch( MethodInvocationException mie )
			{
			  // something invoked in the template
			  // threw an exception
				System.out.println(mie.getMessage());	
			}			
			StringWriter sw = new StringWriter();
			t.merge(vcontext, sw);
			
			context._getResponse().getWriter().println(sw.toString());
			
		} catch(Exception e) {
			JwfErrorHandler.displayError(context, 500, "error while writting response : " + e.getMessage());
			e.printStackTrace();
		}
		
	}


}
