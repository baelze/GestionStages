package admin;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings({ "unused", "serial" })


public class Create extends HttpServlet {

public void doPost( HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException
{	String code_fonction=request.getParameter("code_fonction");
	String nom=request.getParameter("nom");
	String prenom=request.getParameter("prenom");
	String fonction=request.getParameter("fonction");
	String password=request.getParameter("password");
	String email=request.getParameter("email");
	
	// BASE DE DONNEES
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(); 
	
	Entity admin = new Entity("admin");
	admin.setProperty("code_fonction", code_fonction); 
	admin.setProperty("nom", nom);
	admin.setProperty("prenom",prenom);
	admin.setProperty("focntion",fonction);
	admin.setProperty("email",email);
	admin.setProperty("password",password);
	
	datastore.put(admin);// on met dans la base de donnees datastolre la table schedule avec les enregistrements
	
	
	
}

}