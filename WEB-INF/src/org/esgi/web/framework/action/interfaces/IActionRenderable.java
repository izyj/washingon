package org.esgi.web.framework.action.interfaces;

import org.esgi.web.framework.renderer.interfaces.IRenderer;

public interface IActionRenderable extends IAction{
	public void setRenderer(IRenderer renderer);
	public String render();
}
