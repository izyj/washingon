package org.esgi.web.framework.router.interfaces;

import org.esgi.web.framework.context.interfaces.IContext;

public interface IRewriter {

	void addRule(IRewriteRule rule); 

	/* set the action class and parameters */
	void rewrite(IContext request);
	
}
