package jwf.action.user;

import java.io.IOException;

import jwf.action.AActionCredential;
import jwf.error.JwfErrorHandler;
import jwf.message.JwfMessage;
import jwf.renderer.Renderer;

import org.esgi.web.framework.context.interfaces.IContext;

public class ActionUserLogout extends AActionCredential {

	@Override
	public void proceed(IContext context) {

		context._getRequest().getSession(false).invalidate();
		try {
			context._getResponse().sendRedirect(
					"http://localhost:8080/JWFISI/accueil");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
