package org.esgi.web.framework.router.interfaces;

import org.esgi.web.framework.context.interfaces.IContext;

/*
 * responsible to dispatch the request to a specific action instance.
 */
public interface IDispatcher {
	void dispatch(IContext context);
}
