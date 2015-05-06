package org.esgi.web.framework.router.interfaces;

import org.esgi.web.framework.context.interfaces.IContext;

public interface IRewriteRule {
	boolean matches(IContext context);
	void rewrite(IContext context);
}
