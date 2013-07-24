package prof;

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
{	String identifiant=request.getParameter("identifiant");
	String nom=request.getParameter("nom");
	String prenom=request.getParameter("prenom");
	String email=request.getParameter("email");
	String specialite=request.getParameter("specialite");
	String password=request.getParameter("password");
	
	
	// BASE DE DONNEES
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(); 
	
	Entity professeur = new Entity("professeur");
	professeur.setProperty("identifiant", identifiant); 
	professeur.setProperty("nom", nom);
	professeur.setProperty("prenom",prenom);
	professeur.setProperty("email",email);
	professeur.setProperty("specialite",specialite);
	professeur.setProperty("password",password);
	
	datastore.put(professeur);// on met dans la base de donnees datastolre la table schedule avec les enregistrements
	
	
	
}

}