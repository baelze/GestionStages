package stage;

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


public class Inscription extends HttpServlet {

public void doPost( HttpServletRequest request,HttpServletResponse response ) throws ServletException, IOException
{
	String nom=request.getParameter("nom");
	String prenom=request.getParameter("prenom");
	String email=request.getParameter("email");
	String dateNaissance=request.getParameter("dateNaissance");
	//String formation=request.getParameter("formation");
	String niveau=request.getParameter("niveau");
	String password=request.getParameter("password");
	String domaine=request.getParameter("domaine");
	
	
	// BASE DE DONNEES
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(); 
	
	Entity etudiant = new Entity("etudiant");
	
	etudiant.setProperty("nom", nom);
	etudiant.setProperty("prenom",prenom);
	etudiant.setProperty("email", email); 
	etudiant.setProperty("dateNaissance",dateNaissance);
	//etudiant.setProperty("formation",formation);
	etudiant.setProperty("niveau",niveau);
	etudiant.setProperty("domaine",domaine);
	etudiant.setProperty("dateNaissance",dateNaissance);
	etudiant.setProperty("password",password);
	
	datastore.put(etudiant);// on met dans la base de donnees datastolre la table schedule avec les enregistrements
	
	
	
}

}