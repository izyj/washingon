package jwf.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import jwf.constantes.BddConstantesKeys;

public class Database {

	protected Connection con;
	protected Statement st ;
	protected String requete ;
	  
	
	public Database() {
		
	}

	
	protected void OpenConnection(){
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(BddConstantesKeys.urlConnexion,BddConstantesKeys.login,BddConstantesKeys.motDePasse);
			st = con.createStatement();
			
			
		}
		catch(SQLException e){
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	protected void CloseConnection(){
		
		try {
			con.close();
			st.close();
			
			
		}catch(SQLException e){
			
			e.printStackTrace();
			
		}
		
	}
}
