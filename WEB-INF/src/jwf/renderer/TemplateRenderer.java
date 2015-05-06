package jwf.renderer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import jwf.context.Context;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.renderer.interfaces.IRenderer;

public class TemplateRenderer implements IRenderer {
	
	public  String json = "" ;
	public 	String[] headfields = {"header","footer","current"};
	public 	String[] midfields = {"Content"};
	public String[] f;
	VelocityContext contextVel;
	
	public TemplateRenderer() {
		
	}

	@Override
	public String render(IContext context) {
		
		f = organize(context);		
		return "";
	}
	
	public void prioriteAffichage(IContext context) {
		
		
		// test parse	
			
		 
		
		  
		
	}
	/**
	 * Cette methode parse le fichier JSon et renvoi 
	 * @return
	 */
	public String[] organize(IContext context){
		
		String[] rep= null;
		JsonFactory f = new JsonFactory();
	    JsonParser jp;
	    HashMap<String, String> list = new HashMap<>();
	    
		try {
			
			PrintWriter out = context._getResponse().getWriter();
			jp = f.createJsonParser(new File(context._getRequest().getRealPath("/")+"WEB-INF/json/templateOrder.json"));
		    jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)+
		    while (jp.nextToken() != JsonToken.END_OBJECT) {
		      String fieldname = jp.getCurrentName();
		      jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
		      out.println(fieldname);
		      while (jp.nextToken() != JsonToken.END_OBJECT) {
		         String namefield = jp.getCurrentName();
		         jp.nextToken(); // move to value
		         if ("header".equalsIgnoreCase(namefield)) {
		        	 	
		        	 list.put("header", jp.getText());
		        
		         } else if ("footer".equalsIgnoreCase(namefield)) {
		        	 
		        	 list.put("footer", jp.getText());
		         }else if("tempConnexion".equalsIgnoreCase(namefield)){
		         	list.put("tempConnexion", jp.getText());
		         }
		         else if("pageInscription".equalsIgnoreCase(namefield)){
			         	list.put("inscription", jp.getText());
			         }
		         else if("pageMusique".equalsIgnoreCase(namefield)){
			         	list.put("musique", jp.getText());
			         	
			         }
		         //		        	 else {
//		           throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
//		         }
		       }
		      
		    
		   }
		    jp.close(); // ensure resources get cleaned up timely and properly
		   context.setPageOrder(list);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * Fin du test
		 * 
		 */
		return rep;
		
		
	}
	

	
}
