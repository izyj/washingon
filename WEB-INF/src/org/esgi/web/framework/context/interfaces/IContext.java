package org.esgi.web.framework.context.interfaces;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IContext {

	void setActionClass(String actionClass);
	String getActionClass();

	HttpServletRequest _getRequest();
	HttpServletResponse _getResponse();

	Object getParameter(String key);
	String[] getParameterKeys();
	File[] getUploadedFiles();

	void setAttribute(String key, Object o);
	Object getAttribute(String key);

	void setSessionAttribute(String key, Object value);
	Object getSessionAttribute(String key);

	public String[] getUserCredentials();

	boolean resetSession();

	IHtmlContext toHtmlContext();

	void setPageOrder(HashMap<String, String> pageOrder);
	HashMap<String, String> getPageOrder();

}
