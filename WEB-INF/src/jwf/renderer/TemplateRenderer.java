package jwf.renderer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
	public TemplateRenderer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String render(IContext context) {
		
		return null;
	}
	
	public void parseJson(IContext context) throws IOException{
		PrintWriter out = context._getResponse().getWriter();
		
		// test parse	
			JsonFactory f = new JsonFactory();
		    JsonParser jp;
			try {
				jp = f.createJsonParser(new File("D:\\jsontest\\user.json"));
				 //  User user = new User();
			    jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
			    while (jp.nextToken() != JsonToken.END_OBJECT) {
			      String fieldname = jp.getCurrentName();
			      jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
			      if ("name".equalsIgnoreCase(fieldname)) { // contains an object
			        //Name name = new Name();
			    	  out.println(fieldname);
			      while (jp.nextToken() != JsonToken.END_OBJECT) {
			         String namefield = jp.getCurrentName();
			         out.println(namefield);
			         
			         out.println();
			         jp.nextToken(); // move to value
			         if ("first".equals(namefield)) {
			        
			         } else if ("last".equals(namefield)) {
			          
			         } else {
			           throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
			         }
			       }
			      
			     } 
			   }
			    jp.close(); // ensure resources get cleaned up timely and properly
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
		 
		
		  
		
	}
	
	

	
}
