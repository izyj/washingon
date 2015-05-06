package org.esgi.web.framework.action.interfaces;

import org.esgi.web.framework.context.interfaces.IContext;

public interface IAction {

	/**
	 * the highest priority is 0, the lower is Integer.MAX_VALUE
	 * used in the Dispatcher priority queue. 
	 */
	int setPriority(int priority);
	int getPriority();
	
	
	/**
	 *  may only be used in implementation constructor scope.
	 *  @param role 
	 */
	void addCredential(String role);
	
	/**
	 * 
	 * @return true if credentials are needed for this action.
	 */
	boolean needsCredentials();
	
	/**
	 * @return true if at least one given roles matches with require roles.
	 */
	boolean hasCredential(String[] roles);
	
	void proceed(IContext context);
	
}
