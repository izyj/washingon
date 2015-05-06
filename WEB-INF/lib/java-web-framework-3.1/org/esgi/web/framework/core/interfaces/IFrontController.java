package org.esgi.web.framework.core.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Responsible to handle any request from client.
 */
public interface IFrontController {
	void handle(HttpServletRequest request, HttpServletResponse response);
}
