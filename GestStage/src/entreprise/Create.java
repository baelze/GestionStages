package entreprise;

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
{	String libelle=request.getParameter("libelle");
	String adresse=request.getParameter("adresse");
	String site_web=request.getParameter("site_web");
	String email=request.getParameter("email");
	String password=request.getParameter("password");
	
	
	// BASE DE DONNEES
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(); 
	
	Entity entreprise = new Entity("entreprise");
	entreprise.setProperty("libelle", libelle); 
	entreprise.setProperty("adresse", adresse);
	entreprise.setProperty("site_web",site_web);
	entreprise.setProperty("email",email);
	entreprise.setProperty("password",password);
	
	datastore.put(entreprise);// on met dans la base de donnees datastolre la table schedule avec les enregistrements
	
	
	
}

}