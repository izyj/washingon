package org.esgi.web.framework.context.interfaces;

public interface IHtmlContext extends IContext {
	
	void setPageTitle(String title);
	void setPageDescription(String description);
	void addKeyword(String keyword);
	void addCssLink(String url);
	void addJsLink(String url);

	String getPageTitle();
	String getPageDescription();
	String[] getKeywords();
	String[] getCssLinks();
	String[] getJsLinks();
	
}
